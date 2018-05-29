package com.example.achint.ecommerce.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achint.ecommerce.Controller.MainController;
import com.example.achint.ecommerce.Interface.UsersInterface;
import com.example.achint.ecommerce.Model.Users;
import com.example.achint.ecommerce.R;
import com.example.achint.ecommerce.Sessions.SessionManagement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    UsersInterface usersInterface;
    Users userInResponse;
    EditText eFname;
    EditText eLname;
    EditText eAddress;
    EditText ePhone;
    EditText eEmail;
    EditText ePassword;
    SessionManagement session;
    Button eSave;

    public void getUser(String email) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        Call<Users> call = usersInterface.getUserDetails(email);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    userInResponse = response.body();
                    eFname.setText(userInResponse.getFirstname());
                    eLname.setText(userInResponse.getLastname());
                    eAddress.setText(userInResponse.getAddress());
                    eEmail.setText(userInResponse.getEmail());
                    ePhone.setText(userInResponse.getContact());
                    ePassword.setText(userInResponse.getPassword());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(com.example.achint.ecommerce.View.ProfileActivity.this, "Failed to fetch", Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();


        usersInterface = MainController.getInstance().getClientForLogin().create(UsersInterface.class);
        getUser(user.get(SessionManagement.KEY_EMAIL));

        eFname = findViewById(R.id.e_fname);
        eLname = findViewById(R.id.e_lname);
        eAddress = findViewById(R.id.e_address);
        ePhone = findViewById(R.id.e_phone);
        eEmail = findViewById(R.id.e_email);
        eSave = findViewById(R.id.bt_save);
        ePassword = findViewById(R.id.e_pass);



    }

}
