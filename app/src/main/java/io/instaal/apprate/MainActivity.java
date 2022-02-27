package io.instaal.apprate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import io.instaal.miniapprate.MiniAppRate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView default_theme = findViewById(R.id.default_theme);
        default_theme.setOnClickListener(view -> {
            new MiniAppRate(this)
                    .setTheme(MiniAppRate.DEFAULT_THEME)
                    .start();

        });

        CardView mini_theme = findViewById(R.id.smart_theme);
        mini_theme.setOnClickListener(view -> {
            new MiniAppRate(this)
                    .setTheme(MiniAppRate.SMART_THEME)
                    .start();

        });

        CardView simple_theme = findViewById(R.id.fancy_theme);
        simple_theme.setOnClickListener(view -> {
            new MiniAppRate(this)
                    .setTheme(MiniAppRate.FANCY_THEME)
                    .start();

        });



    }
}