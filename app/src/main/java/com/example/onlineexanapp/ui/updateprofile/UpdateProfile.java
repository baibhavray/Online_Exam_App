package com.example.onlineexanapp.ui.updateprofile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.bumptech.glide.Glide;
import com.example.onlineexanapp.MainActivity;
import com.example.onlineexanapp.R;
import com.example.onlineexanapp.ui.exam.OnlineExamFragment;
import com.example.onlineexanapp.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends Fragment {
    SharedPreferences sp;
    Context context;
    String emialid;
    TextView tvEmailIdUP;
    Button btnUpdateUP;
    EditText etNameUP, etPhoneNoUP, etAddressUP;
    RadioGroup rgGender;
    RadioButton radioButton, rbMale, rbFemale, rbOther;
    ImageView imageViewUP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_update_profile, container, false);

        context = getContext();
        sp = context.getSharedPreferences("logininfo", context.MODE_PRIVATE);
        emialid = sp.getString("emailid", null);

        tvEmailIdUP = view.findViewById(R.id.tvEmailIdUP);
        etNameUP = view.findViewById(R.id.etNameUP);
        etPhoneNoUP = view.findViewById(R.id.etPhoneNoUP);
        etAddressUP = view.findViewById(R.id.etAddressUP);
        btnUpdateUP = view.findViewById(R.id.btnUpdateUP);
        rgGender = view.findViewById(R.id.rgGender);
        rbMale = view.findViewById(R.id.rbMale);
        rbFemale = view.findViewById(R.id.rbFemale);
        rbOther = view.findViewById(R.id.rbOther);

        imageViewUP = view.findViewById(R.id.imageViewUP);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = view.findViewById(i);
            }
        });

        tvEmailIdUP.setText("Email Id: " + emialid);
        getUserDetails(emialid);


        btnUpdateUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etNameUP.getText().toString();
                String mobileno = etPhoneNoUP.getText().toString();
                String address = etAddressUP.getText().toString();
                String gender = radioButton.getText().toString();
                updateProfile(name, mobileno, address, gender);
            }
        });

        return view;
    }

    public void updateProfile(final String name, final String mobileno, final String address, final String gender) {
        String url = "http://192.168.0.102/android/onlineexam/update_profile.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

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
                hm.put("name", name);
                hm.put("mobileno", mobileno);
                hm.put("address", address);
                hm.put("gender", gender);
                return hm;
            }
        };
        queue.add(sr);
    }

    void getUserDetails(final String emailid) {
        String url = "http://192.168.0.102/android/onlineexam/get_user_details.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String name = jsonObject.getString("name");
                    String mobileno = jsonObject.getString("mobileno");
                    String address = jsonObject.getString("address");
                    String gender = jsonObject.getString("gender");
                    String image = jsonObject.getString("image");


                    etNameUP.setText(name);
                    etPhoneNoUP.setText(mobileno);
                    etAddressUP.setText(address);

                    if (gender.equalsIgnoreCase("Male")) {
                        rgGender.check(R.id.rbMale);
                    } else if (gender.equalsIgnoreCase("Female")) {
                        rgGender.check(R.id.rbFemale);
                    } else if (gender.equals("Other")) {
                        rgGender.check(R.id.rbOther);
                    }

                    Glide.with(context).load(image).into(imageViewUP);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
