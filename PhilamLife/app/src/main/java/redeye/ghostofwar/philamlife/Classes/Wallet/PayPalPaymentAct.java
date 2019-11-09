package redeye.ghostofwar.philamlife.Classes.Wallet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

import redeye.ghostofwar.philamlife.Classes.Activity.overall_home_activity;
import redeye.ghostofwar.philamlife.R;


public class PayPalPaymentAct extends Activity implements OnClickListener {

    /*
     * - Set to PaymentActivity.ENVIRONMENT_PRODUCTION to move real money.
     *
     * - Set to PaymentActivity.ENVIRONMENT_SANDBOX to use your test credentials from
     * https://developer.paypal.com
     *
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires without communicating
     * to PayPal's servers.
     */
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;

    // note that these credentials will differ between live & sandbox environments.


    private static final String CONFIG_CLIENT_ID = "AYOtLSfEHEDpiCCJa-I-72-lvMlLO5I3yqNUB9glD4jTk_xh1Dd_GsgPE7KlbeHOsC_hRAm5eUIlOH9S";        //Live Id


    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;

    private ProgressDialog dialog;

    private static PayPalConfiguration config;


    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay_pal_payment);


        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID)
                // The following are only used in PayPalFuturePaymentActivity.
                .merchantName("Philam")
                .languageOrLocale("english")
                    .acceptCreditCards(true)
                ;
        //.merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
        //.merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));



        Intent intent = new Intent(getApplicationContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        ConvertedPHP = (Double.parseDouble(getIntent().getStringExtra("cashinprice").replaceAll(",","")) + 20.00) / 50;
        ConvertedPHP = new BigDecimal(ConvertedPHP).setScale(2, RoundingMode.HALF_UP).doubleValue();

        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(String.valueOf(
                ConvertedPHP

        )), "USD", "P "+getIntent().getStringExtra("cashinprice"),
                PayPalPayment.PAYMENT_INTENT_AUTHORIZE);
        thingToBuy.invoiceNumber(InvoiceNumber);
        thingToBuy.softDescriptor("Cashin");
        intent = new Intent(PayPalPaymentAct.this, PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);



    }

    String InvoiceNumber ,transactid;
    Double ConvertedPHP = 0.00 ;
    public void onFuturePaymentPressed(View pressed) {
        Intent intent = new Intent(PayPalPaymentAct.this, PayPalFuturePaymentActivity.class);

        startActivityForResult(intent, REQUEST_CODE_FUTURE_PAYMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data
                        .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {


                    Toast.makeText(context, "Cash In Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), overall_home_activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    Log.d("paymentdetails", confirm.toString());
                    try {
                        JSONObject jObj =  new JSONObject(confirm.toJSONObject().toString(4));
                        JSONObject payObjects = jObj.getJSONObject("response");
                        transactid = payObjects.getString("id");
                        Log.d("Pay JSON",confirm.toJSONObject().toString(4));
                        Log.d("Pay Proof : ", String.valueOf(confirm.getProofOfPayment().toJSONObject()));
                        Log.d("Pay Confirm : ", String.valueOf(confirm.getPayment().toJSONObject()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else
                    finish();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
                finish();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment was submitted. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth = data
                        .getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("FuturePaymentExample", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("FuturePaymentExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        Toast.makeText(getApplicationContext(), "Future Payment code received from PayPal",
                                Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        Log.e("FuturePaymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("FuturePaymentExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("FuturePaymentExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }

        }
    }

    private void sendAuthorizationToServer(PayPalAuthorization authorization) {

        // TODO:
        // Send the authorization response to your server, where it can exchange the authorization code
        // for OAuth access and refresh tokens.
        //
        // Your server must then store these tokens, so that your server code can execute payments
        // for this user in the future.

    }

    public void onFuturePaymentPurchasePressed(View pressed) {
        // Get the Application Correlation ID from the SDK
        String correlationId = PayPalConfiguration.getApplicationCorrelationId(this);

        Log.i("FuturePaymentExample", "Application Correlation ID: " + correlationId);

        // TODO: Send correlationId and transaction details to your server for processing with
        // PayPal...
        Toast.makeText(getApplicationContext(), "App Correlation ID received from SDK",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

    }

}
