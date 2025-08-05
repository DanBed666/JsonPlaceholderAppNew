package com.example.jsonplaceholderappnew;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ChangeEmailActivity extends AppCompatActivity
{
    EditText current_mail;
    EditText passwordET;
    EditText new_mail;
    Button send_link;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        current_mail = findViewById(R.id.current_mail);
        passwordET = findViewById(R.id.password);
        new_mail = findViewById(R.id.new_mail);
        send_link = findViewById(R.id.send_link);

        send_link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                changeEmail();
            }
        });
    }

    public void changeEmail()
    {
        FirebaseUser user = mAuth.getCurrentUser();

        String email = current_mail.getText().toString();
        String password = passwordET.getText().toString();
        AuthCredential authCredential = EmailAuthProvider.getCredential(email, password);

        assert user != null;
        user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    Log.d("AUTH", "Re-authenticated");
                    FirebaseUser user = mAuth.getCurrentUser();
                    String newEmail = new_mail.getText().toString();

                    assert user != null;
                    user.verifyBeforeUpdateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Log.d("EMAILVER", "Wysłano link weryfikacyjny!");
                                Toast.makeText(getApplicationContext(), "Wysłano link weryfikacyjny na wskazany adres email!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Log.e("ERROR", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Weryfikacja nieudana! Niepoprawny email lub hasło!", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                }
            }
        });
    }
}