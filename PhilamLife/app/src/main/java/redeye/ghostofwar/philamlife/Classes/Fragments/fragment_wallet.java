package redeye.ghostofwar.philamlife.Classes.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_adapter;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_constructors;
import redeye.ghostofwar.philamlife.Classes.Landing.chooser;
import redeye.ghostofwar.philamlife.Classes.Wallet.PayPalPaymentAct;
import redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_adapter;
import redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_constructors;
import redeye.ghostofwar.philamlife.R;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_wallet extends Fragment  {


    public static RecyclerView recyclerView;
    public  static redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_adapter wallet_cashprocess_adapter;
    public  static List<redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_constructors> wallet_cashprocess_constructors =new ArrayList<>();

    CardView cashin;
    Context context;
    TextView walletmoney,availpoints;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_wallet_holder, container, false);
        cashin = rootView.findViewById(R.id.cashin);
        availpoints = rootView.findViewById(R.id.availpoints);
        context = getContext();
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String name = preferences.getString("session", "");

        walletmoney = rootView.findViewById(R.id.walletmoney);
        new getServices(context).execute(name);

        recyclerView = rootView.findViewById(R.id.wallethistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cashin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FingerprintDialog.initialize(getContext())
                        .title("Fingerprint Authentication For Payment")
                        .message("Please touch the fingerprint sensor with any enrolled fingerprint to this device.").callback(new FingerprintDialogCallback() {
                    @Override
                    public void onAuthenticationSucceeded() {
                        final Dialog dialogs = new Dialog(getContext());
                        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogs.setCancelable(false);
                        dialogs.setContentView(R.layout.dialogcashin);
                        Button proceed = dialogs.findViewById(R.id.proceed);
                        proceed.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                dialogs.dismiss();
                                EditText cashinprice = dialogs.findViewById(R.id.cashinprice);
                                Intent intent = new Intent(getContext(), PayPalPaymentAct.class);


                                intent.putExtra("email",name);
                                intent.putExtra("processtype","0");
                                intent.putExtra("cashinprice",cashinprice.getText().toString());
                                startActivity(intent);

                            }
                        });
                        Button close = dialogs.findViewById(R.id.close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogs.dismiss();
                            }
                        });
                        dialogs.show();
                    }

                    @Override
                    public void onAuthenticationCancel() {
                        Toast.makeText(context, "Authentication is required.", Toast.LENGTH_SHORT).show();

                    }
                })
                        .show();



            }


        });
        return  rootView;


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

            wallet_cashprocess_constructors.clear();
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
                        redeye.ghostofwar.philamlife.Classes.Wallet.wallet_cashprocess_constructors current1 = new wallet_cashprocess_constructors(pw_processedmoney,pw_processtype,pw_processdate,pw_processfee);
                        wallet_cashprocess_constructors.add(current1);




                    }

                    walletpoints = cashin-cashout;
                    availpoints.setText(String.valueOf(cashin*0.01));
                    walletmoney.setText(String.valueOf(walletpoints));
                    wallet_cashprocess_adapter = new wallet_cashprocess_adapter(context, wallet_cashprocess_constructors);
                    recyclerView.setAdapter(null);
                    recyclerView.setAdapter(wallet_cashprocess_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }



}
