package com.example.onlineexanapp.ui.changepassword;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlineexanapp.R;
import com.example.onlineexanapp.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    SharedPreferences sp;
    Context context;
    String emialid;
    EditText etCurrentPassword,etNewPassword,etConfirmPassword;
    Button btnSignin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        sp = context.getSharedPreferences("logininfo", context.MODE_PRIVATE);
        emialid = sp.getString("emailid",null);

        View view = inflater.inflate(R.layout.fragment_change_password,container,false);
        etCurrentPassword = view.findViewById(R.id.etCurrectPassword);
        etNewPassword = view.findViewById(R.id.etNewPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnSignin = view.findViewById(R.id.btnSignin);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curpassword = etCurrentPassword.getText().toString();
                String newpassword = etNewPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (curpassword.length() == 0){
                    Toast.makeText(context,"Currecnt password field is empty",Toast.LENGTH_LONG).show();
                }else if (newpassword.length() == 0){
                    Toast.makeText(context,"New password field is empty",Toast.LENGTH_LONG).show();
                }else if (confirmPassword.length() == 0){
                    Toast.makeText(context,"Confirm password field is empty",Toast.LENGTH_LONG).show();
                }else if (confirmPassword == newpassword){
                    Toast.makeText(context,"New password and Confirm password are not same",Toast.LENGTH_LONG).show();
                }else {
                    updatePassword(curpassword,newpassword);

                }

            }
        });

        return view;
    }
    void updatePassword(final String curpassword, final String newpassword){
        String url = "http://192.168.0.102/android/onlineexam/update_password.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("emailid", emialid);
                hm.put("curpassword", curpassword);
                hm.put("newpassword", newpassword);
                return hm;
            }
        };
        queue.add(sr);
    }
}
