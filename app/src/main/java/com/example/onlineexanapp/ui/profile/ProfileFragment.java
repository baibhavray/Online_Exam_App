package com.example.onlineexanapp.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.onlineexanapp.LogInActivity;
import com.example.onlineexanapp.R;
import com.example.onlineexanapp.ui.exam.OnlineExamFragment;
import com.example.onlineexanapp.ui.updateprofile.UpdateProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    SharedPreferences sp;
    Context context;
    String emailid;
    TextView tvName,tvEmailId,tvMobileNo,tvAddress,tvGender;
    ImageView imageViewFP;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        sp = context.getSharedPreferences("logininfo",context.MODE_PRIVATE);
        emailid = sp.getString("emailid",null);

        view =  inflater.inflate(R.layout.fragment_profile,container,false);
        tvName = view.findViewById(R.id.tvName);
        tvEmailId = view.findViewById(R.id.tvEmailId);
        tvMobileNo = view.findViewById(R.id.tvMobileNo);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvGender = view.findViewById(R.id.tvGender);
        imageViewFP = view.findViewById(R.id.imageViewFP);

        getUserDetails(emailid);

        return view;
    }
    void getUserDetails(final String emailid){
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
                    tvName.setText("Name : "+name);
                    tvEmailId.setText("Email Id : "+emailid);
                    tvMobileNo.setText("Mobile No : "+mobileno);
                    tvAddress.setText("Address : "+address);
                    tvGender.setText("Gender : "+gender);
                    Glide.with(view).load(image).into(imageViewFP);

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
