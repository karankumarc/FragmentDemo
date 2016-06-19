package com.techpalle.karan.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ADMIN on 6/19/2016.
 */
public class DetailFragment extends Fragment {

    TextView textViewDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.detail_fragment_layout, null);
        textViewDetail = (TextView) view.findViewById(R.id.textViewDetail);

        if(bundle != null)
            textViewDetail.setText(bundle.getString("tabName"));

        return view;
    }

    public void updateTextView(String textToUpdate){
        textViewDetail.setText(textToUpdate);
    }
}
