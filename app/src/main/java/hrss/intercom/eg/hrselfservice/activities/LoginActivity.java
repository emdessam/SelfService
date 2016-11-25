package hrss.intercom.eg.hrselfservice.activities;

import android.content.Context;
import android.content.Intent;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import hrss.intercom.eg.hrselfservice.R;
import hrss.intercom.eg.hrselfservice.api.APIListener;
import hrss.intercom.eg.hrselfservice.api.ServerConfig;
import hrss.intercom.eg.hrselfservice.helpers.Constants;
import hrss.intercom.eg.hrselfservice.helpers.Log;
import hrss.intercom.eg.hrselfservice.helpers.MyApplication;
import hrss.intercom.eg.hrselfservice.helpers.Utility;
import hrss.intercom.eg.hrselfservice.retrofit.RerofitInterceptor;
import hrss.intercom.eg.hrselfservice.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

/**
 * Created by Emad.Essam on 9/2/2016.
 */

public class LoginActivity extends Activity implements APIListener {
    public String Signin;
    String engNme;
    String email;
    String password;
    String empName;
    String mgrName;
    String mgrCode;
    String passText;
    String userIDText;
    LinearLayout loginLayout;
    public String Signout;
    public String role;
    public String gender;
    TextView other;
    CircleImageView otherPerson;
    TextView EMpName;
    String empPhoto;
    String Token;
    TextInputLayout user;
    public JSONObject resultObject = null;


    Context mContext;


    private EditText mUserNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onResume() {
        MyApplication.setCurrentActivity(this);

        if (engNme != null) {
            Log.e("engName", engNme);

            EMpName.setText("Welcome, " + engNme);
//            user.setHint(userID);
            mUserNameView.setText(userIDText);
        } else {

            EMpName.setText(R.string.other_person);
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mContext = this;
        mUserNameView = (EditText) findViewById(R.id.email);

        loginLayout = (LinearLayout) findViewById(R.id.parent);


//        populateAutoComplete();
        passText = Constants.getDataInSharedPrefrences(Constants.PASSWORD, mContext);
        userIDText = Constants.getDataInSharedPrefrences(Constants.USER_ID, mContext);
        engNme = Constants.getDataInSharedPrefrences(Constants.ENG_NAME, mContext);
        empPhoto = Constants.getDataInSharedPrefrences(Constants.USER_PHOTO, mContext);
        byte[] decodedString = Base64.decode(empPhoto, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        otherPerson = (CircleImageView) findViewById(R.id.profile_image);
        user = (TextInputLayout) findViewById(R.id.user_name);

        otherPerson.setImageBitmap(decodedByte);
        EMpName = (TextView) findViewById(R.id.other_person);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Constants.isNetworkOnline(mContext)) {

                    attemptLogin();


                } else {

                    final Snackbar snackbar = Snackbar.make(loginLayout, "Connect to internet", 5000)
                            .setAction("RETRY", new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {


                                    if (Constants.isNetworkOnline(mContext)) {

                                        Snackbar.make(loginLayout, "You are connected now", Snackbar.LENGTH_SHORT).show();

                                    } else {
                                        Snackbar.make(loginLayout, "You aren't connected now", Snackbar.LENGTH_SHORT).show();

                                    }
                                }
                            });
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();


                }
            }
        });
        other = (TextView) findViewById(R.id.other);
        otherPerson = (CircleImageView) findViewById(R.id.other_user_profile);
        other.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                mPasswordView.setText("");

                mUserNameView.setText("");
                mUserNameView.setEnabled(true);
                mUserNameView.requestFocus();


            }
        });
        otherPerson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserNameView.setEnabled(true);
                mUserNameView.requestFocus();

            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mUserNameView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            if (mUserNameView.getText().toString().equalsIgnoreCase(Constants.Demo)) {
                Log.e("DEMO On", "here");
                RetrofitAsynTask.DEMO_CONSTANT = Constants.DEMO_ON;

            } else {
                Log.e("DEMO Off", "here");
                RetrofitAsynTask.DEMO_CONSTANT = Constants.DEMO_OFF;
            }

            login();
        }
    }


    @Override
    public void onSuccess(int id, String url, String response) {

        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.e("success response", response + "hduer");


        if (id == 0) {

            try {
                resultObject = new JSONObject(response);

                String mResult = resultObject.getString("result");
                if (mResult.equalsIgnoreCase("1")) {
                    role = resultObject.getString("role");
                    Signin = resultObject.getString("in");
                    Signout = resultObject.getString("out");
                    gender = resultObject.getString("gender");
                    Token = resultObject.getString("token");
//                    password = resultObject.getString("password");
//                    email = resultObject.getString("userId");
                    empName = resultObject.getString("empName");
                    mgrName = resultObject.getString("mgrName");
                    mgrCode = resultObject.getString("mgrCode");

                    Constants.SaveDataInSharedPrefrences(Constants.USER_TOKEN, Token, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.USER_ID, email, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.PASSWORD, password, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.EMP_NAME, empName, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.MGR_NAME, mgrName, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.MGR_CODE, mgrCode, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.ROLE, role, mContext);
                    Constants.SaveDataInSharedPrefrences(Constants.GENDER, gender, mContext);


                    Constants.SaveDataInSharedPrefrences(Constants.IS_USER_LOGIN, "true", mContext);
                    Log.e("1111111111111111111111Token:: ", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("in", Signin);
                    i.putExtra("out", Signout);
                    finish();
                    startActivity(i);
                    //  Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
                    ////then open activity
                } else if (mResult.equalsIgnoreCase("0")) {

                    String msg = resultObject.getString("msg");
                    Toast.makeText(this, "Invalid username and / or password", Toast.LENGTH_LONG).show();
                    if (msg.equalsIgnoreCase("invalid")) {

                        Toast.makeText(this, "Invalid username and / or password", Toast.LENGTH_LONG).show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        // showProgress(false);

        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.d("statusCode", responseCode + "ggg");
    }


    public void login() {
        Log.e("Login On", "here");
        Map<String, String> mRetrofitParams = new HashMap<>();
        mRetrofitParams.put("userId", mUserNameView.getText().toString());
        mRetrofitParams.put("password", mPasswordView.getText().toString());
        Constants.httpClient = new OkHttpClient.Builder();
        Map<String, String> mHeader = new HashMap<>();
        Constants.httpClient.addInterceptor(new RerofitInterceptor(mHeader, mContext));
        new RetrofitAsynTask(0, ServerConfig.LOGIN_PATH, ServerConfig.METHOD_POST, null, mRetrofitParams
                , this, this).execute();


    }
}

