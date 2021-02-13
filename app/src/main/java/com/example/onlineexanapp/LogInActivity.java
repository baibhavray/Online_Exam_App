package com.example.onlineexanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    EditText etEmailId, etPassword;
    Button btnSignIn,btnSignup;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sp = getSharedPreferences("logininfo",MODE_PRIVATE);
        edit = sp.edit();

        etEmailId = findViewById(R.id.etEmailId);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignin);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = etEmailId.getText().toString();
                String password = etPassword.getText().toString();

                edit.putString("emailid",emailid);
                edit.putString("password",password);
                edit.commit();

                if (emailid.length() == 0) {
                    Toast.makeText(LogInActivity.this, "Email Id Field is Blank", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(LogInActivity.this, "Password Field is Blank", Toast.LENGTH_LONG).show();
                } else {
                    checkCredential(emailid, password);
                    sendMailId(emailid);
                }

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(i2);
            }
        });
    }

    void checkCredential(final String emailid, final String password) {
        String url = "http://192.168.0.102/android/onlineexam/ckecklogin.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("valid")) {
                    Intent i = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LogInActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogInActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("emailid", emailid);
                hm.put("password", password);
                return hm;
            }
        };
        queue.add(sr);
    }


    void sendMailId(final String emailid) {
        String url = "http://192.168.0.102/android/onlineexam/result.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("emailid", emailid);
                return hm;
            }
        };
        queue.add(sr);
    }

}
