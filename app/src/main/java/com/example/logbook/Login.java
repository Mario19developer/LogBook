package com.example.logbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView registrar;
    boolean isEmailValid, isPasswordValid;//step true or false
    TextInputLayout emailError, passError;

    private String emaill = "";
    private String passwordl = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Lemail);
        password = findViewById(R.id.Lpassword);
        login = findViewById(R.id.Llogin);
        registrar = findViewById(R.id.Lregitrar);

        emailError = (TextInputLayout) findViewById(R.id.EmailError);
        passError = (TextInputLayout) findViewById(R.id.PassError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emaill = email.getText().toString();
                passwordl = password.getText().toString();
                Validacion();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Registrar.class);
                startActivity(intent);
            }
        });
    }

    private void Validacion() {
        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isEmailValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Por favor Espere...", Toast.LENGTH_SHORT).show();
            LoginUser();
        }
    }

    private void LoginUser() {
        mAuth.signInWithEmailAndPassword(emaill, passwordl).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenido " + emaill, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "No se pudo iniciar sesion comprueba los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!= null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }
}