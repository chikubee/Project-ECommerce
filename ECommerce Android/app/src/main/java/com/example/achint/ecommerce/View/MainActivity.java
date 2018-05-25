package com.example.achint.ecommerce.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.achint.ecommerce.R;
import com.example.achint.ecommerce.Sessions.AlertDialogManager;
import com.example.achint.ecommerce.Sessions.SessionManagement;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button bt_signup;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManagement session;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManagement(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);

        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });
    }
}
