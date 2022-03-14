package io.instaal.apprate;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import io.instaal.HeyRate.HeyRate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CardView default_theme = findViewById(R.id.basic_theme);
        default_theme.setOnClickListener(view ->
                new HeyRate
                        .BasicTheme(this)
                        .start());


        CardView mini_theme = findViewById(R.id.default_theme);
        mini_theme.setOnClickListener(view -> new HeyRate.DefaultTheme(this).start());


        CardView simple_theme = findViewById(R.id.advanced_theme);
        simple_theme.setOnClickListener(view -> new HeyRate.AdvancedTheme(this).start());


    }
}

