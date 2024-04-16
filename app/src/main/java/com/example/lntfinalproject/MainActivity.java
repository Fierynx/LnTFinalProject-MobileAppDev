package com.example.lntfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private TextView username;
    private ImageButton logoutButton;
    private ProgressBar progressBar;
    private View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigation = findViewById(R.id.bottomNavigation);
        username = findViewById(R.id.username);
        logoutButton = findViewById(R.id.logoutButton);
        progressBar = findViewById(R.id.progressBar);
        overlay =findViewById(R.id.overlay);
        Counter counterFragment = new Counter();
        Luas luasFragment = new Luas();
        Volume volumeFragment = new Volume();

        setUsernameValue();

        setFragment(counterFragment);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                int id = menuItem.getItemId();

                if (id == R.id.counter) {
                    fragment = counterFragment;
                } else if (id == R.id.luas) {
                    fragment = luasFragment;
                } else if (id == R.id.volume) {
                    fragment = volumeFragment;
                }

                if (fragment != null) {
                    return setFragment(fragment);
                }
                return false;
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You have logged out!", Toast.LENGTH_SHORT).show();
                showProgressBar();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void setUsernameValue(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String key = "name_" + user.getUid();
        String name = sp.getString(key, "USER");

        username.setText(name);
    }
    private boolean setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
            return true;
        }
        return false;
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