package com.techpalle.karan.fragmentdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MasterFragment.OnListViewItemSelectedListener {

    LinearLayout linearLayout;
    String tabName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.container_details);
        try{
            if(linearLayout == null){  //Phone opened in portrait mode
                FragmentManager fragmentManager = getSupportFragmentManager();
                MasterFragment masterFragment = (MasterFragment) fragmentManager.findFragmentById(R.id.container_main);
                if(masterFragment == null){
                    masterFragment = new MasterFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.container_main, masterFragment).commit();
                }
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                MasterFragment masterFragment = (MasterFragment) fragmentManager.findFragmentById(R.id.container_master);
                DetailFragment detailFragment= (DetailFragment) fragmentManager.findFragmentById(R.id.container_details);
                if(masterFragment == null && detailFragment == null){
                    masterFragment = new MasterFragment();
                    detailFragment = new DetailFragment();
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction1.add(R.id.container_master, masterFragment).commit();
                    fragmentTransaction2.add(R.id.container_details, detailFragment).commit();
                }
            }
        }
        catch(Exception ex){

        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String tabName = savedInstanceState.getString("tabName");
        websiteTabClicked(tabName);/*
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.container_main) instanceof MasterFragment){
            outState.putString("tabName","");
        } else {
            outState.putString("tabName",tabName);
        }*/
        super.onRestoreInstanceState(savedInstanceState);
    }

    /*public void dataPassedToActivity(String data){
        Toast.makeText(getApplicationContext(),data, Toast.LENGTH_LONG).show();
    }*/

    @Override
    public void websiteTabClicked(String tabName) {
        this.tabName = tabName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(linearLayout == null && !tabName.equals("")){
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("tabName",tabName);
            detailFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_main, detailFragment).addToBackStack(null).commit();
        } else {
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.container_details);
            detailFragment.updateTextView(tabName);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.container_main) instanceof MasterFragment){
            outState.putString("tabName","");
        } else {
            outState.putString("tabName",tabName);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
