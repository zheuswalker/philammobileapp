package redeye.ghostofwar.philamlife.Classes.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import redeye.ghostofwar.philamlife.R;

public class insurance_product extends AppCompatActivity {
Button product;
RadioButton one, two, three, four, five, six, seven;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_product);

        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        five = (RadioButton) findViewById(R.id.five);
        six = (RadioButton) findViewById(R.id.six);
        seven = (RadioButton) findViewById(R.id.seven);


        product = (Button) findViewById(R.id.product);

    product.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (one.isChecked()){


            } else if (two.isChecked()){

            }else if (three.isChecked()){

            }else if (four.isChecked()){

            }else if (five.isChecked()){

            }else if (six.isChecked()){

            }else if (seven.isChecked()){

            }



        }
    });



    }
}
