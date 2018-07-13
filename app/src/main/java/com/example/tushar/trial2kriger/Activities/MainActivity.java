package com.example.tushar.trial2kriger.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tushar.trial2kriger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText email;

    private EditText password;
    private Button login;
    private Button register;
    private Button skip;
    private Button mobile;

    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        email = ( EditText ) findViewById(R.id.emailID);
        password = ( EditText ) findViewById(R.id.passID);
        login = ( Button ) findViewById(R.id.login);
        register = ( Button ) findViewById(R.id.regiter);
        skip = ( Button ) findViewById(R.id.skipID);
        mobile=(Button )findViewById(R.id.logPhoneID);

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PhoneAuthActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
                finish();
            }
        });


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    Toast.makeText(MainActivity.this, "SIGNED IN", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Signed In", Toast.LENGTH_LONG).show();
                }

            }
        };

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAnony();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString()))) {
                    String emailS = email.getText().toString();
                    String passS = password.getText().toString();

                    loginEmail(emailS, passS);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginEmail(String email1,String pass1)
    {
        auth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"SISGNED IN",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,AddImagesActivity.class));
                    finish();
                }else
                {
                    Toast.makeText(MainActivity.this,"FAILED SIGN IN",Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void loginAnony()
    {
        auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"SIGNED IN",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,ImagesListActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}

