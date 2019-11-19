package com.heavylift.stattrack.stattrack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.String;

public class SignupPage extends AppCompatActivity {

    private EditText mEmail_editTxt;
    private EditText mPassword_editTxt;

    private Button mSignIn_btn;
    private Button mRegister_btn;
    private Button mBack_btn;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        mAuth = FirebaseAuth.getInstance();

        mEmail_editTxt = (EditText) findViewById(R.id.email_editTxt);
        mPassword_editTxt = (EditText) findViewById(R.id.password_editTxt);
        mSignIn_btn = (Button) findViewById(R.id.signin_btn);
        mRegister_btn = (Button) findViewById(R.id.register_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);
        mProgress_bar = (ProgressBar) findViewById(R.id.loading_progressBar);
        mProgress_bar.setVisibility(View.GONE);

        mSignIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(mEmail_editTxt.getText().toString(),
                        mPassword_editTxt.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignupPage.this, "Signing in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupPage.this, ExerciseList.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignupPage.this, "Sign in failed!"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                if (isBadPassword()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(mEmail_editTxt.getText().toString(),
                        mPassword_editTxt.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignupPage.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
                        inProgress(false);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignupPage.this, "Registration failed!"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }

    private void inProgress(boolean x) {
        if (x) {
            mProgress_bar.setVisibility(View.VISIBLE);
            mBack_btn.setEnabled(false);
            mSignIn_btn.setEnabled(false);
            mRegister_btn.setEnabled(false);
        } else {
            mProgress_bar.setVisibility(View.GONE);
            mBack_btn.setEnabled(true);
            mSignIn_btn.setEnabled(true);
            mRegister_btn.setEnabled(true);
        }
    }

    private boolean isEmpty(){
        if (TextUtils.isEmpty(mEmail_editTxt.getText().toString())) {
            mEmail_editTxt.setError("Required!");
            return true;
        }
        if (TextUtils.isEmpty(mPassword_editTxt.getText().toString())) {
            mPassword_editTxt.setError("Required!");
            return true;
        }
        return false;
    }

    private boolean isBadPassword(){
        String s = mPassword_editTxt.getText().toString();
        boolean hasCharacter = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        int length = s.length();
        for (int i = 0; i < length; i++){
            int asciiVal = (int) s.charAt(i);
            if ((asciiVal >= 65 && asciiVal <= 90)|| (asciiVal >= 97 && asciiVal <= 122)){
                hasCharacter = true;
            } else if (asciiVal >= 48 && asciiVal <= 57){
                hasNumber = true;
            } else if (asciiVal >= 33 && asciiVal <= 126) {
                hasSpecial = true;
            }
        }
        if (hasCharacter && hasNumber && hasSpecial) return false;
        mPassword_editTxt.setError("Must contain number, character and special character");
        return true;
    }



}
