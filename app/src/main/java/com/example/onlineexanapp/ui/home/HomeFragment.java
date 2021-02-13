package com.example.onlineexanapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.onlineexanapp.R;
import com.example.onlineexanapp.ui.exam.OnlineExamFragment;
import com.example.onlineexanapp.ui.updateprofile.UpdateProfile;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    Button btnStartExam;
    private List<ImageInfo> imageInfos;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = (SliderLayout) view.findViewById(R.id.imageSlider);
        btnStartExam = view.findViewById(R.id.btnStartExam);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.WORM);

        btnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//This is used to replace a fragment with another fragment
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment, new OnlineExamFragment());
//                fragmentTransaction.commit();


//The above things are done in one line.
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new OnlineExamFragment()).commit();

            }
        });

        imageInfos=new ArrayList<>();
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();
        return view;
    }
    void setSliderViews(){
        String url = "http://192.168.0.102/android/onlineexam/get_slider.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                            ImageInfo listData = new ImageInfo(jsonObject.getString("imageurl"));
                            imageInfos.add(listData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i <imageInfos.size(); i++) {
                    final ImageInfo ld = imageInfos.get(i);
                    SliderView sliderView = new SliderView(context);
                    sliderView.setImageUrl(ld.getImageurl());
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    sliderLayout.addSliderView(sliderView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(sr);
    }
}