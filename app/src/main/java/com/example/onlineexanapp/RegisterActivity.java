package com.example.onlineexanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword2, etConfirmPassword, etMobileNo;
    Button btnRegister,btngotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword2 = findViewById(R.id.etPassword2);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etMobileNo = findViewById(R.id.etMobileNo);
        btnRegister = findViewById(R.id.btnRegister);
        btngotoLogin = findViewById(R.id.btngotoLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String emailid = etEmail.getText().toString();
                String password = etPassword2.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                String mobileno = etMobileNo.getText().toString();

                if (name.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Name Field is Blank", Toast.LENGTH_LONG).show();
                } else if (emailid.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Email Id Field is Blank", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Password Field is Blank", Toast.LENGTH_LONG).show();
                } else if (confirmPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Confirm Password Field is Blank", Toast.LENGTH_LONG).show();
                } else if (mobileno.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Mobile No Field is Blank", Toast.LENGTH_LONG).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Confirm Password Not Matched", Toast.LENGTH_LONG).show();
                } else {
                    insertData(name, emailid, password, mobileno);
                }
            }
        });

        btngotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(i);
            }
        });

    }

    void insertData(final String name, final String emailid, final String password, final String mobileno) {
        String url = "http://192.168.0.102/android/onlineexam/register.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("1")) {
                    Toast.makeText(RegisterActivity.this, "Email Id already registered", Toast.LENGTH_LONG).show();
                } else if (response.trim().equals("2")) {
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    finish();
                    Intent i = new Intent(RegisterActivity.this, LogInActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(RegisterActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("name", name);
                hm.put("emailid", emailid);
                hm.put("password", password);
                hm.put("mobileno", mobileno);
                return hm;
            }
        };
        queue.add(sr);
    }
}