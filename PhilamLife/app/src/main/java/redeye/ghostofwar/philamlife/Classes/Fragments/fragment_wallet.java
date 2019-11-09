package redeye.ghostofwar.philamlife.Classes.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import redeye.ghostofwar.philamlife.Classes.Landing.chooser;
import redeye.ghostofwar.philamlife.Classes.Wallet.PayPalPaymentAct;
import redeye.ghostofwar.philamlife.R;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_wallet extends Fragment  {


    CardView cashin;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_wallet_holder, container, false);
        final Context context = getActivity().getApplicationContext();
        cashin = rootView.findViewById(R.id.cashin);
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
