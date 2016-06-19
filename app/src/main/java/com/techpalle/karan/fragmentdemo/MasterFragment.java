package com.techpalle.karan.fragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ADMIN on 6/19/2016.
 */
public class MasterFragment extends Fragment {


    OnListViewItemSelectedListener onListViewItemSelectedListener;
    ListView listView;
    String[] websiteTabs = {"Home","Contact Us","Development", "Training", "Placements"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the root view of the fragment layout file
        View view = inflater.inflate(R.layout.master_fragment_layout, null);

        //Create the UI of the fragment from the view
        listView = (ListView) view.findViewById(R.id.listViewMaster);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,websiteTabs));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Intent intent= new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("tabName",websiteTabs[i]);
                startActivity(intent);*/
                onListViewItemSelectedListener.websiteTabClicked(websiteTabs[i]);
            }
        });

        //Return the root view of the fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Binding the listener with the parent activity to make sure it implements it
        try{
            onListViewItemSelectedListener = (OnListViewItemSelectedListener) context;
        }catch (Exception ex){
        }
    }

    public interface OnListViewItemSelectedListener{
        public void websiteTabClicked(String tabName);
    }
}
