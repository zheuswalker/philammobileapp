package redeye.ghostofwar.philamlife.Classes.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_home;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_profile;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_support;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_wallet;
import redeye.ghostofwar.philamlife.Classes.Fragments.insurance_product;
import redeye.ghostofwar.philamlife.Classes.Home.datamine_adapter;
import redeye.ghostofwar.philamlife.Classes.Home.home_datamine_costructor;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_adapter;
import redeye.ghostofwar.philamlife.Classes.Home.home_services_content_constructors;
import redeye.ghostofwar.philamlife.Classes.Landing.chooser;
import redeye.ghostofwar.philamlife.R;


public class overall_home_activity extends AppCompatActivity {
TextView whereiam;;

    public static RecyclerView recyclerView;
    public  static datamine_adapter datamine_adapter;
    public  static List<home_datamine_costructor> home_datamine_costructor =new ArrayList<>();

    public static BottomNavigationView navigation;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_overall_home_activity);
        whereiam = findViewById(R.id.whereiam);
        navigation= findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(1);

        Fragment fragment = new fragment_home();
        loadFragment(fragment);


        final Dialog dialogs = new Dialog(overall_home_activity.this);
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogs.setCancelable(false);
        dialogs.setContentView(R.layout.dialogdatamine);

            recyclerView = dialogs.findViewById(R.id.datamine);
            Button closedialog = dialogs.findViewById(R.id.closedialog);
        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs.dismiss();
            }
        });
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        home_datamine_costructor current1 = new home_datamine_costructor("We found out that you love to travel. Would you like" +
                "to avail this microinsurance.","https://www.abc.net.au/cm/rimage/11097260-16x9-xlarge.jpg","Protection: Guardian",
                "FULL (P10,000)","MICROINSURANCE (7-DAYS P90)");
        home_datamine_costructor.add(current1);
        current1 = new home_datamine_costructor("We noticed that you use cigarettes","https://www.sayonarasmoking.com/w/wp-content/uploads/chest-pains-from-smoking.jpg","Health 100 ",
                "FULL (P15,000)","MICROINSURANCE (7-DAYS P90)");
        home_datamine_costructor.add(current1);
        current1 = new home_datamine_costructor("Do you want your future childrens to be a scholar? ","" +
                "https://www.usnews.com/dims4/USNEWS/35b6c0b/2147483647/thumbnail/640x420/quality/85/?url=http%3A%2F%2Fcom-usnews-beam-media.s3.amazonaws.com%2Fa2%2Fce%2Ffe9ca478427fb5243b5b7759a438%2F140409-graduation-stock.jpg","Education : FUTURE SCHOLAR ",
                "FULL (P12,000)","MICROINSURANCE (7-DAYS P90)");
        home_datamine_costructor.add(current1);

        datamine_adapter = new datamine_adapter(getApplicationContext(), home_datamine_costructor);
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(datamine_adapter);
        new LinearSnapHelper().attachToRecyclerView(recyclerView);


        dialogs.show();




    }

    public void loadFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("my_fragment").commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = new fragment_home();
        loadFragment(fragment);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.feed_navigation_home:
                    whereiam.setText("SERVICES");
                    fragment_home fragment_feed = new fragment_home();
                    loadFragment(fragment_feed);
                    return true;

                case R.id.feed_navigation_notif:
                {

                    whereiam.setText("WALLET");
                    fragment_wallet fragment_wallet = new fragment_wallet();
                    loadFragment(fragment_wallet

                    );
                    return true;

                }

                case R.id.feed_navigation_profile: {
                    whereiam.setText("PROFILE");
                    fragment = new fragment_profile();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.feed_navigation_search:
                {
                    whereiam.setText("SUPPORT");
                    fragment = new fragment_support();
                    loadFragment(fragment);
                    return true;
                }
                case R.id.feed_navigation_create: {


                    Intent a = new Intent (overall_home_activity.this, insurance_product.class);
                    startActivity(a);


                }
            }
            return false;
        }
    };
}





