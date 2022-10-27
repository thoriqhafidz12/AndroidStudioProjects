package com.example.dasboardku;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin,btnRegis;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegis = (Button) findViewById(R.id.btnRegis);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString(); //ambil dr android
                String pass = password.getText().toString();
                SharedPreferences prefer = getSharedPreferences("MYDATA",MODE_PRIVATE);
                String username = prefer.getString("user",null); //dari prefer MYDATA
                String password = prefer.getString("pass",null);
                System.out.println("User : "+user+" = "+username + ", dan pass = "+pass+"="+
                        password);
                if ((username != null && password != null) && (username.equals(user) &&
                        password.equals(pass)))
                { //username and password are present, do your stuff
                    Intent iMain = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(iMain);
                }else
                {       Toast.makeText(getApplicationContext(),
                        "Belum register....!!! atau User & Password tidak cocok...!!",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegis = new Intent(MainActivity.this,
                        RegisterActivity.class);startActivity(iRegis);
                finish();
            }
        });
    }
}
