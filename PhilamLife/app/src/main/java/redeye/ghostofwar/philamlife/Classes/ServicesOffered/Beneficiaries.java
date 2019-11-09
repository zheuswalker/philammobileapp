package redeye.ghostofwar.philamlife.Classes.ServicesOffered;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_wallet;
import redeye.ghostofwar.philamlife.Classes.Wallet.PayPalPaymentAct;
import redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_adapter;
import redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_constructors;
import redeye.ghostofwar.philamlife.R;

public class Beneficiaries extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiaries);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        name= preferences.getString("session", "");
        Button apply = findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new getServices(getApplicationContext()).execute(name);
            }
        });

    }

    public class deductwallet extends AsyncTask<String, Void, String> {
        Context ctx;
        deductwallet(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = Base.BASE_URL+ EndPoints.INSERTWALLET;

            String data ="";
            String email = params[0];
            String money = params[1];
            String processtype = params[2];
            String servicefee = params[3];

            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String name = preferences.getString("session", "");

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("money","UTF-8")+"="+ URLEncoder.encode(money,"UTF-8")+"&"+
                        URLEncoder.encode("processtype","UTF-8")+"="+ URLEncoder.encode(processtype,"UTF-8")+"&"+
                        URLEncoder.encode("servicefee","UTF-8")+"="+ URLEncoder.encode(servicefee,"UTF-8")
                ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d("resulwallet",result);
            if(result.trim().equals("true"))
                Toast.makeText(ctx, "Successfully deducted from wallet.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ctx, "Cashin was unsuccessful , but no money is deducted to your account.", Toast.LENGTH_SHORT).show();






        }



    }

    public class getServices extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        getServices(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = Base.BASE_URL+ EndPoints.WALLETHISTORY;

            String data ="";

            String email = params[0];
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8");
                ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            JSONObject feedcontentvalues = null;

            try {

                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("wallethistory");

                    Double walletpoints=0.00;
                    Double cashin = 0.00;
                    Double cashout = 0.00;
                    for (int i=0; i < feedvalues.length(); i++)
                    {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String pw_processedmoney = feedarray.getString("pw_processedmoney").trim();
                        String pw_processtype = feedarray.getString("pw_processtype").trim();
                        String pw_processdate = feedarray.getString("pw_processdate").trim();
                        String pw_processfee = feedarray.getString("pw_processfee").trim();
                        if(pw_processtype.equals("0"))
                            cashin+=Double.parseDouble(pw_processedmoney);
                        else
                            cashout+= Double.parseDouble(pw_processedmoney);




                    }

                    walletpoints = cashin-cashout;

                    final Dialog dialogs = new Dialog(Beneficiaries.this);
                    dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogs.setCancelable(false);
                    dialogs.setContentView(R.layout.dialogpayment);
                    Button wallet= dialogs.findViewById(R.id.wallet);
                    wallet.setText("Available Wallet Money (P"+walletpoints+")");
                    if(walletpoints<Double.parseDouble(getIntent().getStringExtra("fee")))
                        wallet.setEnabled(false);
                    wallet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new deductwallet(getApplicationContext()).execute(name,getIntent().getStringExtra("fee"),
                                    "1","10");
                            dialogs.dismiss();
                        }
                    });

                    TextView fee = dialogs.findViewById(R.id.fee);
                    fee.setText(getIntent().getStringExtra("fee"));
                    Button paypal = dialogs.findViewById(R.id.paypal);
                    paypal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getApplicationContext(), PayPalPaymentAct.class);


                            intent.putExtra("email",name);
                            intent.putExtra("processtype","0");
                            intent.putExtra("cashinprice",getIntent().getStringExtra("fee"));
                            intent.putExtra("paymicro","true");
                            startActivity(intent);
                        }
                    });
                    dialogs.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }
}
