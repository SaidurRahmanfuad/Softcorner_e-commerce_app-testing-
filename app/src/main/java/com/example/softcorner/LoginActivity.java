package com.example.softcorner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
   EditText email,password;
   Button sign_in;
     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        sign_in = findViewById(R.id.login_button);



        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String signing_email,signing_password;
                signing_email=email.getText().toString();
                signing_password=password.getText().toString();

                /* to send data at Firebase// */
                if(!TextUtils.isEmpty(signing_email) && !TextUtils.isEmpty(signing_password))
                    mAuth.signInWithEmailAndPassword(signing_email, signing_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //go to new activity
                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                        startActivity(i);
                                        finish();
                                    } else {
                                        String error = Objects.requireNonNull(task.getException()).getMessage();
                                        Toast.makeText(LoginActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                else
                   {
                       Toast.makeText(getApplicationContext(),"Please Enter the require value",Toast.LENGTH_SHORT).show();
                   }
            }
        });
    }
     /* Onstart method for check if user is signed in */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser current_user = mAuth.getCurrentUser();
        if (current_user!=null)
        {
            Intent home_page = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(home_page);
            finish();
        }

    }
}
