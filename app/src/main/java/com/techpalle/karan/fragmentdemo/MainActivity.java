package com.techpalle.karan.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MasterFragment.OnListViewItemSelectedListener {

    LinearLayout linearLayout;
    String tabName;             //Store the current tab name
    Boolean isPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUiSetup();
    }

    /**
     * Create the initial UI depending on the orientation of the phone
     */
    private void initialUiSetup() {
        linearLayout = (LinearLayout) findViewById(R.id.container_details);
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (linearLayout == null) {

                //region Display the master fragment in portrait mode if the container is empty
                isPortrait = true;
                Fragment fragment = fragmentManager.findFragmentById(R.id.container_main);
                if (!(fragment instanceof MasterFragment || fragment instanceof DetailFragment)) {
                    MasterFragment masterFragment = new MasterFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.container_main, masterFragment).commit();
                }
                //endregion

            } else {

                //region Display master and detail fragments if containers are empty
                isPortrait = false;
                MasterFragment masterFragment = (MasterFragment) fragmentManager.findFragmentById(R.id.container_master);
                DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.container_details);
                if (masterFragment == null && detailFragment == null) {
                    masterFragment = new MasterFragment();
                    detailFragment = new DetailFragment();
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction1.add(R.id.container_master, masterFragment).commit();
                    fragmentTransaction2.add(R.id.container_details, detailFragment).commit();
                }
                //endregion
            }
        } catch (Exception ex) {

        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String tabName = savedInstanceState.getString("tabName");
        this.tabName = tabName;
        if(!tabName.equals("")){
            websiteTabClicked(tabName);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void websiteTabClicked(String tabName) {
        this.tabName = tabName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (isPortrait) {
            DetailFragment detailFragment;
            if (!(fragmentManager.findFragmentById(R.id.container_main) instanceof DetailFragment)) {
                detailFragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("tabName", tabName);
                detailFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_main, detailFragment).addToBackStack(null).commit();
            } else {
                detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.container_main);
                detailFragment.updateTextView(tabName);
            }

        } else {
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.container_details);
            detailFragment.updateTextView(tabName);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.container_main) instanceof MasterFragment && isPortrait) {
            outState.putString("tabName", "");
        } else {
            outState.putString("tabName", tabName);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
