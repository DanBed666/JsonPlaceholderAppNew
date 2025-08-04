package com.example.jsonplaceholderappnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    EditText emailET;
    EditText passwordET;
    Button login;
    TextView register;
    private FirebaseAuth mAuth;
    Button forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailET = findViewById(R.id.email_reg);
        passwordET = findViewById(R.id.password_reg);
        login = findViewById(R.id.signInBtn);
        register = findViewById(R.id.tv_register_btn);
        mAuth = FirebaseAuth.getInstance();
        forgetPassword = findViewById(R.id.tv_password_change_btn);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                signIn(email, password);
            }
        });

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    public void signIn(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SIGNIN", "signInWithMailAndPassword:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    // If sign in fails, display a message to the user.
                    Log.w("SIGNIN", "signInWithMailAndPassword:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    public void updateUI(FirebaseUser user)
    {
        if (user != null)
        {
            if (user.isEmailVerified())
            {
                Toast.makeText(this, "You Signed In successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, HomepageActivity.class));
            }
        }
        else
        {
            Toast.makeText(this,"You Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }
}