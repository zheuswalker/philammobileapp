package redeye.ghostofwar.philamlife.Classes.Landing;


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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import redeye.ghostofwar.philamlife.Classes.Activity.overall_home_activity;
import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.R;

public class activity_signin extends AppCompatActivity {
    TextView forgotpass;
    EditText email,password;
    Button login;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = activity_signin.this;


        forgotpass = (TextView) findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {


                    new checklogin(activity_signin.this).execute();
                }
            }
        });


    }


    public class checklogin extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        checklogin(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = Base.BASE_URL+ EndPoints.GETLOGIN;

            String data;
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password.getText().toString().trim(),"UTF-8");
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



            Log.d("resultfromweb",result);
            if(!result.trim().equals("true"))
                Toast.makeText(ctx, "Account not found.", Toast.LENGTH_SHORT).show();
            else
            {

                final Dialog dialogs = new Dialog(context);
                dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogs.setCancelable(false);
                dialogs.setContentView(R.layout.dialogauthmethod);
                Button close = dialogs.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("session", email.getText().toString());
                        editor.apply();

                        Intent intent = new Intent(activity_signin.this, overall_home_activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        dialogs.dismiss();
                        Toast.makeText(ctx, "Fingerprints in this device are now used to authorize future transactions.", Toast.LENGTH_SHORT).show();
                    }
                });

                dialogs.show();


            }

        }



    }

}
