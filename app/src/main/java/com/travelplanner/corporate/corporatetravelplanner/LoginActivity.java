package com.travelplanner.corporate.corporatetravelplanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidEmailAddress;
import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidPassword;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister,tvForgotPassword, tvLogo;
    ImageView ivLogin;
    EditText etEmailId,etPassword;
    String  password,emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailId =  findViewById(R.id.etEmailId);
        etPassword =  findViewById(R.id.etPassword);
        ivLogin =  findViewById(R.id.ivLogin);
        tvRegister =  findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvLogo = (TextView)findViewById(R.id.tvLogo);
        // Changing Font of Corporate Travel Planner
        Typeface ocrExtendedFont = Typeface.createFromAsset(getAssets(),  "fonts/ocrExtended.TTF");
        tvLogo.setTypeface(ocrExtendedFont);


        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailid = etEmailId.getText().toString();
                password = etPassword.getText().toString();

                if (emailid.isEmpty()) {
                    etEmailId.setError("Please enter your Email-Id");
                } else {
                    if (password.isEmpty()) {
                        etPassword.setError("Please enter Password");
                    } else {
                        if (isValidEmailAddress(emailid)) {
                            if (isValidPassword(password)) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                login(emailid,password);
                            } else {
                                etPassword.setError("Password is too short");
                            }
                        } else {
                            etEmailId.setError("Please enter a valid Email Address");
                        }
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(final String email, final String passwo){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
