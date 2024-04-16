package com.example.lntfinalproject;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Counter extends Fragment {
    private TextView textViewCounter;
    private int counter;
    private SharedPreferences sp;
    private String key;

    public Counter() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_counter, container, false);

        textViewCounter = rootView.findViewById(R.id.textViewCounter);
        Button buttonIncrement = rootView.findViewById(R.id.buttonIncrement);
        Button buttonDecrement = rootView.findViewById(R.id.buttonDecrement);
        Button buttonReset = rootView.findViewById(R.id.buttonReset);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        key = "counter_" + user.getUid();

        sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        counter = sp.getInt(key, 0);
        textViewCounter.setText(String.valueOf(counter));

        buttonIncrement.setOnClickListener(view -> {
            counter++;
            textViewCounter.setText(String.valueOf(counter));
            saveToSP(counter);
        });

        buttonDecrement.setOnClickListener(view ->{
            counter--;
            textViewCounter.setText(String.valueOf(counter));
            saveToSP(counter);
        });

        buttonReset.setOnClickListener(view ->{
            counter = 0;
            textViewCounter.setText(String.valueOf(counter));
            saveToSP(counter);
        });

        return rootView;
    }

    private void saveToSP(int counter) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, counter);
        editor.apply();
    }

}