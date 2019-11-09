package redeye.ghostofwar.philamlife.Classes.Fragments;

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
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import redeye.ghostofwar.philamlife.Classes.Configs.Base;
import redeye.ghostofwar.philamlife.Classes.Configs.EndPoints;
import redeye.ghostofwar.philamlife.Classes.Landing.chooser;
import redeye.ghostofwar.philamlife.Classes.Wallet.PayPalPaymentAct;
import redeye.ghostofwar.philamlife.R;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_wallet extends Fragment  {


    CardView cashin;
    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_wallet_holder, container, false);
        cashin = rootView.findViewById(R.id.cashin);
        context = getContext();
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

                                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                                String name = preferences.getString("session", "");

                                intent.putExtra("email",name);
                                intent.putExtra("processtype",0);
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




}
