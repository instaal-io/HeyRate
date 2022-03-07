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


        CardView default_theme = findViewById(R.id.simple_theme);
        default_theme.setOnClickListener(view ->
                new HeyRate(this)
                        .setTheme(HeyRate.SIMPLE_THEME)
//                        .setTitle("Do You Like This App?")
//                        .setDescription("Please Give Us a Review")
//                        .setFeedbackButtonText("Message")
//                        .setRateButtonText("Rate")
//                        .setFeedbackTitle("Message")
//                        .setCancelButtonText("No")
//                        .setSendButtonText("Done")
//                        .setEditTextHint("Write Your Feedback")
//                        .setContactEmail("kmrejowan@gmail.com")
//                        .setBackgroundColor(Color.WHITE)
//                        .setPrimaryColor(Color.BLACK)
//                        .setSecondaryColor(Color.GRAY)
//                        .setNegativeButtonColor(Color.DKGRAY)
//                        .setPositiveButtonTextColor(Color.GREEN)
//                        .setCancelable(true)
//                        .setShowAfterLaunch(1)
                        .start());


        CardView mini_theme = findViewById(R.id.default_theme);
        mini_theme.setOnClickListener(view ->
                new HeyRate(this)
                        .setTheme(HeyRate.DEFAULT_THEME)
                        .setTitle("Do You Like This App?")
                        .setFeedbackTitle("Message")
                        .setFeedbackButtonText("Message")
                        .setLaterButtonText("Not Now")
                        .setCancelButtonText("No")
                        .setSendButtonText("Okay")
                        .setEditTextHint("adlkadf d sdfa")
                        .setBackgroundColor(Color.CYAN)
                        .setHideLaterButton(true)
                        .setContactEmail("ahmmedrejowan@gmail.com")
                        .setPrimaryColor(Color.RED)
                        .setRatingRequired(5)
                        .setCornerRadius(10)
                        .setSecondaryColor(Color.BLACK)
                        .setAppIcon(R.mipmap.ic_launcher)
                        .setShowAfterLaunch(1)
                        .start());


        CardView simple_theme = findViewById(R.id.advanced_theme);
        simple_theme.setOnClickListener(view -> {
            new HeyRate(this)
                    .setTheme(HeyRate.ADVANCED_THEME)
                    .setShowAfterLaunch(1)
                    .start();

        });


    }
}


//        new HeyRate(this)
//                .setTheme(HeyRate.SIMPLE_THEME)
//                .setTitle("Do You Like This App?")
//                .setDescription("Please Give Us a Review")
//                .setFeedbackButtonText("Message")
//                .setRateButtonText("Rate")
//                .setFeedbackTitle("Message")
//                .setCancelButtonText("No")
//                .setSendButtonText("Done")
//                .setEditTextHint("Write Your Feedback")
//                .setContactEmail("kmrejowan@gmail.com")
//                .setBackgroundColor(Color.WHITE)
//                .setPrimaryColor(Color.BLACK)
//                .setSecondaryColor(Color.GRAY)
//                .setNegativeButtonColor(Color.DKGRAY)
//                .setPositiveButtonTextColor(Color.GREEN)
//                .setCancelable(true)
//                .setShowAfterLaunch(1)
//                .start();