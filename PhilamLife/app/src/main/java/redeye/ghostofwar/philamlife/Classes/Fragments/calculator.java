package redeye.ghostofwar.philamlife.Classes.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import redeye.ghostofwar.philamlife.R;

public class calculator extends AppCompatActivity {
RadioButton a, b , c , d, e , f;
TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_live_a_healthier_longer_and_better_life);
a =  findViewById(R.id.a);
        b =  findViewById(R.id.b);
        c =  findViewById(R.id.c);
        d =  findViewById(R.id.d);
        e =  findViewById(R.id.e);
        f =  findViewById(R.id.f);
        total = findViewById(R.id.total);

        if (a.isChecked()){
            int b = 54000;
        }






    }




}
