package com.example.dynamic;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements Celcius.FragmentAListener, Fahrenheit.FragmentBListener {
    private Celcius fragmentA;
    private Fahrenheit fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new Celcius();
        fragmentB = new Fahrenheit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    public void onInputASent(String input) {
        if(!fragmentA.validInput())
            return;
        fragmentB.convert(input);
    }

    @Override
    public void onInputBSent(String input) {
        if(!fragmentB.validInput())
            return;
        fragmentA.convert(input);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            Toast.makeText(MainActivity.this, "Home button clicked", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nav_account:
                            Toast.makeText(MainActivity.this, "Account button clicked", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nav_clear:
                            Toast.makeText(MainActivity.this, "Clear button clicked", Toast.LENGTH_SHORT).show();
                            fragmentA.clearData();
                            fragmentB.clearData();
                            break;
                    }
                    return true;
                }
            };

}
