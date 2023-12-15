package com.Derek.courseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LessonDetailActivity extends AppCompatActivity {

    private static final int QUIZ_REQUEST_CODE = 2;
    private static final String LESSON_PROGRESS_KEY = "lessonProgress";
    private int lessonProgress;  // Declare lessonProgress

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Get the lessonId from the intent
        int lessonId = getIntent().getIntExtra("lessonId", -1);

        // Display the content of the lesson (not shown here)

        // Set up a button click listener to start the quiz
        Button startQuizButton = findViewById(R.id.startQuizButton);
        startQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(LessonDetailActivity.this, QuizActivity.class);
            intent.putExtra("lessonId", lessonId);
            startActivityForResult(intent, QUIZ_REQUEST_CODE);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save relevant data for restoring state
        outState.putInt(LESSON_PROGRESS_KEY, lessonProgress);
    }
}
