package redeye.ghostofwar.philamlife.Classes.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_home;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_profile;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_support;
import redeye.ghostofwar.philamlife.Classes.Fragments.fragment_wallet;
import redeye.ghostofwar.philamlife.R;


public class overall_home_activity extends AppCompatActivity {
TextView whereiam;;

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



                }
            }
            return false;
        }
    };
}





