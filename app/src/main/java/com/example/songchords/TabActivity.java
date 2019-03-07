package com.example.songchords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        ArrayList<String> test = getIntent().getStringArrayListExtra("test");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.lyrictab);
        textView.setText((CharSequence) test);
    }
}
