package redeye.ghostofwar.philamlife.Classes.Landing;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import redeye.ghostofwar.philamlife.Classes.Activity.overall_home_activity;
import redeye.ghostofwar.philamlife.R;

public class chooser extends AppCompatActivity implements FingerprintDialogCallback {

    Button signup_btn, login_btn;
    Context context;
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
}
