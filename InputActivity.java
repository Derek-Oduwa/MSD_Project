package com.Derek.courseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {
    private EditText inputEditText; // Corrected variable name
    private Button enterButton;
    private Button inputButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        inputEditText = findViewById(R.id.inputEditText); // Corrected ID
        enterButton = findViewById(R.id.enterButton);
        inputButton = findViewById(R.id.inputButton);

        enterButton.setOnClickListener(v -> onEnterButtonClick(inputEditText.getText().toString()));
        inputButton.setOnClickListener(v -> startMainActivity());

        EditText courseEditText = findViewById(R.id.editTextCourse); // Corrected variable name
        Button saveButton = findViewById(R.id.buttonSave); // Corrected ID

        saveButton.setOnClickListener(v -> saveCourse(courseEditText.getText().toString()));
    }

    private void saveCourse(String courseName) {
        EditText courseEditText = findViewById(R.id.editTextCourse);

        if (!courseName.isEmpty()) {
            Course newCourse = new Course(courseName);
            AppDatabase.getInstance((ListActivity) getApplicationContext()).courseDao().insert(newCourse);
            courseEditText.setText(""); // Clear the EditText after saving
        }
    }




    private void onEnterButtonClick(String inputText) {
        // Handle the entered text as needed
    }

    private void startMainActivity() {
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to it from MainActivity
    }
}
