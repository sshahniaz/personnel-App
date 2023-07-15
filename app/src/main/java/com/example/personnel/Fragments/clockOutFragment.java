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


public class clockOutFragment extends Fragment {
    View view;
    Context context;
    Button clockoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and set in view variable
       view = inflater.inflate(R.layout.fragment_clock_out, container, false);

//       set button for on click function
       Button clockoutBtn = (Button) view.findViewById(R.id.clockOutbtn);

//       set on click function
       clockoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

//               create object of clock in fragment
               clockInFragment clockInFragmentObj = new clockInFragment();
               FragmentTransaction clockOutTransaction = getFragmentManager().beginTransaction();
               clockOutTransaction.replace(R.id.buttonFragment, clockInFragmentObj);
               clockOutTransaction.commit();

           }
       });

        return view;
    }
}