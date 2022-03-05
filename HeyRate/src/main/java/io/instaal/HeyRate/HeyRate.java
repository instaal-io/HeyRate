package io.instaal.HeyRate;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


public class HeyRate {

    public static final String DEFAULT_THEME = "default";
    public static final String SMART_THEME = "smart";
    public static final String FANCY_THEME = "fancy";
    private final Activity activity;
    SharedPreferences sharedPreferences;
    boolean isRatingMode = true;
    private String THEME = "default";
    private final int APP_ICON = 0;


    public HeyRate(Activity activity) {
        this.activity = activity;
        sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

    }


    public void start() {
        switch (THEME) {
            case SMART_THEME:
                showSmartDialog();
                break;
            case FANCY_THEME:
                showFancyDialog();
                break;
            case DEFAULT_THEME:
            default:
                showDefaultDialog();
                break;
        }

    }

    private void showDefaultDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.simple_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        LinearLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        LinearLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        CardView feedbackButton = dialog.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility()== View.VISIBLE){
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }



        });


        dialog.show();

    }

    private void showSmartDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        LinearLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        LinearLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        TextView feedbackButton = dialog.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility()== View.VISIBLE){
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }



        });



        dialog.show();
    }

    private void showFancyDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.advanced_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);


        LinearLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        LinearLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        TextView feedbackButton = dialog.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility()== View.VISIBLE){
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }



        });

        dialog.show();
    }


    public HeyRate setTheme(String theme) {
        THEME = theme;
        return this;
    }


}
