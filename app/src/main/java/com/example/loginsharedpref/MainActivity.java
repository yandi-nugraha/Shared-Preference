package com.example.loginsharedpref;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    private Boolean isLogin = false;

    private static SharedPreferences sharedPreferences;
    private final String sharedPrefFile = "com.example.loginsharedpref";

    private static final String LOGIN_KEY = "login-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);

        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean(LOGIN_KEY, false);

        if (isLogin) {
            toSecondActivity();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("pakjoko") && password.getText().toString().equals("cuan")) {
                    saveLogin();
                    toSecondActivity();
                } else {
                    showAlertDialog();
                }
            }
        });
    }

    private void saveLogin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_KEY, true);
        editor.apply();
    }

    private void toSecondActivity() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public static void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_KEY, false);
        editor.apply();
    }

    public void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Username atau Password yang Anda Masukan Salah!");
        alertDialog.setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.show();
    }
}