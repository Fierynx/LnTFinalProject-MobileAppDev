package com.example.lntfinalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Luas extends Fragment {

    private EditText etSide, etBase, etHeight, etRadius;
    private TextView tvResult;
    private Button buttonCalculate;
    private LinearLayout inputSquare, inputTriangle, inputCircle;
    private LinearLayout layoutSquare, layoutTriangle, layoutCircle;
    public String mode;

    public Luas() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_luas, container, false);

        etSide = rootView.findViewById(R.id.etSide);
        etBase = rootView.findViewById(R.id.etBase);
        etHeight = rootView.findViewById(R.id.etHeight);
        etRadius = rootView.findViewById(R.id.etRadius);
        tvResult = rootView.findViewById(R.id.tvResult);
        buttonCalculate = rootView.findViewById(R.id.buttonCalculate);

        inputSquare = rootView.findViewById(R.id.inputSquare);
        inputTriangle = rootView.findViewById(R.id.inputTriangle);
        inputCircle = rootView.findViewById(R.id.inputCircle);

        layoutSquare = rootView.findViewById(R.id.layoutSquare);
        layoutTriangle = rootView.findViewById(R.id.layoutTriangle);
        layoutCircle = rootView.findViewById(R.id.layoutCircle);

        mode = "square";
        layoutSquare.setBackgroundResource(R.drawable.border_selected);
        inputSquare.setVisibility(View.VISIBLE);

        layoutSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutSquare.setBackgroundResource(R.drawable.border_selected);
                layoutTriangle.setBackgroundResource(R.drawable.border_unselected);
                layoutCircle.setBackgroundResource(R.drawable.border_unselected);
                inputSquare.setVisibility(View.VISIBLE);
                inputCircle.setVisibility(View.GONE);
                inputTriangle.setVisibility(View.GONE);
                mode = "square";
            }
        });

        layoutTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutSquare.setBackgroundResource(R.drawable.border_unselected);
                layoutTriangle.setBackgroundResource(R.drawable.border_selected);
                layoutCircle.setBackgroundResource(R.drawable.border_unselected);
                inputSquare.setVisibility(View.GONE);
                inputCircle.setVisibility(View.GONE);
                inputTriangle.setVisibility(View.VISIBLE);
                mode = "triangle";
            }
        });

        layoutCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutSquare.setBackgroundResource(R.drawable.border_unselected);
                layoutTriangle.setBackgroundResource(R.drawable.border_unselected);
                layoutCircle.setBackgroundResource(R.drawable.border_selected);
                inputSquare.setVisibility(View.GONE);
                inputCircle.setVisibility(View.VISIBLE);
                inputTriangle.setVisibility(View.GONE);
                mode = "circle";
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.equals("square")){
                    if(etSide.getText().toString().isEmpty()){
                        tvResult.setText("Please input square side length");
                    }else if(Double.parseDouble(etSide.getText().toString()) < 0){
                        tvResult.setText("Side length must be larger or equal to 0");
                    }else if(!etSide.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Side length can only contain numbers");
                    }else{
                        double side = Double.parseDouble(etSide.getText().toString());
                        double areaSquare = side * side;
                        tvResult.setText("Area of Square: " + areaSquare + " cm^2");
                    }
                }else if(mode.equals("triangle")){
                    if(etBase.length() <= 0){
                        tvResult.setText("Please input triangle base length");
                    }else if(Double.parseDouble(etBase.getText().toString()) < 0){
                        tvResult.setText("Base length must be larger or equal to 0");
                    }else if(!etBase.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Base length can only contain numbers");
                    }else if(etHeight.length() <= 0){
                        tvResult.setText("Please input triangle height");
                    }else if(Double.parseDouble(etHeight.getText().toString()) < 0){
                        tvResult.setText("Triangle height must be larger or equal to 0");
                    }else if(!etHeight.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Triangle height can only contain numbers");
                    }else{
                        double base = Double.parseDouble(etBase.getText().toString());
                        double height = Double.parseDouble(etHeight.getText().toString());
                        double areaTriangle = 0.5 * base * height;
                        tvResult.setText("Area of Triangle: " + areaTriangle + " cm^2");
                    }

                }else if(mode.equals("circle")){
                    if(etRadius.getText().toString().isEmpty()){
                        tvResult.setText("Please input circle radius");
                    }else if(Double.parseDouble(etRadius.getText().toString()) < 0){
                        tvResult.setText("Radius must be larger or equal to 0");
                    }else if(!etRadius.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Radius can only contain numbers");
                    }else{
                        double radius = Double.parseDouble(etRadius.getText().toString());
                        double areaCircle = Math.PI * radius * radius;
                        tvResult.setText("Area of Circle: " + areaCircle + " cm^2");
                    }
                }
            }
        });

        return rootView;
    }
}