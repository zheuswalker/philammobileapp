package redeye.ghostofwar.philamlife.Classes.Landing;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import redeye.ghostofwar.philamlife.R;

public class forgot_password extends AppCompatActivity {
Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(forgot_password.this, password_confirm.class);
                startActivity(a);
            }
        });



    }
}
