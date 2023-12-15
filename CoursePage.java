package com.Derek.courseapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Derek.courseapp.adapter.CourseAdapter;
import com.Derek.courseapp.model.Course;
import com.Derek.courseapp.model.PlayList;
import com.Derek.courseapp.retrofit.ApiInterface;
import com.Derek.courseapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursePage extends AppCompatActivity {

    RecyclerView courseRecyclerView;
    ApiInterface apiInterface;
    CourseAdapter courseAdapter;

    TextView member, rating, name, price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_course_page);

        member = findViewById(R.id.members);
        rating = findViewById(R.id.rating);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);

        courseRecyclerView = findViewById(R.id.course_recycler);

        Call<List<Course>> call = apiInterface.getCourseContent();

        call.enqueue(new Callback<List<Course>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Course>> call, @NonNull Response<List<Course>> response) {

                List<Course> courseList = response.body();

                assert courseList != null;
                getCourseContent(courseList.get(0).getPlayList());
                //lets run it


                member.setText(courseList.get(0).getMember());
                rating.setText(courseList.get(0).getRating());
                name.setText(courseList.get(0).getCourseName());
                price.setText("₹ "+courseList.get(0).getPrice());

            }

            @Override
            public void onFailure(@NonNull Call<List<Course>> call, @NonNull Throwable t) {
                Toast.makeText(CoursePage.this, "No response from server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCourseContent(List<PlayList> playLists) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRecyclerView.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this); // Initialize adapter with context only
        courseRecyclerView.setAdapter(courseAdapter);

        // Set the playLists using the setter method
        courseAdapter.setPlayLists(playLists);
        courseAdapter.notifyDataSetChanged();
    }
}
