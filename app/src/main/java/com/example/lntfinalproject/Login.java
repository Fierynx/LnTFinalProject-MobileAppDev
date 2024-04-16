package com.example.lntfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvSignUp;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        progressBar = findViewById(R.id.progress_bar);
        overlay = findViewById(R.id.overlay);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        setEditTextValue();
        login();
        redirectToRegister();
    }

    private void setEditTextValue(){
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");

        etEmail.setText(email);
        etPassword.setText(password);
    }

    private void login(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(email.length() <= 0){
                    Toast.makeText(Login.this, "Email field must be filled", Toast.LENGTH_SHORT).show();
                }else if(!email.contains("@")){
                    Toast.makeText(Login.this, "Email must contain '@' character", Toast.LENGTH_SHORT).show();
                }else if(!email.endsWith(".com")){
                    Toast.makeText(Login.this, "Email must end with \".com\"", Toast.LENGTH_SHORT).show();
                }else if(password.length() <= 0){
                    Toast.makeText(Login.this, "Password field must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        showProgressBar();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Email and/or password are invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void redirectToRegister(){
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                Intent intent = new Intent(Login.this, Register.class);
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