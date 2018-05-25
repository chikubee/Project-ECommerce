package com.example.achint.ecommerce.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.achint.ecommerce.R;

public class MainActivity extends AppCompatActivity {
    Button bt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_signup = findViewById(R.id.bt_register);
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);

            }

        });
    }
}
