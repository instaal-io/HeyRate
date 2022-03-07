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

        CardView default_theme = findViewById(R.id.simple_theme);
        default_theme.setOnClickListener(view ->

        new HeyRate(this)
                .setTheme(HeyRate.SIMPLE_THEME)
                .setTitle("Do You Like This App?")
                .setDescription("Please Give Us a Review")
                .setFeedbackButtonText("Message")
                .setRateButtonText("Rate")
                .setFeedbackTitle("Message")
                .setCancelButtonText("No")
                .setSendButtonText("Done")
                .setEditTextHint("Write Your Feedback")
                .start());

        CardView mini_theme = findViewById(R.id.default_theme);
        mini_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.DEFAULT_THEME)
                    .start();

        });

        CardView simple_theme = findViewById(R.id.advanced_theme);
        simple_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.ADVANCED_THEME)
                    .start();

        });



    }
}