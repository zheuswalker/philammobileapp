package redeye.ghostofwar.philamlife.Classes.Landing;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import javax.net.ssl.HttpsURLConnection;

import redeye.ghostofwar.philamlife.R;


public class activity_signup extends AppCompatActivity {
    TextView signinbtn;
    CheckBox newsletter;
    RadioGroup contactoption,contacttime;
    EditText fullname,birthday,location,email,contact,password,confirmpass;
    Button signupbtn;
    String newsletters,contactoptions, contacttimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        signinbtn = (TextView) findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(activity_signup.this, activity_signin.class);
                startActivity(a);
            }
        });
        fullname = findViewById(R.id.fullname);
        birthday = findViewById(R.id.birthday);
        location = findViewById(R.id.location);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmpass);
        contactoption = findViewById(R.id.contactoption);
        contacttime = findViewById(R.id.contacttime);
        newsletter = findViewById(R.id.newsletter);

        signupbtn = findViewById(R.id.signupbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmpass.getText().toString().trim().equals(password.getText().toString().trim())){
                    if(newsletter.isChecked())
                        newsletters="1";


                    int index_selected = contactoption.indexOfChild(contactoption
                            .findViewById(contactoption.getCheckedRadioButtonId()));
                    if(index_selected==1)
                        contactoptions = "email";
                    else
                        contactoptions = "Phone";
                    index_selected = contacttime.indexOfChild(contacttime
                            .findViewById(contacttime.getCheckedRadioButtonId()));
                    if(index_selected == 1)
                        contacttimes = "Pm";
                    else
                        contacttimes = "Am";

                    new createaccount(activity_signup.this).execute();


                }
            }
        });

    }

    public class createaccount extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        createaccount(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = "https://server.sympies.net/philamserver/PhilamServer/mobileinsertclientinfo.php";
            String data;
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("fullname","UTF-8")+"="+ URLEncoder.encode(fullname.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("birthdate","UTF-8")+"="+ URLEncoder.encode(birthday.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+ URLEncoder.encode(location.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("contact","UTF-8")+"="+ URLEncoder.encode(contact.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password.getText().toString().trim(),"UTF-8")+"&"+
                        URLEncoder.encode("contactoption","UTF-8")+"="+ URLEncoder.encode(contactoptions,"UTF-8")+"&"+
                        URLEncoder.encode("contacttime","UTF-8")+"="+ URLEncoder.encode(contacttimes,"UTF-8")+"&"+
                        URLEncoder.encode("newsletter","UTF-8")+"="+ URLEncoder.encode(newsletters,"UTF-8");
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
            if(result.trim().equals("true"))
                Toast.makeText(ctx, "Account Created. Please signin to continue.", Toast.LENGTH_SHORT).show();

        }



    }

}