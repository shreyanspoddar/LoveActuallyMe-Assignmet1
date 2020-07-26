package com.shreyans.poddar.loveactuallyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String email ;
    String password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final TextView txtEmail= findViewById(R.id.textemail);
        final TextView enterPassword = findViewById(R.id.Password);
        Button btn_create = findViewById(R.id.button_login);


        mAuth = FirebaseAuth.getInstance();
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString().trim();
                password = enterPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this,"Please Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6 && password.length()<12){
                    Toast.makeText(SignUp.this,"Please Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password!=null) {
                    authy();
                }
            }
        });

    }
    private void  authy(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(SignUp.this,"Registration complete",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SignUp.this,"Authentication Failed "+task.getException(),Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
