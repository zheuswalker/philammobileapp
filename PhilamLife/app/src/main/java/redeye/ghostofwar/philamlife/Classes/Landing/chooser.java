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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

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
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import redeye.ghostofwar.philamlife.Classes.Activity.overall_home_activity;
import redeye.ghostofwar.philamlife.R;

public class chooser extends AppCompatActivity implements FingerprintDialogCallback {

    private CallbackManager callbackManager;
    String email;
    Context context;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    Button signup_btn, login_btn,signupwithfacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);
        context = chooser.this;

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = preferences.getString("session", "");

        if(!name.equalsIgnoreCase(""))
        {
            try {
                FingerprintDialog.initialize(chooser.this)
                        .title("You're currently logged in to this account.")
                        .message("Please touch the fingerprint sensor with any enrolled fingerprint to this device.")
                        .callback(chooser.this)
                        .show();
            }catch (Exception ex){
                Toast.makeText(context, "Fingerprint scanner is not enabled.", Toast.LENGTH_SHORT).show();
            }
        }
    login_btn =  (Button) findViewById(R.id.login);
    signup_btn =  (Button) findViewById(R.id.signup);

    login_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = preferences.getString("session", "");

            if(!name.equalsIgnoreCase(""))
            {
                try {
                    FingerprintDialog.initialize(chooser.this)
                            .title("You're currently logged in to this account.")
                            .message("Please touch the fingerprint sensor with any enrolled fingerprint to this device.")
                            .callback(chooser.this)
                            .show();
                }catch (Exception ex){
                    Toast.makeText(context, "Fingerprint scanner is not enabled.", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Intent a = new Intent(chooser.this, activity_signin.class);
                startActivity(a);

            }

        }
    });
signup_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent ak = new Intent(chooser.this, activity_signup.class);
        startActivity(ak);
    }
});

        signupwithfacebook = findViewById(R.id.signupwithfacebook);
        callbackManager = CallbackManager.Factory.create();
        signupwithfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("curract", "landing");
                editor.apply();
                LoginManager.getInstance().logInWithReadPermissions(chooser.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>()
                        {
                            @Override
                            public void onSuccess(LoginResult loginResult)
                            {

                            }

                            @Override
                            public void onCancel()
                            {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception)
                            {
                                // App code
                            }
                        });
            }
        });

    }

    AccessTokenTracker accessToken = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            loaduserProfile(currentAccessToken);
        }
    };
    private void loaduserProfile(AccessToken acctok){
        GraphRequest request = GraphRequest.newMeRequest(acctok, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String emails = object.getString("email");
                    String id = object.getString("id");
                    email = emails;
                    Log.d("facebok information", first_name+last_name+emails+id);
                    new createaccount(context).execute(first_name+" "+last_name,emails);

                }
                catch (JSONException e){
                    e.printStackTrace();

                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onAuthenticationSucceeded() {
        Intent intent = new Intent(chooser.this, overall_home_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void onAuthenticationCancel() {
        Intent a = new Intent(chooser.this, activity_signin.class);
        startActivity(a);

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
            String fullname = params[0];
            String email = params[1];
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("fullname","UTF-8")+"="+ URLEncoder.encode(fullname,"UTF-8")+"&"+
                        URLEncoder.encode("birthdate","UTF-8")+"="+ URLEncoder.encode("","UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+ URLEncoder.encode("","UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("contact","UTF-8")+"="+ URLEncoder.encode("","UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode("random","UTF-8")+"&"+
                        URLEncoder.encode("contactoption","UTF-8")+"="+ URLEncoder.encode("email","UTF-8")+"&"+
                        URLEncoder.encode("contacttime","UTF-8")+"="+ URLEncoder.encode("AM","UTF-8")+"&"+
                        URLEncoder.encode("newsletter","UTF-8")+"="+ URLEncoder.encode("1","UTF-8");
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
                    editor.putString("session", email);
                    editor.apply();

                    Intent intent = new Intent(chooser.this, overall_home_activity.class);
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
