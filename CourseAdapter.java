package com.Derek.courseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Derek.courseapp.Course;
import com.Derek.courseapp.R;
import com.Derek.courseapp.model.PlayList;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private Context context;
    private List<PlayList> playLists;
    private Course course;
    public CourseAdapter(Context context) {
        this.context = context;
        this.playLists = new ArrayList<>(); // Initialize an empty list
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            throw new IllegalStateException("Context cannot be null. Make sure to initialize the adapter with a valid context.");
        }

        View view = LayoutInflater.from(context).inflate(R.layout.course_list_row_items, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        int i = position + 1;
        holder.contentNumber.setText("0" + i);
        holder.contentTime.setText(playLists.get(position).getTime());
        holder.contentName.setText(playLists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public void setCourses(List<Course> courses) {
        this.course = course;
        notifyDataSetChanged();
    }


    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView contentNumber, contentTime, contentName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            contentName = itemView.findViewById(R.id.content_title);
            contentTime = itemView.findViewById(R.id.content_time);
            contentNumber = itemView.findViewById(R.id.content_number);
        }
    }
}
