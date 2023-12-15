package com.Derek.courseapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_USER_SCORE = "userScore";
    private static final int QUIZ_RESULT_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Display quiz questions and options
        displayQuizContent();

        // Set up a button click listener for submitting the quiz
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answers and calculate the score
                int userScore = calculateUserScore();

                // Create the resultIntent to send the user's score back to the calling activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_USER_SCORE, userScore);

                // Set the result code and finish the activity
                setResult(QUIZ_RESULT_CODE, resultIntent);
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void displayQuizContent() {
        // This is where you would dynamically set up your quiz questions and options
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(getString(R.string.quiz_question_hello_world));

        RadioGroup optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        optionsRadioGroup.removeAllViews(); // Clear existing options

        String[] answerOptions = getResources().getStringArray(R.array.quiz_options_hello_world);

        for (int i = 0; i < answerOptions.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answerOptions[i]);
            radioButton.setId(i); // Set a unique ID for each option
            optionsRadioGroup.addView(radioButton);
        }
    }

    private int calculateUserScore() {
        // This is a placeholder. Replace it with your actual logic to calculate the user's score based on quiz answers.
        // For simplicity, let's say the correct answer is at position 1 (index 0).

        RadioGroup optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        int selectedAnswerIndex = optionsRadioGroup.getCheckedRadioButtonId();

        // Ensure that the user has selected an answer before calculating the score
        if (selectedAnswerIndex == -1) {
            // Show a message or prevent submission
            return 0;
        }

        return (selectedAnswerIndex == 1) ? 1 : 0;
    }
}
