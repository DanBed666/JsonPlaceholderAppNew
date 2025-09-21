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

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity
{
    EditText emailET;
    EditText passwordET;
    Button register;
    TextView login;
    private FirebaseAuth mAuth;
    EditText nameET;
    EditText usernameET;
    EditText cityET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailET = findViewById(R.id.email_log);
        passwordET = findViewById(R.id.password_log);
        register = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.tv_login_btn);
        mAuth = FirebaseAuth.getInstance();

        nameET = findViewById(R.id.name);
        usernameET = findViewById(R.id.username);
        cityET = findViewById(R.id.city);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                signUp(email, password);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public void signUp(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SIGNUP", "signUpWithMailAndPassword:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else
                {
                    // If sign in fails, display a message to the user.
                    Log.w("SIGNUP", "signUpWithMailAndPassword:failure", task.getException());
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
            startActivity(new Intent(this, LoginActivity.class));
            addUser();
            user.sendEmailVerification();
            Toast.makeText(this,"Aby zakończyć rejestrację, potwierdź swój adres email",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"You Didnt signed up",Toast.LENGTH_LONG).show();
        }
    }

    public void addUser()
    {
        String email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        String uId = mAuth.getCurrentUser().getUid();

        String name = nameET.getText().toString();
        String username = usernameET.getText().toString();
        String city = cityET.getText().toString();

        UserDB user = new UserDB(uId, name, username, email, city);

        //UserViewModel userViewModel = new UserViewModel();
        //userViewModel.createNewUser(user);
    }
}