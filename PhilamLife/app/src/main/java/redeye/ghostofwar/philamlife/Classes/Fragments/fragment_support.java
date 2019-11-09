package redeye.ghostofwar.philamlife.Classes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.thefinestartist.finestwebview.FinestWebView;

import redeye.ghostofwar.philamlife.Classes.Branches.mapbox;
import redeye.ghostofwar.philamlife.R;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_support extends Fragment {


    TextView callph,callpldt,email, philamvital,pami,bpiphilam,aia;
    CardView locatenow;
    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_support_holder, container, false);
        callph = rootView.findViewById(R.id.callph);
        callpldt = rootView.findViewById(R.id.callpldt);
        context = getContext();
        callph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:028528200"));
                context.startActivity(intent);
            }
        });
        callpldt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800105282000"));
                context.startActivity(intent);
            }
        });
        email = rootView.findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","philamlife@aia.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquery for PhilamLife Plans");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        philamvital = rootView.findViewById(R.id.philamvital);
        philamvital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(context).show("https://www.philamvitality.com/vmp-ph");
            }
        });

        philamvital.setPaintFlags(philamvital.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        pami = rootView.findViewById(R.id.pami);
        pami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(context).show("https://www.philamfunds.com/en/index.html");
            }
        });

        pami.setPaintFlags(pami.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        bpiphilam = rootView.findViewById(R.id.bpiphilam);
        bpiphilam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(context).show("https://www.bpi-philam.com/en/index.html");
            }
        });

        bpiphilam.setPaintFlags(bpiphilam.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        aia = rootView.findViewById(R.id.aia);
        aia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(context).show("https://www.aia.com/en/index.html");
            }
        });

        aia.setPaintFlags(aia.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        locatenow = rootView.findViewById(R.id.locatenow);
        locatenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, mapbox.class);
                context.startActivity(intent);
            }
        });
        return  rootView;


    }


//    public class getprofiledetails extends AsyncTask<String, Void, String> {
//        AlertDialog alertDialog;
//        Context ctx;
//        getprofiledetails(Context ctx){
//            this.ctx = ctx;
//        }
//        @Override
//        protected String doInBackground(String... params) {
//
//            String reference = Base.BASE_URL+ EndPoints.PROFILEDETAILS;
//
//            String data ="";
//            try {
//                URL url = new URL(reference);
//                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(false);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//                String name = preferences.getString("session", "");
//
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
//                data = URLEncoder.encode("email","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8");
//                bufferedWriter.write(data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
//                String response = "";
//                String line = "";
//                while ((line = bufferedReader.readLine())!=null)
//                {
//                    response+= line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return response;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return  null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.d("resultfromwebprofile",result);
//
//            JSONObject feedcontentvalues = null;
//
//            try {
//
//                feedcontentvalues = new JSONObject(result);
//
//                try {
//                    JSONArray feedvalues = feedcontentvalues.getJSONArray("profiledetails");
//                        JSONObject feedarray = feedvalues.getJSONObject(0);
//                        String pcc_fullname = feedarray.getString("pcc_fullname").trim();
//                        String pcc_email = feedarray.getString("pcc_email").trim();
//                        String pcc_contact = feedarray.getString("pcc_contact").trim();
//
//
//                        fullname.setText(pcc_fullname);
//                        email.setText(pcc_email);
//                        contact.setText(pcc_contact);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            } catch(JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//
//    }

}
