package com.example.medonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
EditText editTextgetmail;
 Button btnresetpassword;
ProgressBar progressBar;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editTextgetmail=findViewById(R.id.getmail);
        btnresetpassword=findViewById(R.id.btnresetpassword);
        progressBar=findViewById(R.id.inprogress);

        auth=FirebaseAuth.getInstance();

        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }

            private void resetPassword() {
                String email=editTextgetmail.getText().toString().trim();
                if(email.isEmpty()){
                    editTextgetmail.setError("email is required");
                    editTextgetmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextgetmail.setError("please provide a valid email");
                    editTextgetmail.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgetPassword.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(forgetPassword.this,"try again something wrong happened",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}