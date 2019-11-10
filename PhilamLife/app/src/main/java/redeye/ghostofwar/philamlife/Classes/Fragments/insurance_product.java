package redeye.ghostofwar.philamlife.Classes.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import redeye.ghostofwar.philamlife.Classes.Cal.bussiness_and_my_employees;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_achieve_my_dreams;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_live_a_healthier_longer_and_better_life;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_live_a_healthier_related_setbacks;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_prepare_for_life_uncertainties;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_return_comfortably;
import redeye.ghostofwar.philamlife.Classes.Cal.cal_save_for_my_children_educ;
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
                Intent a = new Intent(insurance_product.this, cal_return_comfortably.class);
                startActivity(a);
            } else if (two.isChecked()){
                Intent a = new Intent(insurance_product.this, cal_achieve_my_dreams.class);
                startActivity(a);

            }else if (three.isChecked()){
                Intent a = new Intent(insurance_product.this, cal_save_for_my_children_educ.class);
                startActivity(a);

            }else if (four.isChecked()){
                Intent a = new Intent(insurance_product.this, cal_live_a_healthier_related_setbacks.class);
                startActivity(a);

            }else if (five.isChecked()){
                Intent a = new Intent(insurance_product.this, cal_live_a_healthier_longer_and_better_life.class);
                startActivity(a);

            }else if (six.isChecked()){
                Intent a = new Intent(insurance_product.this, cal_prepare_for_life_uncertainties.class);
                startActivity(a);

            }else if (seven.isChecked()){
                Intent a = new Intent(insurance_product.this, bussiness_and_my_employees.class);
                startActivity(a);

            }



        }
    });



    }
}
