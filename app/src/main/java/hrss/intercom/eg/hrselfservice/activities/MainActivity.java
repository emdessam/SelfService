package hrss.intercom.eg.hrselfservice.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import hrss.intercom.eg.hrselfservice.R;
import hrss.intercom.eg.hrselfservice.activities.add.NewCompensationActivity;
import hrss.intercom.eg.hrselfservice.activities.add.NewMissionActivity;
import hrss.intercom.eg.hrselfservice.activities.add.NewPermissionActivity;
import hrss.intercom.eg.hrselfservice.api.APIListener;
import hrss.intercom.eg.hrselfservice.api.ServerConfig;
import hrss.intercom.eg.hrselfservice.helpers.Constants;
import hrss.intercom.eg.hrselfservice.helpers.Log;
import hrss.intercom.eg.hrselfservice.helpers.Utility;
import hrss.intercom.eg.hrselfservice.retrofit.RerofitInterceptor;
import hrss.intercom.eg.hrselfservice.retrofit.RetrofitAsynTask;
import okhttp3.OkHttpClient;

/**
 * Created by Emad.Essam on 9/2/2016.
 */


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener, View.OnClickListener, APIListener {

    public JSONObject resultObject = null;
    public String openingBalance;
    public String accumulatedDays;
    public String consumed;
    public String Signin;
    public String Signout;
    public String role;
    String mProfileImage;

    private RecyclerView.LayoutManager layoutManager;
    public String gender;
    private ShowcaseView showcaseView;
    public String TOKEN;
    public static final String ARG_USE_EXPANSION = "arg_use_expansion";
    public static final String ARG_EXPANSION_LEFT_OFFSET = "arg_left_offset";
    public static final String ARG_EXPANSION_TOP_OFFSET = "arg_top_offset";
    public static final String ARG_EXPANSION_VIEW_WIDTH = "arg_view_width";
    public static final String ARG_EXPANSION_VIEW_HEIGHT = "arg_view_height";
    float t; // max number of vac
    float r; //num of vac
    float d = 0.0f;//percentage
    FloatingActionButton profile;
    private BoomMenuButton boomMenuButton;
    TextView login;
    int counter = 0;
    TextView logout;
    public Float allBalance;
    TextView textPercentage;
    TextView ratio;
    private Context mContext;


    float f;
    ImageView ivDrawable;
    CircularProgressDrawable drawable;
    private boolean isInit = false;
    Animator currentAnimation;

    void CalcBalance() {
        r = Float.parseFloat(consumed);
        t = Float.parseFloat(String.valueOf(allBalance));
        Log.e("test total", String.valueOf(t));
        f = t - r;

        if (r <= t) {
            d = f * 100 / t;

            textPercentage.setText(f + "");
            ratio.setText("/" + t);
        } else {
            f = t - t;
            textPercentage.setText(f + "");
            d = f * 100 / t;

            ratio.setText("/" + t);

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        role = Constants.getDataInSharedPrefrences(Constants.ROLE, mContext);
        gender = Constants.getDataInSharedPrefrences(Constants.GENDER, mContext);

        login = (TextView) findViewById(R.id.login_time);
        logout = (TextView) findViewById(R.id.logout_time);

        Bundle bundle = getIntent().getExtras();
        Signin = bundle.getString("in");
        Signout = bundle.getString("out");
        login.setText(Signin);
        logout.setText(Signout);
        Log.e("signout33", Signout);

        Log.e("role33", role);
        profile = (FloatingActionButton) findViewById(R.id.manage);
        profile.setVisibility(View.INVISIBLE);

        mContext = this;

        setSupportActionBar(toolbar);
//        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
        boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);
// textView is the TextView view that should display it
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(i);
            }
        });

        ivDrawable = (ImageView) findViewById(R.id.iv_drawable);
        showcaseView = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setStyle(R.style.ShowcaseView_Light)
                .singleShot(22)
                .setContentText(R.string.showcase_fragment_message)
                .setContentTitle(R.string.showcase_fragment_title)
                .setTarget(new ViewTarget(findViewById(R.id.iv_drawable)))
                .setOnClickListener(this)
                .build();
        showcaseView.setButtonText(getString(R.string.next));
//       new ShowcaseView.Builder(this)
//                .withMaterialShowcase()
//                .setStyle(R.style.CustomShowcaseTheme)
//                .setTarget(new ViewTarget(findViewById(R.id.boom)))
//                .hideOnTouchOutside()
//                .singleShot(11)
//                .setContentTitle(R.string.showcase_fragment_title)
//                .setContentText(R.string.showcase_fragment_message)
//                .setShowcaseEventListener(new SimpleShowcaseEventListener() {
//
//                    @Override
//                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
//                        initViews();
//                    }
//
//                })
//                .build();
        textPercentage = (TextView) findViewById(R.id.textView);
        ratio = (TextView) findViewById(R.id.textView2);


        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(50)
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(android.R.color.holo_green_light))
                .create();
        ivDrawable.setImageDrawable(drawable);

//        hookUpListeners();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();

        getBalance();

        if (role.equalsIgnoreCase("E")) {
            profile.setVisibility(View.INVISIBLE);


        } else if (role.equalsIgnoreCase("M")) {
            profile.setVisibility(View.VISIBLE);

        } else {
            profile.setVisibility(View.VISIBLE);

        }

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                getProfileDetails();
//                String empEnName = null;
//                String image= null;
//                try {
//                    empEnName = String.valueOf(resultObject.get("empEnName"));
//                    image = String.valueOf(resultObject.get("photo"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String[] separated = empEnName.split(" ");
//                String test = separated[0];
//                String last = separated[separated.length-1];
//                empEnName = test+" "+last;
//                Constants.SaveDataInSharedPrefrences(Constants.ENG_NAME,empEnName,mContext);
//                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//                mProfileImage= String.valueOf(decodedByte);
//
//                Constants.SaveDataInSharedPrefrences(Constants.USER_PHOTO,mProfileImage,mContext);
//
//            }
//        });
//
//
//        t.start();


    }

//    public void getProfileDetails(){
//        android.util.Log.e("eweweeeeeeeeeee",Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
//        Utility.showProgressDialog(MainActivity.this, getString(R.string.Loading));
//
//
//        Map<String, String> mRetrofitHeader = new HashMap<>();
//        String TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);
//
//
//        mRetrofitHeader.put("token",TOKEN);
//        Utility.generateRetrofitHttpHeader(this);
////        resultObject=new JSONObject(response);
////
////        String mResult= resultObject.getString("result");
////        if(mResult.equalsIgnoreCase("1")){
////            openingBalance= resultObject.getString("openingBalance");
////            balance= resultObject.getString("balance");
//
////    Map<String, String> mNewHeader = new HashMap<>();
////
////    mNewHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext));
//        Constants.httpClient = new OkHttpClient.Builder();
//
//        Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader,mContext));
//
//        new RetrofitAsynTask(0, ServerConfig.PROFILE_DETAIL, ServerConfig.METHOD_GET,mRetrofitHeader, null
//                , this, this).execute();
//
//    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!isInit) {
            initBoom();
//            initInfoBoom();
        }
        isInit = true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
        } else if (id == R.id.nav_vacation) {
            Intent i = new Intent(MainActivity.this, VacationActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_compensation) {
            Intent i = new Intent(MainActivity.this, CompensationDaysActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_permission) {
            Intent i = new Intent(MainActivity.this, PermissionActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_mission) {
            Intent i = new Intent(MainActivity.this, MissionActivity.class);
            startActivity(i);
        } else if (id == R.id.extension) {

            Intent i = new Intent(MainActivity.this, ExtensionNumbActivity.class);
            startActivity(i);
        } else if (id == R.id.Log_out) {


            Intent i = new Intent(MainActivity.this, LoginActivity.class);

            startActivity(i);

            finish();


        }
//        else if (id == R.id.profile_View) {
//            Intent i = new Intent(MainActivity.this, PersonActivity.class);
//            startActivity(i);
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private Animator prepareStyle2Animation() {
        AnimatorSet animation = new AnimatorSet();


//        String s = d.getText().toString();
//        float fl = Float.parseFloat(s);
        float fls = d / 100;
        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY,
                0f, fls);
        progressAnimation.setDuration(1200);
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        if (d <= 25f) {
            ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                    getResources().getColor(android.R.color.holo_red_dark));
            textPercentage.setTextColor(Color.parseColor("#ffcc0000"));

            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.setDuration(1200);
            animation.playTogether(progressAnimation, colorAnimator);

        } else if (d > 25f && d <= 50f) {
            ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                    getResources().getColor(android.R.color.holo_red_dark),
                    getResources().getColor(android.R.color.holo_red_light),
                    getResources().getColor(android.R.color.holo_orange_dark));
            textPercentage.setTextColor(Color.parseColor("#ffff8800"));

            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.setDuration(1200);
            animation.playTogether(progressAnimation, colorAnimator);
        } else if (d > 50f && d <= 75f) {
            ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                    getResources().getColor(android.R.color.holo_red_dark),
                    getResources().getColor(android.R.color.holo_red_light),
                    getResources().getColor(android.R.color.holo_orange_dark),
                    getResources().getColor(android.R.color.holo_orange_light),
                    getResources().getColor(android.R.color.holo_green_light)
            );
            textPercentage.setTextColor(Color.parseColor("#ff99cc00"));

            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.setDuration(1200);
            animation.playTogether(progressAnimation, colorAnimator);
        } else if (d > 75f && d <= 100f) {
            ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, CircularProgressDrawable.RING_COLOR_PROPERTY,
                    getResources().getColor(android.R.color.holo_red_dark),
                    getResources().getColor(android.R.color.holo_red_light),
                    getResources().getColor(android.R.color.holo_orange_dark),
                    getResources().getColor(android.R.color.holo_orange_light),
                    getResources().getColor(android.R.color.holo_green_light),
                    getResources().getColor(android.R.color.holo_green_dark));

            textPercentage.setTextColor(Color.parseColor("#ff669900"));
            colorAnimator.setEvaluator(new ArgbEvaluator());
            colorAnimator.setDuration(1200);
            animation.playTogether(progressAnimation, colorAnimator);
        }


        return animation;
    }

    //    public void showDate (View view){
//        DateDialog dateDialog = new DateDialog();
//
//        dateDialog.show(getSupportFragmentManager(),"Date");
//
//
//    }
    @Override
    public void toShow() {
        setProgress(0);
//        animationListener.setProgress(0);
    }

    @Override
    public void showing(float fraction) {
        setProgress((int) (fraction * 100));
    }

    @Override
    public void showed() {
        setProgress(100);
    }

    @Override
    public void toHide() {
        setProgress(100);
    }

    @Override
    public void hiding(float fraction) {
        setProgress((int) ((1 - fraction) * 100));
    }

    @Override
    public void hided() {
        setProgress(0);
    }


    public void onClick(int buttonIndex) {

        switch (buttonIndex) {
            case 0:
                Intent i = new Intent(this, NewCompensationActivity.class);
                startActivity(i);


                break;
            case 1:
                Intent intent = new Intent(this, NewMissionActivity.class);

                startActivity(intent);
                break;
            case 2:
                Intent intt = new Intent(this, NewPermissionActivity.class);


                startActivity(intt);
                break;
            case 3:
                Intent intet = new Intent(this, VacationCategoryActivity.class);
                startActivity(intet);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                showcaseView.setShowcase(new ViewTarget(login), true);
                showcaseView.setContentTitle("This is your sign in time ");


                break;

            case 1:
                showcaseView.setShowcase(new ViewTarget(logout), true);
                showcaseView.setContentTitle("This is your sign out time ");

                break;
            case 2:
                showcaseView.setShowcase(new ViewTarget(boomMenuButton), true);
                showcaseView.setContentTitle("This is shortcuts for any new request ");

                break;
            case 3:
                showcaseView.setShowcase(new ViewTarget(profile), true);
                showcaseView.setContentTitle("Click here to view your profile");

                break;
            case 4:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("Check it out");

                showcaseView.setContentText("IF you need to show this again clear app cache!");
                showcaseView.setButtonText(getString(R.string.close));
                setAlpha(0.4f, ivDrawable, login, logout);
                break;

            case 5:
                showcaseView.hide();
                setAlpha(1.0f, ivDrawable, login, logout);
                break;
        }
        counter++;
    }

    private void initBoom() {
        int number = 4;

//                buttonNumberSeek.getProgress() + 1;

        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.drawable.ic_compensation,
                R.drawable.ic_mission,
                R.drawable.ic_permission,
                R.drawable.ic_vacation

        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(mContext, drawablesResource[i]);

        String[] STRINGS = new String[]{
                " ",
                " ",
                " ",
                " "
        };
        String[] strings = new String[number];
        for (int i = 0; i < number; i++)
            strings[i] = STRINGS[i];

        int[][] colors = new int[number][2];
        for (int i = 0; i < number; i++) {
            colors[i][1] = GetRandomColor();

            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        ButtonType buttonType = ButtonType.CIRCLE;
//        switch (buttonTypeGroup.getCheckedRadioButtonId()) {
//            case R.id.circle_button:
//                buttonType = ButtonType.CIRCLE;
//                break;
//            case R.id.hamburger_button:
//                buttonType = ButtonType.HAM;
//                break;
//        }

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .subButtons(drawables, colors, strings)
                .button(buttonType)
                .boom(getBoomType())
                .place(getPlaceType())
                .boomButtonShadow(Util.getInstance().dp2px(4), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(4), Util.getInstance().dp2px(2))
                .onSubButtonClick(this)
                .animator(this)
                .init(boomMenuButton);
//
//        // Now with Builder, you can init BMB more convenient
//        new BoomMenuButton.Builder()
//                .subButtons(drawables, colors, strings)
//                .button(buttonType)
//                .boom(getBoomType())
//                .place(getPlaceType())
//                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
//                .onSubButtonClick(this)
//                .animator(this)
//                .dim(DimType.DIM_0)
//                .init(boomMenuButtonInActionBar);
    }

    private BoomType getBoomType() {

        return BoomType.HORIZONTAL_THROW;
    }

    private PlaceType getPlaceType() {

        return PlaceType.CIRCLE_4_2;

    }

//    private int []mColors= {
//
//           getResources().getColor(R.color.color_one),
//            getResources().getColor(R.color.color_two),
//            getResources().getColor(R.color.color_three),
//            getResources().    getColor(R.color.color_four),
//    };

    private String[] Colors = {

            "#fc1100",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#25fc2e",
            "#CDDC39",
            "#FFC107",
            "#ff7f00",
            "#795548",
            "#607D8B"};

    public int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);

        return Color.parseColor(Colors[p]);
    }

    private void initViews() {
        initBoom();
        boomMenuButton.setBoomButtonBackgroundColor(R.color.colorPrimary, R.color.colorPrimaryDark);
        boomMenuButton.setShowOrderType(OrderType.DEFAULT);

        boomMenuButton.setHideOrderType(OrderType.DEFAULT);

    }

    public void getBalance() {


        Utility.showProgressDialog(MainActivity.this, getString(R.string.Loading));


//        Map<String, String> mRetrofitHeader = new HashMap<>();
//        TOKEN = Constants.getDataInSharedPrefrences(Constants.USER_TOKEN,mContext);
//
//
//        mRetrofitHeader.put("token",TOKEN);
        Utility.generateRetrofitHttpHeader(this);
//        resultObject=new JSONObject(response);
//
//        String mResult= resultObject.getString("result");
//        if(mResult.equalsIgnoreCase("1")){
//            openingBalance= resultObject.getString("openingBalance");
//            balance= resultObject.getString("balance");

        Map<String, String> mNewHeader = new HashMap<>();

        mNewHeader.put("token", Constants.getDataInSharedPrefrences(Constants.USER_TOKEN, mContext));
        Constants.httpClient = new OkHttpClient.Builder();

        Constants.httpClient.addInterceptor(new RerofitInterceptor(mNewHeader, mContext));

        new RetrofitAsynTask(0, ServerConfig.VAC_BALANCE, ServerConfig.METHOD_GET, mNewHeader, null
                , this, this).execute();
    }

    @Override
    public void onSuccess(int id, String url, String response) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();
        Log.e("Response in main", response + "gg");
        try {
            resultObject = new JSONObject(response);

            String mResult = resultObject.getString("result");
            if (mResult.equalsIgnoreCase("1")) {

                openingBalance = resultObject.getString("openingBalance");
                accumulatedDays = resultObject.getString("accumulatedDays");
                Float opening = Float.parseFloat(openingBalance);
                Float all = Float.parseFloat(accumulatedDays);
                allBalance = all + opening;
                consumed = resultObject.getString("consumed");
                //     Constants.SaveDataInSharedPrefrences(Constants.USER_TOKEN,null,mContext);
                CalcBalance();
                currentAnimation = prepareStyle2Animation();
                currentAnimation.start();


            }


        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {
        if (Utility.isProgressDialogShowing())
            Utility.removeProgressDialog();


    }

    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

}
