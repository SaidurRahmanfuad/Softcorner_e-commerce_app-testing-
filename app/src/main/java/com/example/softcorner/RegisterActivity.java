package com.example.softcorner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_email,register_password,register_phone;
    private Button register_button;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        register_email = (EditText) findViewById(R.id.register_email);
        register_password =(EditText) findViewById(R.id.register_password);
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_button =(Button) findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String signup_email,signup_password,signup_phone;
                signup_email=register_email.getText().toString();
                signup_password=register_password.getText().toString();
                signup_phone=register_phone.getText().toString();

                if(!TextUtils.isEmpty(signup_email) && !TextUtils.isEmpty(signup_password) && !TextUtils.isEmpty(signup_phone))
                {
                    mAuth.createUserWithEmailAndPassword(signup_email,signup_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                if(task.isSuccessful())
                                {
                                   Intent i = new Intent(RegisterActivity.this,HomeActivity.class);
                                   startActivity(i);
                                   finish();
                                }
                                  else
                                      {
                                          String error =task.getException().getMessage();
                                          Toast.makeText(RegisterActivity.this,"Error"+ error ,Toast.LENGTH_SHORT).show();

                                      }
                                }
                            });
                }
                else
                    {
                       Toast.makeText(RegisterActivity.this,"Please Enter The value",Toast.LENGTH_SHORT).show();


                    }
            }
        });
    }
}




