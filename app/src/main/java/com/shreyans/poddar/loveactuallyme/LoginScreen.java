package com.shreyans.poddar.loveactuallyme;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String email ;
    String password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        final TextView txtEmail= findViewById(R.id.textemail);
        final TextView enterPassword = findViewById(R.id.Password);
        Button login = findViewById(R.id.button_login);
        Button sign = findViewById(R.id. button_SignUp);
        mAuth = FirebaseAuth.getInstance();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginScreen.this, SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString().trim();
                password = enterPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginScreen.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginScreen.this,"Please Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6 && password.length()<12){
                    Toast.makeText(LoginScreen.this,"Please Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password!=null) {
                    authys();

                }
            }
        });

    }
    private void  authys(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginScreen.this,Insideapp.class);
                            startActivity(intent);
                            Toast.makeText(LoginScreen.this,"Login complete",Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(LoginScreen.this,"Login Failed "+task.getException(),Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Insideapp.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}