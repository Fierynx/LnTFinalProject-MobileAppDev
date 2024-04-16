package com.example.lntfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText etIdBimbel, etEmail, etName, etPassword, etConfirmPassword;
    private Button buttonRegister;
    private TextView tvSignIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etIdBimbel = findViewById(R.id.etIdBimbel);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        tvSignIn = findViewById(R.id.tvSignIn);
        progressBar = findViewById(R.id.progress_bar);
        overlay = findViewById(R.id.overlay);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        register();

        redirectToLogin();
    }

    private void register(){
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_bimbel = etIdBimbel.getText().toString();
                String email = etEmail.getText().toString();
                String name = etName.getText().toString();
                String password = etPassword.getText().toString();
                String confirm_password = etConfirmPassword.getText().toString();
                if(id_bimbel.length() <= 0){
                    Toast.makeText(Register.this, "ID bimbel field must be filled", Toast.LENGTH_SHORT).show();
                }else if(email.length() <= 0){
                    Toast.makeText(Register.this, "Email field must be filled", Toast.LENGTH_SHORT).show();
                }else if(!email.contains("@")){
                    Toast.makeText(Register.this, "Email must contain '@' character", Toast.LENGTH_SHORT).show();
                }else if(!email.endsWith(".com")){
                    Toast.makeText(Register.this, "Email must end with \".com\"", Toast.LENGTH_SHORT).show();
                }else if(name.length() <= 0){
                    Toast.makeText(Register.this, "Name field must be filled", Toast.LENGTH_SHORT).show();
                }else if(name.length() < 5){
                    Toast.makeText(Register.this, "Name must be at least 5 letters long", Toast.LENGTH_SHORT).show();
                }else if(password.length() <= 0){
                    Toast.makeText(Register.this, "Password field must be filled", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6){
                    Toast.makeText(Register.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                }else if(confirm_password.length() <= 0){
                    Toast.makeText(Register.this, "Confirm password field must be filled", Toast.LENGTH_SHORT).show();
                }else if(!confirm_password.equals(password)){
                    Toast.makeText(Register.this, "Confirm password value must be the same as password", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Register.this, "You have been registered!", Toast.LENGTH_SHORT).show();
                                        showProgressBar();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        assert user != null;
                                        String id_bimbel_key = "id_bimbel_" + user.getUid();
                                        String name_key = "name_" + user.getUid();
                                        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString(id_bimbel_key, id_bimbel);
                                        editor.putString("email", email);
                                        editor.putString(name_key, name);
                                        editor.putString("password", password);
                                        editor.apply();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user
                                        Toast.makeText(Register.this, "Registration failed, please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void redirectToLogin(){
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void showProgressBar() {
        setVisibilityOn();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setVisibilityOff();
            }
        }, 2000);
    }

    private void setVisibilityOn(){
        progressBar.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.VISIBLE);
    }

    private void setVisibilityOff(){
        progressBar.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
    }
}