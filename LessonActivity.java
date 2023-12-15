package com.Derek.courseapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;



public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Use WebView to display HTML content
        WebView webView = findViewById(R.id.lessonWebView);
        String lessonContent = "<html><body><h1>Introduction to Python</h1><p>Your lesson content goes here.</p></body></html>";
        webView.loadDataWithBaseURL(null, lessonContent, "text/html", "utf-8", null);
    }
}
