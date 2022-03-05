package io.instaal.apprate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import io.instaal.HeyRate.HeyRate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView default_theme = findViewById(R.id.default_theme);
        default_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.DEFAULT_THEME)
                    .start();

        });

        CardView mini_theme = findViewById(R.id.smart_theme);
        mini_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.SMART_THEME)
                    .start();

        });

        CardView simple_theme = findViewById(R.id.fancy_theme);
        simple_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.FANCY_THEME)
                    .start();

        });



    }
}