package com.heavylift.stattrack.stattrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;

public class Login_Page extends AppCompatActivity {
    String name, email, password1, password2;

    EditText nameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText retypePasswordInput;

    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

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
                    showToast("Hello this isn't correct");
                } else {
                    showToast("Success!");
                }


            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(Login_Page.this, text, Toast.LENGTH_SHORT).show();
    }

}
