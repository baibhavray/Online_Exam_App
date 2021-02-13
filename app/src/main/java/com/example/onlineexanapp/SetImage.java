package com.example.onlineexanapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SetImage extends Fragment {
    SharedPreferences sp;
    Context context;
    String emailid;
    TextView tvName;
    ImageView imageView;
    NavigationView navigationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        sp = context.getSharedPreferences("logininfo",context.MODE_PRIVATE);
        emailid = sp.getString("emailid",null);
        View view =  inflater.inflate(R.layout.nav_header_main,container,false);

        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        imageView = (ImageView)hView.findViewById(R.id.imageView);
        tvName = (TextView)hView.findViewById(R.id.tvName);
        tvName.setText("ABCDE");
        imageView.setImageResource(R.drawable.tictactoe);
        //getProfilePic(emailid);
        tvName.setText("abcse");
        return view;
    }
    void getProfilePic(final String emailid){
        String url = "http://192.168.0.102/android/onlineexam/get_images.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String name = jsonObject.getString("name");
                    String image = jsonObject.getString("image");
//                    tvName.setText("Name : "+name);
//                    imageView.setImageURI(Uri.parse(image));

//                    tvName.setText("new text");
//                    imageView.setImageResource(Integer.parseInt(image));
//                    navigationView.setNavigationItemSelectedListener(this);


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


















//
//
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toolbar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.onlineexanapp.ui.home.ImageInfo;
//import com.google.android.material.navigation.NavigationView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SetImage extends AppCompatActivity {
//    SharedPreferences sp;
//    ImageView imageView;
//    TextView tvName;
//    String emailid;
//    NavigationView navigationView;
//    Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.nav_header_main);
//
//        sp = getSharedPreferences("logininfo",MODE_PRIVATE);
//        emailid = sp.getString("emailid",null);
//
////        imageView = findViewById(R.id.imageView);
////        tvName = findViewById(R.id.tvName);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        //setSupportActionBar(toolbar);
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
//        imageView = (ImageView)hView.findViewById(R.id.imageView);
//        tvName = (TextView)hView.findViewById(R.id.tvName);
//        tvName.setText("ABCDE");
//        imageView.setImageResource(R.drawable.tictactoe);
//
//
//        //getProfilePic(emailid);
//    }
//
//
//
//    void getProfilePic(final String emailid){
//        String url = "http://192.168.0.102/android/onlineexam/get_images.php";
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    JSONObject jsonObject = jsonArray.getJSONObject(0);
//                    String name = jsonObject.getString("name");
//                    String image = jsonObject.getString("image");
////                    tvName.setText("Name : "+name);
////                    imageView.setImageURI(Uri.parse(image));
//
////                    tvName.setText("new text");
////                    imageView.setImageResource(Integer.parseInt(image));
////                    navigationView.setNavigationItemSelectedListener(this);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> hm = new HashMap<>();
//                hm.put("emailid", emailid);
//                return hm;
//            }
//        };
//        queue.add(sr);
//    }
//}