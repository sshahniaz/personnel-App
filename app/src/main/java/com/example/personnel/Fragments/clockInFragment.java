package com.example.personnel.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.personnel.R;

public class clockInFragment extends Fragment {
View view;
Context context;
Button clockinBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clock_in, container, false);

        Button clockinBtn = (Button) view.findViewById(R.id.clockInBtn);
        clockinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockOutFragment clockOutFragmentObj = new clockOutFragment();
                FragmentTransaction clockInTransaction = getFragmentManager().beginTransaction();

                clockInTransaction.replace(R.id.buttonFragment, clockOutFragmentObj);
                clockInTransaction.commit();
            }
        });
        return view;
    }
}