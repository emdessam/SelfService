package hrss.intercom.eg.hrselfservice.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import okhttp3.OkHttpClient;


/**
 * Created by Emad.Essam on 10/11/2015.
 */
public class Constants {

    //
    public static final String Demo = "demo";

    public static final String KEY_TEXT = "Text";
    public static final String KEY_CHOICES = "Choices";
    public static final String KEY_SELECTION = "selection";
    public static final String KEY_O = "O";
    public static final String KEY_R = "R";
    public static final String KEY_TYPE = "Type";
    public static final String KEY_STATE = "State";
    public static final String KEY_CUSTOMER_SURVEY = "customerSurvey";
    public static final String KEY_QUESTION_HEADER = "QuestionHeader";
    public static final String KEY_ID = "ID";
    public static final String KEY_ANSWER = "Answer";
    public static final String KEY_ANSWERS = "Answers";
    public static final String KEY_SELECTION_INDEX = "selectionIndex";
    public static final String KEY_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_SLASH_JSON = "application/json";


    public static final String DEAL_NUMBER = "dealNumber";
    public static final String INSTRUCTION_TYPE = "instructionType";
    public static final String CANCEL = "cancel";
    public static final String INSTRUCTION_OPTION_ID = "instructionOptionId";
    public static final String CHANGED_AMOUNT = "changedAmount";
    public static final String NEW_DURATION = "newDuration";
    public static final String DURATION_UNIT_v2 = "durationUnit";
    public static final String M = "M";
    public static final String PAYMENT_INSTRUCTION = "paymentInstruction";
    public static final String RENEW = "Renew";
    public static final String ACCOUNT_TYPE_ID = "account_type_id";
    public static final String ACCOUNT_TYPE_ID_V2 = "accountTypeId";


    public static final String CARD = "card";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CARD_KIND = "cardKind";
    public static final String TIME = "time";
    public static final String LOCATION = "location";
    public static final String NUMBER_OF_CHEQUES = "numberOfCheques";
    public static final String DELIVERY_OPTION = "deliveryOption";
    public static final String RECEIVING_BRANCH_CODE = "receivingBranchCode";


    //CUSTOMER SURVEY
    public static final String SINGLE_SELECTION = "Single Selection";  //viewType=0
    public static final String ENTRY_TEXT = "Entry Text";              //viewType=1
    public static final String REQUIRED = "Required";
    public static final String OPTIONAL = "Optional";
    public static final String ANSWERS_LIST_LENGTH = "answersListLength";

    //AccountInterestRateInquiryActivity
    public static final String CURRENCY_CODE_V1 = "currencyCode";
    public static final String CURRENCY_CODE_V2 = "currency_code";
    public static final String ACCOUNT_TYPE_CODE_V1 = "accountTypeCode";
    public static final String ACCOUNT_TYPE_CODE_V2 = "account_type_code";
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String RESULT = "result";
    public static final String ACCOUNT_RATES = "account_rates";
    public static final String RATE = "rate";
    public static final String DURATION = "duration";
    public static final String DURATION_UNIT = "duration_unit";
    public static final String HUNDRED_PERCENTAGE = " % ";
    public static final String SPACE = " ";
    public static final String EMPTY_TEXT = "";
    public static final String DOT = ".";
    public static final String ZERO_DOT_ZERO_ZERO = "0.00";
    public static final String ACCOUNTS = "accounts";
    public static final String CATEGORIES = "categories";
    public static final String ACCOUNT_NAME = "account_name";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CURRENCIES = "currencies";
    public static final String CURRENCY_ID = "currency_id";
    public static final String CURRENCY_NAME = "currency_name";

    //AccountNotificationsSubscriptionExecuteActivity
    public static final String PUSH_NOTIFICATION_1 = "push_notification";
    public static final String PUSH_NOTIFICATION_V2 = "pushNotification";
    public static final String DAYS = "days";
    public static final String TRUE = "true";
    public static final String SELECTED_ACCOUNT = "selectedAccount";
    public static final String SELECTED_ALERT = "selected_alert";
    public static final String ACCOUNT_NUMBER = "accountNumber";
    public static final String CHECKED = "checked";
    public static final String ALERT_SUBSCRIPTION_ID = "alertSubscribtionId";
    public static final String ALERT_TYPE_ID = "alertTypeId";
    public static final String FREQUENCY_ID = "frequencyId";
    public static final String EMAILS = "emails";
    public static final String MOBILES = "mobiles";
    public static final String UTF_8 = "UTF-8";
    public static final String APOSTROPHE = "'";
    public static final String MSG = "msg";
    public static final String Account_Notifications_Subscription_Execute_PAGE = "AccountNotificationsSubscriptionExecute Page";
    public static final String HOST_PATH = "http://host/path";
    public static final String APP_HOST_PATH = "android-app://tub.banknet.intercom.eg.banknetmobile.activities/http/host/path";
    //SafetynetUtility
    public static final String SHA_256 = "SHA-256";
    public static final String SHA1 = "SHA1";
    public static final String X509 = "X509";

    //SafetyNetResponse
    public static final String NONCE = "nonce";
    public static final String APK_CERTIFICATE_DIGEST_SHA_256 = "apkCertificateDigestSha256";
    public static final String APK_DIGEST_SHA_256 = "apkDigestSha256";
    public static final String APK_PACKAGE_NAME = "apkPackageName";
    public static final String CTS_PROFILE_MATCH = "ctsProfileMatch";
    public static final String TIME_STAMP_MS = "timestampMs";

    //SafetyNetHelper
    public static final String SLASH_SLASH_DOT = "\\.";
    public static final String GOOGLE_PLAY_SERVICES_CONNECTION_FAILED = "Google Play services connection failed";

    //GoogleApisTrustManager
    public static final String GOOGLEAPIS_COM_PINS_0 = "sha1/f2QjSla9GtnwpqhqreDLIkQNFu8=";
    public static final String GOOGLEAPIS_COM_PINS_1 = "sha1/Q9rWMO5T+KmAym79hfRqo3mQ4Oo=";
    public static final String GOOGLEAPIS_COM_PINS_2 = "sha1/wHqYaI2J+6sFZAwRfap9ZbjKzE4=";
    public static final String COULD_NOT_FIND_SSL_IN_GOOGLEAPIS = "could not find a valid SSL public key pin for www.googleapis.com";
    public static final String SHA1_SLASH = "sha1/";

    //HEADERS
    public static final String ChANNEL = "ch";
    public static final String LANG = "lang";
    public static final String AUTH = "Authorization";
    public static String Cuurent_LANG = "cuurent_local";
    public static final String LOCALE = "locale";
    public static final String LANG_EN = "en";
    public static final String LANG_AR = "ar";
    public static final String USER_TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final String USER_PHOTO = "photo";

    public static final String PASSWORD = "password";
    public static final String ENG_NAME = "empEnShortName";
    public static final String EMP_NAME = "empName";
    public static final String MGR_CODE = "mgrCode";
    public static final String GENDER = "gender";
    public static final String ROLE = "role";

    public static final String MGR_NAME = "mgrName";

    public static final String HRSS = "HRSS";

    //ArFloatLabeledEditText
    public static final String NEW_API = "NewApi";
    public static final String CAN_ONLY_HAVE_ONE_EDIT_TEXT_SUBVIEW = "Can only have one Edittext subview";
    public static final String ALPHA = "alpha";
    public static final String TRANSLATION_Y = "translationY";

    //MapsConfigurations
    public static final String UNUSED = "unused";
    public static final String COMMA = ",";
    public static final String DESTINATION_EQUAL = "destination=";
    public static final String ORIGIN_EQUAL = "origin=";
    public static final String SENSOR_EQUAL_FALSE = "sensor=false";
    public static final String AND = "&";
    public static final String JSON = "json";
    public static final String QUESTION_MARK = "?";
    public static final String MAPS_URL = "https://maps.googleapis.com/maps/api/directions/";

    //TYPE_FACES
    public static final String TYPE_FACE = "Roboto-Bold.ttf";
    public static final String TYPE_FACE_MEDIUM = "Roboto-Medium.ttf";
    public static final String TYPE_FACE_REGULAR = "Roboto-Regular.ttf";
    public static final String TYPE_FACE_LIGHT = "Roboto-Light.ttf";

    //MyBranchInfoWindowAdaptor
    public static final String SPACE_DASH_SPACE = " - ";
    public static final String NULL = "null";
    public static final String BUTTON_CLICKED = "'s button clicked!";

    //SeparatedListAdapter
    public static final String COUNTRY = "country";
    public static final String REGION_ATM = "RegionAtm";
    public static final String FLAG = "flag";
    public static final String MANY_ATM = "ManyATM";
    public static final String REGION_BRANCH = "RegionBranch";
    public static final String MANY_BRANCHES = "ManyBranches";
    public static final String M_REGION = "mRegion";

    //Service_Fragment
    public static final String SELECTED_ACCOUNT_V2 = "selected_account";
    public static final String ACCOUNT_NO = "accountNo";
    public static final String ACCOUNT_NICK_NAME = "accountNickName";
    public static final String CHANGE_TYPE = "CHANGE_TYPE";
    public static final String FALSE = "FALSE";
    public static final String SERVICE_FRAGMENT_V2 = "ServiceFragment";

    //Transaction_Fragment
    public static final String ACCOUNT_NO_V2 = "AccountNO";

    //AccountNotificationsSubscriptionPreActivity
    public static final String ACNT_IDENTIFIER = "acnt_identifier";
    public static final String ACNT_NICKNAME = "acnt_nickname";
    public static final String ACNT_TYPE = "acnt_type";
    public static final String BRANCH_NAME = "branchName";
    public static final String ACNT_BALANCE = "acnt_balance";
    public static final String ACNT_CURRENCY = "acnt_currency";
    public static final String ACNT_BALANCE_TYPE = "acnt_balanceType";

    //AccountNotificationsSubscriptionTypeActivity
    public static final String ALERTS = "alerts";
    public static final String ACTIVE = "active";
    public static final String ALERT_DESCRIPTION = "alertDescription";
    public static final String ALERT_ID = "alertId";
    public static final String ALERT_NAME = "alertName";
    public static final String ALERT_SUBSCRIPTION_ID_v2 = "alertSubscriptionId";
    public static final String SUBSCRIBED = "subscribed";
    public static final String FREQUENCIES = "frequencies";
    public static final String FREQUENCY_NAME = "frequencyName";
    public static final String MOBILE = "mobile";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    //AccountServiceActivity
    public static final String ACCOUNT_SERVICE_ACTIVITY = "AccountServiceActivity";

    //AccountStatementActivity
    public static final String ACCOUNT = "account";
    public static final String DOWNLOAD = "download";
    public static final String REF_ID = "refId";
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ACCOUNT_STATEMENT_PDF = "accountStatementPDF";
    public static final String APPLICATION_SLASH_PDF = "application/pdf";
    public static final String SLASH_ACCOUNT_STATEMENT_PDF = "/accountStatementPDF";
    public static final String DOT_PDF = ".pdf";


    public static OkHttpClient.Builder httpClient = null;
    public static String SERVICE_FRAGMENT = "serviceFragment";
    public static String FROM = "from";
    public static String MY_ACCOUNT = "myAccount";
    public static String MY_CARD_ACCOUNT = "myCardAccount";
    public static String FROM_SUB_CREDIT_CARD_SERVICE_FRAGMENT = "fromSubCreditCardServiceFragment";
    public static boolean IsNearestEnable = false;
    public static final String LOG_TAG = "UNITEDBANK_LOG";
    public static String IS_USER_LOGIN = "isUserLoggedIn";
    public static String HAS_OTP_GENERATOR = "hasOTP";
    private static String regexStr = " ^0[0-9].*$";
    public static String USERNAME = "username";
    public static String ENCRYPTSECRETKEY = "PK";
    public static String APP_PROCESS_ID = "process_Id";
    //ERROR Messages
    public static final String ERROR_GENERAL = "GENERAL_ERROR";
    public static final String USER_MISSING = "USER_MISSING";
    public static final String TOKEN_MISSING = "TOKEN_MISSING";
    public static final String TOKEN_INVALID = "TOKEN_INVALID";
    public static final String AUTH_REQUIRED = "AUTH_REQUIRED";
    public static final String USER_PASSWORD_INVALID_KEY = "401";
    public static final String SERVER_HTTP_404 = "404";
    public static final String SERVER_IS_NOT_REACHED_KEY = "0";
    public static final String SERVER_ERROR_KEY = "500";
    public static final String CLIENT_ERROR_KEY = "400";
    public static final String NO_STATEMENTS_MESSAGE_KEY = "NoStatements";
    public static final String INSUFFICIENT_FUND_IN_CHARGE_ACCOUNT_KEY = "Insufficient_Funds_In_Charge_Account";
    public static final String CHEQUE_BOOK_REQUEST_ALREADY_EXIST_VALUE = "There is a pending request for same account number";
    public static final String CHEQUE_BOOK_REQUEST_ALREADY_EXIST_KEY = "REQUEST_ALREADY_EXIST";
    public static final String CHEQUE_BOOK_REQUEST_SERVICE_UNAVAILABLE_KEY = "0002";
    public static final String TAB_SELECTED = "tab_selected";
    public static final String TAB_ACCOUNT = "tab_account";
    public static final String TAB_CARD = "tab_card";
    public static final String TAB_ATM = "tab_atm";
    public static final String TAB_BRANCH = "tab_branch";
    public static final String TAB_ = "tab_";
    public static final String SERVER_HTTP_200 = "200";

    //Internal Fund Transfers
    public static final String KEY_TRANSFER_FROM = "fromAccount";
    public static final String KEY_TRANSFER_TO = "toAccount";
    public static final String KEY_AMOUNT = "amount";

    //Internal Fund Transfer Verify Error messages
    public static final String KEY_CURRENCY_NOT_ALLOWED = "CURRENCY_NOT_ALLOWED";
    public static final String KEY_NO_SUFFICIENT_BALANCE = "NotSufficientBalanceException";
    public static final String KEY_EXCEED_DAILY_MAXLIMIT = "EXCEED_DAILY_MAXLIMIT";
    public static final String KEY_ACCOUNT_CREDIT_BALANCE = "ACCOUNT_CREDIT_BALANCE";
    public static final String KEY_SAME_ACCOUNTS_EXCEPTION = "SAME_ACCOUNTS_EXCEPTION";
    public static final String KEY_SERVICE_TEMPORARY_UN_AVAILABLE = "0002";
    public static final String KEY_INVALID_CHARGE_ACCOUNT = "INVALID_CHARGE_ACCOUNT";
    public static final String KEY_INSUFFICIENT_CHARGE_ACCOUNT_BALANCE = "INSUFFICIENT_CHARGE_ACCOUNT_BALANCE";
    public static final String KEY_SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE";
    public static final String UNSUBSCRIBED_CHANNEL = "UNSUBSCRIBED_CHANNEL";

    //Internal Fund Transfer Execute Error Messages

    //Constants Card and Account Alerts

    public static final String AlertAccountTransactionId = "2";
    public static final String AlertAccountPeriodicBalanceId = "13";
    public static final String AlertCreditCardExpiryReminderId = "31";
    public static final String AlertCreditCardMinimumPaymentDueDateReminderId = "29";
    //public static final Strin NAlertCreditCardPeriodicPositionId; //not used yet - future enchancement
    public static final String AlertDebitCardExpiryReminderId = "26";
    public static final String AlertPrepaidCardExpiryReminderId = "37";

    //


    // Execute
    public static final String KEY_PartiallyCompletedReturnCode = "4001";
    public static final String KEY_ReversalFailedReturnCode = "4002";
    public static final String KEY_ReversalPartiallyFailedReturnCode = "4003";
    public static final String KEY_ReversalCompletedBENotCompletedCMSReturnCode = "4004";
    public static final String KEY_ReversalPartiallyCompletedBENotCompletedCMSReturnCode = "4005";
    public static final String KEY_ReversalTimeOutReturnCode = "4006";
    public static final String KEY_ReversalCompletedReturnCode = "4008";
    public static final String KEY_TransactionTimeOutReturnCode = "4007";
    public static final String KEY_TransactionPartiallyCompletedBEAndNotCompletedCMSReturnCode = "4009";
    public static final String KEY_EXP_MQ_TIME_OUT = "EXP_MQ_TIMEOUT";
    public static final String KEY_IN_PROGRESS = "InProgress";
    public static final String KEY_ALREADY_UNSUBSCRIBED_ACCOUNT = "ALREADY_UNSUBSCRIBED_ACCOUNT";
    public static final String KEY_ALREADY_UNSUBSCRIBED_CARD = "ALREADY_UNSUBSCRIBED_CARD";
    public static final String KEY_INVALID_EXPIRATION_DATE = "INVALID_EXPIRATION_DATE";
    public static final String KEY_INVALID_DUE_DATE = "INVALID_DUE_DATE";
    public static final String EXT_TRANSFER_ = "EXT_TRANSFER_USER_ACCOUNT";
    public static final String INVALID_ACCOUNT_NO_EXP = "InvalidAccountNumberException";
    //Cheque Book Request Error Message

    public static final String NO_SUITABLE_ACCOUNTS_FOR_CHEQUE_REQUEST_KEY = "NO_SUITABLE_ACCOUNTS_FOR_CHEQUE_REQUEST";
    public static final String INVALID_PROVIDED_OTP_KEY = "InvalidProvidedOTP";


    //Keys for Changing account name and Card Name

    public static final String IS_CARD_OR_ACCOUNT_NAME_CHANGED = "isCardOrAccountNameChanged";
    public static final String CHANGED_ACCOUNT_NAME = "changedAccountName";
    ///// keys for GCM Push Notification ///
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String GCM_TOKEN = "gcmToken";
    //Keys for notification alerts from customer position

    public static final String SUBSCRIBED_IN_NOTIFICATION_FROM_SERVICES_ACCOUNT = "account_service";
    public static final String SUBSCRIBED_IN_NOTIFICATION_FROM_SERVICES_CARDS = "card_service";
    public static boolean isHistoryShown = false;
    public static String RENEW_WITH_ORIGINAL_AMOUNT = "1";
    public static String RENEW_WITH_ORIGINAL_AMOUNT_AND_INTERSET = "2";
    public static String RENEW_WITH_INCREASE_BY = "3";
    public static String RENEW_WITH_DECREASE_BY = "4";
    public static String DEMO_ON = "1";
    public static String DEMO_OFF = "0";

    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            // ConnectivityManager connectivity = (ConnectivityManager) context
            // .getSystemService(Context.CONNECTIVITY_SERVICE);
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    public static boolean isEmpty(EditText et) {
        if (TextUtils.isEmpty(et.getText().toString())
                || TextUtils.isEmpty(et.getText().toString())) {
            return true;
        }
        return false;
    }

    public static boolean isRegexValid(EditText et) {
        if (Pattern.matches(regexStr, et.getText().toString())) {
            // if (mMobile_et.getText().toString().matches(regexStr)){
            return true;
        } else {

            return false;
        }
    }

//    public static String HandleResponseCode(int responseCode) {
//        String Usermessage = null;
//
//        switch (responseCode) {
//            case 0:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//            case 500:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//            case 400:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//            case 401:
//                Usermessage = MyApplication.getAppContext().getString(R.string.general_error);
//                break;
//
//        }


//        return Usermessage;
//
//
//    }

    public static void showErrorHandlingMessageLong(Context context, String msg, int time) {
        /*final Toast toast = Toast.makeText(context , msg , Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, time);*/


    }


    public static void showSecurityDialog(String title, String msg, String buttonMsg, final Activity activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, buttonMsg,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        Utility.ClearSharedPrefrence();
                        activity.finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }
                });
        alertDialog.show();

    }

    public static void SaveDataInSharedPrefrences(String mkey, String mValue, Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.HRSS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mkey, mValue);
        editor.commit();

    }

    public static String getDataInSharedPrefrences(String mkey, Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.HRSS, Context.MODE_PRIVATE);
        return sharedPref.getString(mkey, "");


    }

    public static void showAlertDialog(String title, String msg, String buttonMsg, final Activity currentActivity, final Class backActivity) {
        AlertDialog alertDialog = new AlertDialog.Builder(currentActivity).create();
    }
}