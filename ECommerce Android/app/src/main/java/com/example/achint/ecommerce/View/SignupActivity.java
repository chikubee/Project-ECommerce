package com.example.achint.ecommerce.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achint.ecommerce.Controller.MainController;
import com.example.achint.ecommerce.FormValidaton.Validation;
import com.example.achint.ecommerce.Interface.UsersInterface;
import com.example.achint.ecommerce.Model.Users;
import com.example.achint.ecommerce.R;
import com.example.achint.ecommerce.Sessions.AlertDialogManager;
import com.example.achint.ecommerce.Sessions.SessionManagement;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    UsersInterface usersInterface;
    SessionManagement session;
    AlertDialogManager alert = new AlertDialogManager();
    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etContact;
    EditText etEmail;
    EditText etPassword;
    Button signup;

    private void addUser(Users users){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        Call<Users> call = usersInterface.createUser(users);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.code() == 200) {
                    Users userInResponse = response.body();
                    session = new SessionManagement(getApplicationContext());
                    session.createLoginSession(userInResponse.getFirstname(), userInResponse.getEmail(), userInResponse.getId());
                    Toast.makeText(SignupActivity.this,"registered successfully:)",Toast.LENGTH_LONG).show();
                    alert.showAlertDialog(SignupActivity.this, "Successful SignUp, verify your email for login.", "Welcome to Easy Buy", true);
                    progressDialog.dismiss();
                    Intent i = new Intent(SignupActivity.this,NavigationActivity.class);
                    startActivity(i);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(SignupActivity.this,"registration failed :(",Toast.LENGTH_LONG).show();
                alert.showAlertDialog(SignupActivity.this, "SignUp Failed", "Please try again..", false);
                progressDialog.dismiss();
            }
    });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.isPassword(etPassword, true)) ret = false;
        if (!Validation.isEmailAddress(etEmail, true)) ret = false;
        if (!Validation.isPhoneNumber(etContact, false)) ret = false;

        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFirstName = findViewById(R.id.et_firstname);
        etLastName = findViewById(R.id.et_lastname);
        etAddress = findViewById(R.id.et_address);
        etContact = findViewById(R.id.et_contact);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        signup = findViewById(R.id.bt_signup);


        etEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etEmail, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPassword(etPassword, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){ }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(etContact, false);
            }
        });



        usersInterface = MainController.getInstance().getClientForLogin().create(UsersInterface.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation ()){
                    Users users = new Users();
                    users.setFirstname(etFirstName.getText().toString());
                    users.setLastname(etLastName.getText().toString());
                    users.setAddress(etAddress.getText().toString());
                    users.setContact(etContact.getText().toString());
                    users.setEmail(etEmail.getText().toString());
                    users.setPassword(etPassword.getText().toString());
                    etFirstName.setText("");
                    etLastName.setText("");
                    etContact.setText("");
                    etAddress.setText("");
                    etEmail.setText("");
                    etPassword.setText("");
                    addUser(users);
                }

                else{
                    Toast.makeText(SignupActivity.this, "Form contains error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
