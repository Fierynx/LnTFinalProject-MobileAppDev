package com.example.lntfinalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Volume extends Fragment {
    private EditText etBlockLength, etBlockWidth, etBlockHeight;
    private EditText etPyramidHeight, etBaseLength, etBaseWidth;
    private EditText etRadius, etCylinderHeight;
    private TextView tvResult;
    private Button buttonCalculate;
    private LinearLayout inputBlock, inputPyramid, inputCylinder;
    private LinearLayout layoutBlock, layoutPyramid, layoutCylinder;
    public String mode;

    public Volume() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_volume, container, false);

        etBlockLength = rootView.findViewById(R.id.etBlockLength);
        etBlockWidth = rootView.findViewById(R.id.etBlockWidth);
        etBlockHeight = rootView.findViewById(R.id.etBlockHeight);

        etPyramidHeight = rootView.findViewById(R.id.etPyramidHeight);
        etBaseLength = rootView.findViewById(R.id.etBaseLength);
        etBaseWidth = rootView.findViewById(R.id.etBaseWidth);

        etCylinderHeight = rootView.findViewById(R.id.etCylinderHeight);
        etRadius = rootView.findViewById(R.id.etRadius);

        tvResult = rootView.findViewById(R.id.tvResult);
        buttonCalculate = rootView.findViewById(R.id.buttonCalculate);

        inputBlock = rootView.findViewById(R.id.inputBlock);
        inputPyramid = rootView.findViewById(R.id.inputPyramid);
        inputCylinder = rootView.findViewById(R.id.inputCylinder);

        layoutBlock = rootView.findViewById(R.id.layoutBlock);
        layoutPyramid = rootView.findViewById(R.id.layoutPyramid);
        layoutCylinder = rootView.findViewById(R.id.layoutCylinder);

        mode = "block";
        layoutBlock.setBackgroundResource(R.drawable.border_selected);
        inputBlock.setVisibility(View.VISIBLE);

        layoutBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutBlock.setBackgroundResource(R.drawable.border_selected);
                layoutPyramid.setBackgroundResource(R.drawable.border_unselected);
                layoutCylinder.setBackgroundResource(R.drawable.border_unselected);
                inputBlock.setVisibility(View.VISIBLE);
                inputPyramid.setVisibility(View.GONE);
                inputCylinder.setVisibility(View.GONE);
                mode = "block";
            }
        });

        layoutPyramid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutBlock.setBackgroundResource(R.drawable.border_unselected);
                layoutPyramid.setBackgroundResource(R.drawable.border_selected);
                layoutCylinder.setBackgroundResource(R.drawable.border_unselected);
                inputBlock.setVisibility(View.GONE);
                inputPyramid.setVisibility(View.VISIBLE);
                inputCylinder.setVisibility(View.GONE);
                mode = "pyramid";
            }
        });

        layoutCylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("");
                layoutBlock.setBackgroundResource(R.drawable.border_unselected);
                layoutPyramid.setBackgroundResource(R.drawable.border_unselected);
                layoutCylinder.setBackgroundResource(R.drawable.border_selected);
                inputBlock.setVisibility(View.GONE);
                inputPyramid.setVisibility(View.GONE);
                inputCylinder.setVisibility(View.VISIBLE);
                mode = "cylinder";
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.equals("block")){
                    if(etBlockLength.getText().toString().isEmpty()){
                        tvResult.setText("Please input block length");
                    }else if(Double.parseDouble(etBlockLength.getText().toString()) < 0){
                        tvResult.setText("Block length must be larger or equal to 0");
                    }else if(!etBlockLength.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Block length can only contain numbers");
                    }else if(etBlockWidth.getText().toString().isEmpty()){
                        tvResult.setText("Please input block width");
                    }else if(Double.parseDouble(etBlockWidth.getText().toString()) < 0){
                        tvResult.setText("Block width must be larger or equal to 0");
                    }else if(!etBlockWidth.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Block width can only contain numbers");
                    }else if(etBlockHeight.getText().toString().isEmpty()){
                        tvResult.setText("Please input block height");
                    }else if(Double.parseDouble(etBlockHeight.getText().toString()) < 0){
                        tvResult.setText("Block height must be larger or equal to 0");
                    }else if(!etBlockHeight.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Block height can only contain numbers");
                    }else{
                        double length = Double.parseDouble(etBlockLength.getText().toString());
                        double width = Double.parseDouble(etBlockWidth.getText().toString());
                        double height = Double.parseDouble(etBlockHeight.getText().toString());
                        double volumeBlock = length * width * height;
                        tvResult.setText("Volume of Block: " + volumeBlock + " cm^3");
                    }
                }else if(mode.equals("pyramid")){
                    if(etPyramidHeight.getText().toString().isEmpty()){
                        tvResult.setText("Please input pyramid height");
                    }else if(Double.parseDouble(etPyramidHeight.getText().toString()) < 0){
                        tvResult.setText("Pyramid height must be larger or equal to 0");
                    }else if(!etPyramidHeight.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Pyramid height can only contain numbers");
                    }else if(etBaseLength.getText().toString().isEmpty()){
                        tvResult.setText("Please input pyramid base length");
                    }else if(Double.parseDouble(etBaseLength.getText().toString()) < 0){
                        tvResult.setText("Pyramid base length must be larger or equal to 0");
                    }else if(!etBaseLength.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Pyramid base length can only contain numbers");
                    }else if(etBaseWidth.getText().toString().isEmpty()){
                        tvResult.setText("Please input pyramid base width");
                    }else if(Double.parseDouble(etBaseWidth.getText().toString()) < 0){
                        tvResult.setText("Pyramid base width must be larger or equal to 0");
                    }else if(!etBaseWidth.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Pyramid base width can only contain numbers");
                    }else{
                        double height = Double.parseDouble(etPyramidHeight.getText().toString());
                        double base_length = Double.parseDouble(etBaseLength.getText().toString());
                        double base_width = Double.parseDouble(etBaseWidth.getText().toString());
                        double volumePyramid = ((double) 1 /3) * height * base_length * base_width;
                        tvResult.setText("Volume of Block: " + volumePyramid + " cm^3");
                    }

                }else if(mode.equals("cylinder")){
                    if(etCylinderHeight.getText().toString().isEmpty()){
                        tvResult.setText("Please input cylinder height");
                    }else if(Double.parseDouble(etCylinderHeight.getText().toString()) < 0){
                        tvResult.setText("Cylinder height must be larger or equal to 0");
                    }else if(!etCylinderHeight.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Cylinder height can only contain numbers");
                    }else if(etRadius.getText().toString().isEmpty()){
                        tvResult.setText("Please input cylinder base radius");
                    }else if(Double.parseDouble(etRadius.getText().toString()) < 0){
                        tvResult.setText("Cylinder base radius length must be larger or equal to 0");
                    }else if(!etRadius.getText().toString().matches("[0-9]+(\\.[0-9]+)?")){
                        tvResult.setText("Cylinder base radius length can only contain numbers");
                    }else{
                        double height = Double.parseDouble(etCylinderHeight.getText().toString());
                        double radius = Double.parseDouble(etRadius.getText().toString());
                        double volumeCylinder = Math.PI * radius * radius * height;
                        tvResult.setText("Volume of Block: " + volumeCylinder + " cm^3");
                    }
                }
            }
        });

        return rootView;
    }
}