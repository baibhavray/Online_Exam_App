package com.example.onlineexanapp.ui.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.onlineexanapp.R;

import java.util.ArrayList;

public class CoursesAdapter extends ArrayAdapter<Courses> {
    public CoursesAdapter(@NonNull Context context, @NonNull ArrayList<Courses> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_gv_items, parent, false);
        }
        final Courses courses = getItem(position);

        TextView courseTV = listitemView.findViewById(R.id.gv_Titles);
        ImageView courseIV = listitemView.findViewById(R.id.gv_Images);
        courseTV.setText(courses.getTitle());
        courseIV.setImageResource(courses.getImage());

//        listitemView.findViewById(R.id.cardViewList).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(convertView.getContext(), courses.getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return listitemView;
    }
}

