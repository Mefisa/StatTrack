package com.heavylift.stattrack.stattrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.String;

public class SignupPage extends AppCompatActivity {
    String name, email, password1, password2;

    EditText nameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText retypePasswordInput;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mRootReference = mDatabase.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput1);
        retypePasswordInput = (EditText) findViewById(R.id.passwordInput2);

        submitButton = (Button) findViewById(R.id.submitInput);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                email = emailInput.getText().toString();
                password1 = passwordInput.getText().toString();
                password2 = retypePasswordInput.getText().toString();
                showToast(name);
                showToast(email);
                showToast(password1);
                showToast(password2);
                if (!password1.equals(password2)) {
                    showToast("The passwords do not match");
                } else {
                    showToast("Success!");
                }
                startActivity(new Intent(SignupPage.this, exercise_list.class));
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(SignupPage.this, text, Toast.LENGTH_SHORT).show();
    }

}
