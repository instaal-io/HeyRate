package io.instaal.HeyRate;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


public class HeyRate {

    public static final String SIMPLE_THEME = "simple";
    public static final String DEFAULT_THEME = "default";
    public static final String ADVANCED_THEME = "advanced";
    private final Activity activity;
    private final int APP_ICON = 0;
    SharedPreferences sharedPreferences;
    boolean isRatingMode = true;
    private String THEME = "default";
    private String main_title = "";
    private String main_des = "";
    private String feedback_button_text = "";
    private String rate_button_text = "";
    private String feedback_title = "";
    private String edit_text_hint = "";
    private String cancel_button_text = "";
    private String send_button_text = "";


    public HeyRate(Activity activity) {
        this.activity = activity;
        sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

    }


    public void start() {
        switch (THEME) {
            case ADVANCED_THEME:
                showAdvancedDialog();
                break;
            case SIMPLE_THEME:
                showSimpleDialog();
                break;
            case DEFAULT_THEME:
            default:
                showDefaultDialog();
                break;
        }

    }

    private void showSimpleDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.simple_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);

        TextView mainTitle, desc, feedBackButtonText, rateNowButtonText, feedbackTitle, cancelButtonText, sendButtonText;

        mainTitle = dialog.findViewById(R.id.main_text);
        desc = dialog.findViewById(R.id.description);
        feedBackButtonText = dialog.findViewById(R.id.feedback_button);
        rateNowButtonText = dialog.findViewById(R.id.rate_button);
        feedbackTitle = dialog.findViewById(R.id.feedback_text);
        cancelButtonText = dialog.findViewById(R.id.cancel_button);
        sendButtonText = dialog.findViewById(R.id.send_button);

        if (!main_title.equals("")) {
            mainTitle.setText(main_title);
        }
        if (!main_des.equals("")) {
            mainTitle.setText(main_title);
        }

        if (!feedback_button_text.equals("")) {
            feedBackButtonText.setText(feedback_button_text);
        }
        if (!rate_button_text.equals("")) {
            rateNowButtonText.setText(rate_button_text);
        }
        if (!feedback_title.equals("")) {
            feedbackTitle.setText(feedback_title);
        }
        if (!cancel_button_text.equals("")) {
            cancelButtonText.setText(cancel_button_text);
        }

        if (!send_button_text.equals("")) {
            sendButtonText.setText(send_button_text);
        }


        CardView feedbackButton = dialog.findViewById(R.id.feedback_card);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility() == View.VISIBLE) {
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }


        });


        dialog.show();

    }

    private void showDefaultDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        TextView feedbackButton = dialog.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility() == View.VISIBLE) {
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }


        });


        dialog.show();
    }

    private void showAdvancedDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.advanced_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);


        RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        TextView feedbackButton = dialog.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(view -> {

            if (ratingLayout.getVisibility() == View.VISIBLE) {
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


    public HeyRate setTitle(String title) {
        main_title = title;
        return this;
    }

    public HeyRate setFeedbackTitle(String feedbackTitle) {
        feedback_title = feedbackTitle;
        return this;
    }

    public HeyRate setDescription(String description) {
        main_des = description;
        return this;
    }

    public HeyRate setFeedbackButtonText(String feedbackButtonText) {
        feedback_button_text = feedbackButtonText;
        return this;
    }

    public HeyRate setRateButtonText(String rateButtonText) {
        rate_button_text = rateButtonText;
        return this;
    }

    public HeyRate setEditTextHint(String editTextHint) {
        edit_text_hint = editTextHint;
        return this;
    }

    public HeyRate setCancelButtonText(String cancelButtonText) {
        cancel_button_text = cancelButtonText;
        return this;
    }

    public HeyRate setSendButtonText(String sendButtonText) {
        send_button_text = sendButtonText;
        return this;
    }

}
