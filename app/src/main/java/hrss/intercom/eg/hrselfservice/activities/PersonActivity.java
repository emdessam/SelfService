package hrss.intercom.eg.hrselfservice.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import hrss.intercom.eg.hrselfservice.R;
import hrss.intercom.eg.hrselfservice.api.APIListener;
import hrss.intercom.eg.hrselfservice.api.ProfileResults;
import hrss.intercom.eg.hrselfservice.api.ServerConfig;
import hrss.intercom.eg.hrselfservice.helpers.Constants;
import hrss.intercom.eg.hrselfservice.helpers.Utility;
import hrss.intercom.eg.hrselfservice.retrofit.RerofitInterceptor;
import hrss.intercom.eg.hrselfservice.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

public class PersonActivity extends Activity implements APIListener {

//    public  JSONObject jsonObject=null;

    public ProfileResults jsonObject;
    TextView empCode;
    TextView arabicName;
    TextView engName;
    CircleImageView profImage;
    TextView birthDate;
    TextView jobTitle;
    TextView workEmail;
    TextView hiringDate;
    TextView directManager;
    TextView department;
    TextView officeExtension;
    TextView mobile1;
//    TextView mobile2;
//    TextView arabicShort;
//    TextView engShort;

    //    TextView personalEmail;
    TextView homePhone;
    TextView socialStatus;
    TextView country;
    TextView city;
    TextView postalCode;
    TextView address;

    String mEngShort;
    String mBirthDate;
    String mJobTitle;
    String mHiringDate;
    String mDirectManager;
    String mDepartment;
    String mOfficeExtension;
    String mMobile1;
    String mMobile2;
    String mWorkEmail;
    String mPersonalEmail;
    String mProfileImage;
    String mHomePhone;
    String mSocialStatus;
    String mCountry;
    String mCity;
    String mPostalCode;
    String mAddress;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        mContext = this;

        Log.e("7777777777777777", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext);

        empCode = (TextView) findViewById(R.id.emp_code);
        arabicName = (TextView) findViewById(R.id.arabic_name);
        birthDate = (TextView) findViewById(R.id.birth_date);
        jobTitle = (TextView) findViewById(R.id.job_title);
        hiringDate = (TextView) findViewById(R.id.hiring_date);
        directManager = (TextView) findViewById(R.id.direct_manager);
        department = (TextView) findViewById(R.id.department_name);
        workEmail = (TextView) findViewById(R.id.work_email);
        engName = (TextView) findViewById(R.id.eng_name);
//        arabicShort  = (TextView)findViewById(R.id.arabic_short);
//        engShort  = (TextView)findViewById(R.id.eng_short);
        officeExtension = (TextView) findViewById(R.id.office_extension);
        mobile1 = (TextView) findViewById(R.id.mobile1);
//        mobile2  = (TextView)findViewById(R.id.mobile2);
//        personalEmail = (TextView)findViewById(R.id.personal_email);
        homePhone = (TextView) findViewById(R.id.home_phone);
        socialStatus = (TextView) findViewById(R.id.social_status);
        country = (TextView) findViewById(R.id.country);
        city = (TextView) findViewById(R.id.city);
        postalCode = (TextView) findViewById(R.id.postal_code);
        address = (TextView) findViewById(R.id.address);


        getProfileDetails();

    }

    public void getProfileDetails() {
        Log.e("eweweeeeeeeeeee", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Utility.showProgressDialog(PersonActivity.this, getString(R.string.Loading));


        Map<String, String> mRetrofitHeader = new HashMap<>();
        String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext);


        mRetrofitHeader.put("token", TOKEN);
        Utility.generateRetrofitHttpHeader(this);
//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

//    Map<String, String> mNewHeader = new HashMap<>();
//
//    mNewHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
        Constants.httpClient = new OkHttpClient.Builder();

        Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader, mContext));

        new RetrofitAsynTask(0, ServerConfig.PROFILE_DETAIL, ServerConfig.METHOD_GET, mRetrofitHeader, null
                , this, this).execute();

    }

    @Override
    public void onSuccess(int id, String url, String response) {

        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.e("person Response in main", response + "gg");
        try {
            jsonObject = new ProfileResults();
            Gson gson = new Gson();

            jsonObject = gson.fromJson(response, ProfileResults.class);
            String mResult = String.valueOf(jsonObject.getResult());


            if (mResult.equalsIgnoreCase("1")) {
                Log.e("person Response in main", response + "gg");

//                mEmpCode = String.valueOf(jsonObject.getEmpCode());

//                mArabicName = jsonObject.getEmpArName();
//                mBirthDate = jsonObject.getBirthDate();
//                mJobTitle = jsonObject.getString("jobTitle");
//                mHiringDate = jsonObject.getString("employmentDate");
//                mDirectManager = jsonObject.getString("mngrEnName");
//                mDepartment = jsonObject.getString("deptEnName");
//                mWorkEmail = jsonObject.getString("workMail");
//                mEngName = jsonObject.getString("empEnName");
//                mArabicShort = jsonObject.getString("empArShortName");
//                mEngShort = jsonObject.getString("empEnShortName");
//                mOfficeExtension = jsonObject.getString("ext");
//                mMobile1 = jsonObject.getString("mobile1");
//                mMobile2 =  jsonObject.getString("mobile2");
//                mPersonalEmail = jsonObject.getString("personalMail");
//                mHomePhone = jsonObject.getString("homePhone");
//                mSocialStatus =jsonObject.getString("socialStatus");
//                mCountry = String.valueOf(jsonObject.getString("country"));
//                mCity = String.valueOf(jsonObject.getString("city"));
//                mPostalCode = jsonObject.getString("postalCode");
//                mAddress = jsonObject.getString("homeAddress");
//                Log.e("33333333333333333test",mEmpCode);
//                mProfileImage = String.valueOf(jsonObject.get("photo"));
                empCode.setText(String.valueOf(jsonObject.getEmpCode()));
                arabicName.setText(jsonObject.getEmpArName());
                birthDate.setText(jsonObject.getBirthDate());
                jobTitle.setText(jsonObject.getJobTitle());
                hiringDate.setText(jsonObject.getEmploymentDate());
                directManager.setText(jsonObject.getMngrEnName());
                department.setText(jsonObject.getDeptEnName());
                workEmail.setText(jsonObject.getWorkMail());
                engName.setText(jsonObject.getEmpEnName());
//                arabicShort.setText(jsonObject.getEmpArShortName());
//                engShort.setText(jsonObject.getEmpEnShortName());
                officeExtension.setText(jsonObject.getExt());
                mobile1.setText(jsonObject.getMobile1());
//                mobile2.setText(jsonObject.getMobile2());
//                personalEmail.setText(jsonObject.getPersonalMail());
                homePhone.setText(jsonObject.getHomePhone());
                socialStatus.setText(jsonObject.getSocialStatus());
                country.setText(String.valueOf(jsonObject.getCountry()));
                String mCountry = country.getText().toString();
                mSocialStatus = socialStatus.getText().toString();
                city.setText(String.valueOf(jsonObject.getCity()));
                postalCode.setText(jsonObject.getPostalCode());
                address.setText(jsonObject.getHomeAddress());
                mCity = city.getText().toString();
                city.setText("Cairo");
//                mPersonalEmail = personalEmail.getText().toString();
                if (mCountry.equalsIgnoreCase("1")) {
                    country.setText("Egypt");

                } else {

                    country.setText("United States");
                }
                if (mSocialStatus.equalsIgnoreCase("0")) {
                    socialStatus.setText("Single");

                } else {

                    socialStatus.setText("Married");
                }
                if (mCity.equalsIgnoreCase("81")) {
                    city.setText("Cairo");

                } else {

                    city.setText("Giza");
                }
//                if(mPersonalEmail.equalsIgnoreCase(null)){
//                    personalEmail.setText("username@example.com");
//
//                }else{
//
//                    personalEmail.setText(jsonObject.getPersonalMail());
//                }

                String empEnName = jsonObject.getEmpEnName();
                String[] separated = empEnName.split(" ");
                String test = separated[0];
                String last = separated[separated.length - 1];
                empEnName = test + " " + last;
                Constants.SaveDataInSharedPrefrences(Constants.ENG_NAME, empEnName, mContext);
                byte[] decodedString = Base64.decode(String.valueOf(jsonObject.getPhoto()), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                profImage = (CircleImageView) findViewById(R.id.profile_image);

                profImage.setImageBitmap(decodedByte);

                Constants.SaveDataInSharedPrefrences(Constants.USER_PHOTO, mProfileImage, mContext);

            }


        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
    }


}
