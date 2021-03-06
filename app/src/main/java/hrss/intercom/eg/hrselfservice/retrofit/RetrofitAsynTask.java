package hrss.intercom.eg.hrselfservice.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import hrss.intercom.eg.hrselfservice.api.APIListener;
import hrss.intercom.eg.hrselfservice.api.ServerConfig;
import hrss.intercom.eg.hrselfservice.helpers.Constants;
import hrss.intercom.eg.hrselfservice.helpers.Log;
import hrss.intercom.eg.hrselfservice.offline.json.parser.StaticJSONParser;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emad.Essam on 9/26/2016.
 */
public class RetrofitAsynTask extends AsyncTask<String, Integer, Object> {
    APIListener mListener;
    public static String DEMO_CONSTANT = "*";

    String mUri;
    private ProgressDialog mDialog;
    private boolean isLoading = true;
    Map<String, String> mRetrofitHeader;
    String mMethod;
    int mID;
    Map<String, String> options;
    private Context context;
    Call<JsonObject> call;
    String mResponseBody;
    int mResponseCode;


    public RetrofitAsynTask(int pID, String pUri, String pMethod, Map<String, String> pHeaders,
                            Map<String, String> options, APIListener pListener, Context context) {
        mDialog = new ProgressDialog(context);
        mDialog.setCancelable(false);
        mRetrofitHeader = pHeaders;
        mListener = pListener;
        mUri = pUri;
        mMethod = pMethod;
        mID = pID;
        this.options = options;

        this.context = context;

        call = null;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Object doInBackground(String... params) {

        Log.e("Building API Request ", ServerConfig.SERVER_URl + mUri + "hereee");


        if (DEMO_CONSTANT.equalsIgnoreCase(Constants.DEMO_ON)) {
            //offline mode
            StaticJSONParser.checkURI(mUri, mMethod);
        } else {
            /////////////

            RetrofitServiceInterface mRetrofitService = RetrofitServiceInterface.retrofit.create(RetrofitServiceInterface.class);


            if (mMethod.equalsIgnoreCase(ServerConfig.METHOD_GET)) {
                call = mRetrofitService.getMethode(mUri);


            } else if (mMethod.equalsIgnoreCase(ServerConfig.METHOD_POST)) {

                if (options != null) {

                    Map<String, String> data = new HashMap<>();
                    for (String key : options.keySet()) {
                        Object value = options.get(key);
                        if (value != null) {
                            Log.e("key", key + "huh");
                            Log.e("value", value + "huh");
                            data.put(key, value.toString());
                        }
                        call = mRetrofitService.postMethode(mUri, data);
                    }

                } else {

                }
            } else {
                return -1;
            }

        }
        return null;


    }

    @Override
    protected void onPostExecute(Object o) {
        if (RetrofitAsynTask.DEMO_CONSTANT.equalsIgnoreCase(Constants.DEMO_ON)) {//offline mode
            mResponseBody = StaticJSONParser.checkURI(mUri, mMethod);

            JSONObject resultObject = null;

            try {
                resultObject = new JSONObject(mResponseBody);
                String mResult = resultObject.getString(Constants.RESULT);


                mListener.onSuccess(mID, mUri, mResponseBody);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {


            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //   final TextView textView = (TextView) findViewById(R.id.textView);
                    mResponseCode = response.code();
                    if (response.isSuccessful()) {

                        mResponseBody = response.body().toString();

                        Log.e("mRetrofitResponseBody", mResponseBody.toString() + "" + mResponseCode
                                + "");


                        JSONObject resultObject = null;

                        try {
                            resultObject = new JSONObject(mResponseBody);
                            String mResult = resultObject.getString(Constants.RESULT);


                            mListener.onSuccess(mID, mUri, mResponseBody);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                        // Handle unauthorized
                    } else {
                        mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                        // Handle other responses
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                    mResponseBody = t.getMessage();

                    mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                    // Handle unauthorized
                }
            });

        }
    }
}
