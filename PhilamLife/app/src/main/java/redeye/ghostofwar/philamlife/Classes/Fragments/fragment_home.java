package redeye.ghostofwar.philamlife.Classes.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_adapter;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_constructors;
import redeye.ghostofwar.philamlife.R;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class    fragment_home extends Fragment {


    public static RecyclerView recyclerView;
    public  static redeye.ghostofwar.philamlife.Classes.Home.home_services_content_adapter home_services_content_adapter;
    public  static  List<redeye.ghostofwar.philamlife.Classes.Home.home_services_content_constructors> home_services_content_constructors =new ArrayList<>();
    redeye.ghostofwar.philamlife.Classes.Home.home_services_content_constructors current1;


    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_services_holder, container, false);
        context = getContext();
        new getServices(context).execute();
        recyclerView = rootView.findViewById(R.id.notificationrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

            String reference = Base.BASE_URL+ EndPoints.PHILAMSERVICES;

            String data ="";
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
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

            home_services_content_constructors.clear();
            JSONObject feedcontentvalues = null;

            try {

                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("services");

                    for (int i=0; i < feedvalues.length(); i++)
                    {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String pso_service_name = feedarray.getString("pso_service_name").trim();
                        String pso_service_desc = feedarray.getString("pso_service_desc").trim();
                        String pst_service_servicename = feedarray.getString("pst_service_servicename").trim();
                        String pso_icon = feedarray.getString("pso_icon").trim();

                        home_services_content_constructors current1 = new home_services_content_constructors(pso_service_name,pso_service_desc,pso_icon);
                        home_services_content_constructors.add(current1);




                    }
                    home_services_content_adapter = new home_services_content_adapter(context, home_services_content_constructors);
                    recyclerView.setAdapter(null);
                    recyclerView.setAdapter(home_services_content_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }

}
