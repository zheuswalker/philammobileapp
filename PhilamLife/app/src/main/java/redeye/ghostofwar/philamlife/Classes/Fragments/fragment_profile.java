package redeye.ghostofwar.philamlife.Classes.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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

import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.R;


/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_profile extends Fragment {

    TextView contact, fullname, email;
    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_profile_holder, container, false);
        context = getContext();
        contact = rootView.findViewById(R.id.contact);
        fullname = rootView.findViewById(R.id.fullname);
        email = rootView.findViewById(R.id.email);

        new getprofiledetails(context).execute();
        return  rootView;


    }


    public class getprofiledetails extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        getprofiledetails(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = Base.BASE_URL+ EndPoints.PROFILEDETAILS;

            String data ="";
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                String name = preferences.getString("session", "");

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8");
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
            Log.d("resultfromwebprofile",result);

            JSONObject feedcontentvalues = null;

            try {

                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("profiledetails");
                        JSONObject feedarray = feedvalues.getJSONObject(0);
                        String pcc_fullname = feedarray.getString("pcc_fullname").trim();
                        String pcc_email = feedarray.getString("pcc_email").trim();
                        String pcc_contact = feedarray.getString("pcc_contact").trim();


                        fullname.setText(pcc_fullname);
                        email.setText(pcc_email);
                        contact.setText(pcc_contact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }

}
