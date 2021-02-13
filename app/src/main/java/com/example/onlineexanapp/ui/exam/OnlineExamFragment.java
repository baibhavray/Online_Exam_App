package com.example.onlineexanapp.ui.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.onlineexanapp.MainActivity;
import com.example.onlineexanapp.R;

import java.util.ArrayList;

public class OnlineExamFragment extends Fragment {
    Context context;
    GridView gvCourses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_online_exam, container, false);

        //webView = view.findViewById(R.id.webView);
        //webView.loadUrl("http://192.168.0.102/android/onlineexam/get_questions.php");
        //this helps to load the URL directly

        gvCourses = view.findViewById(R.id.gvCourses);

        ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();
        coursesArrayList.add(new Courses(R.drawable.cse,"CSE"));
        coursesArrayList.add(new Courses(R.drawable.ece,"ECE"));
        coursesArrayList.add(new Courses(R.drawable.mechanical,"Mechanical"));
        coursesArrayList.add(new Courses(R.drawable.civil,"Civil"));
        coursesArrayList.add(new Courses(R.drawable.eee,"EEE"));

        coursesArrayList.add(new Courses(R.drawable.chemical,"Chemical"));
        coursesArrayList.add(new Courses(R.drawable.it,"IT"));
        coursesArrayList.add(new Courses(R.drawable.fashion,"Fashion"));
        coursesArrayList.add(new Courses(R.drawable.automoblie,"Automobile"));
        coursesArrayList.add(new Courses(R.drawable.agricultural,"Agriculture"));

        coursesArrayList.add(new Courses(R.drawable.biomedical,"Biomedical"));
        coursesArrayList.add(new Courses(R.drawable.robotics,"Robotics"));
        coursesArrayList.add(new Courses(R.drawable.environmental,"Environmental"));
        coursesArrayList.add(new Courses(R.drawable.marine,"Marine"));
        coursesArrayList.add(new Courses(R.drawable.mining,"Mining"));

        CoursesAdapter aa = new CoursesAdapter(context,coursesArrayList);
        gvCourses.setAdapter(aa);

        gvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
               // getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new cse()).commit();
                CardView cardView = (CardView) v;
                LinearLayout linearLayout = (LinearLayout)  cardView.getChildAt(0);
                TextView textView = (TextView) linearLayout.getChildAt(1);

                Toast.makeText(context,"Selected Course : " +textView.getText(),Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }


}
