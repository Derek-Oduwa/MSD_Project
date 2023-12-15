package com.Derek.courseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.Derek.courseapp.adapter.CategoryAdapter;
import com.Derek.courseapp.model.Category;
import com.Derek.courseapp.retrofit.ApiInterface;
import com.Derek.courseapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String INPUT_TEXT_KEY = "inputText";
    private static final String TAG = "MainActivity";

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private EditText inputEditText;

    // Use ActivityResultLauncher for starting activities with results
    private final ActivityResultLauncher<Intent> activityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Handle the result from the input screen if needed
                    // For example: String inputResult = result.getData().getStringExtra("inputResult");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToExtraButton = findViewById(R.id.goToExtraButton);

        goToExtraButton.setOnClickListener(v -> {
            // Start ExtraActivity when the button is clicked
            Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
            startActivity(intent);
        });

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        categoryRecyclerView = findViewById(R.id.course_recycler);
        Call<List<Category>> call = apiInterface.getAllCategory();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> categoryList = response.body();
                getAllCategory(categoryList);
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "No response from server", Toast.LENGTH_SHORT).show();
            }
        });

        inputEditText = findViewById(R.id.inputEditText);
        Button enterButton = findViewById(R.id.enterButton);
        Button inputButton = findViewById(R.id.inputButton);

        // Set click listener for the enterButton
        enterButton.setOnClickListener(v -> onEnterButtonClick());

        // Set click listener for the inputButton
        inputButton.setOnClickListener(v -> startInputActivity());

        // Restore saved input text
        if (savedInstanceState != null) {
            String savedInputText = savedInstanceState.getString(INPUT_TEXT_KEY);
            inputEditText.setText(savedInputText);
        }
    }

    private void getAllCategory(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    // OnClickListener logic for enterButton
    private void onEnterButtonClick() {
        Log.d(TAG, "Enter button clicked");
        String inputText = inputEditText.getText().toString();

        if (!inputText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Text Entered: " + inputText, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }

    // Start InputActivity using activityLauncher
    private void startInputActivity() {
        Log.d(TAG, "Input button clicked");
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        activityLauncher.launch(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String inputText = inputEditText.getText().toString();
        outState.putString(INPUT_TEXT_KEY, inputText);
    }
}
