package com.travelplanner.corporate.corporatetravelplanner;

import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidEmailAddress;
import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidName;
import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidPassword;
import static com.travelplanner.corporate.corporatetravelplanner.Utils.isValidPhone;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView tvAlreadyReg, tvLogo;
    Button bRegister;
    TextInputLayout tilName,tilEmailId,tilPhone,tilPassword;
    String  name,emailid,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvLogo = (TextView)findViewById(R.id.tvLogo);
        // Changing Font of Corporate Travel Planner
        Typeface ocrExtendedFont = Typeface.createFromAsset(getAssets(),  "fonts/ocrExtended.TTF");
        tvLogo.setTypeface(ocrExtendedFont);


        tilName = findViewById(R.id.tilName);
        tilEmailId = findViewById(R.id.tilEmailId);
        tilPhone =  findViewById(R.id.tilPhone);
        tilPassword = findViewById(R.id.tilPassword);
        bRegister = findViewById(R.id.bRegister);
        tvAlreadyReg = findViewById(R.id.tvAlreadyReg);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = tilName.getEditText().getText().toString();
                emailid = tilEmailId.getEditText().getText().toString();
                phone = tilPhone.getEditText().getText().toString();
                password = tilPassword.getEditText().getText().toString();

                if(name.isEmpty()) {
                    tilName.getEditText().setError("Please enter your Name");
                }else if (emailid.isEmpty()) {
                    tilEmailId.getEditText().setError("Please enter your Email-Id");
                } else if (phone.isEmpty()) {
                    tilPhone.getEditText().setError("Please enter your phone no.");
                }else if (password.isEmpty()) {
                    tilPassword.getEditText().setError("Please enter Password");
                }else if(!isValidName(name)) {
                    tilName.getEditText().setError("Name is too short");
                }else if (!isValidEmailAddress(emailid)) {
                    tilEmailId.getEditText().setError("Please enter a valid Email Address");
                } else if (!isValidPhone(phone)) {
                    tilPhone.getEditText().setError("Please enter a valid phone no.");
                }else if (!isValidPassword(password)) {
                    tilPassword.getEditText().setError("Password is too short");
                }else {
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });


        tvAlreadyReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}

