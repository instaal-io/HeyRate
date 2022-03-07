package io.instaal.HeyRate;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;


public class HeyRate {

    public static final String SIMPLE_THEME = "simple";
    public static final String DEFAULT_THEME = "default";
    public static final String ADVANCED_THEME = "advanced";
    private final Activity activity;
    private final int APP_ICON = 0;
    SharedPreferences sharedPreferences;
    private String THEME = "default";
    private String main_title = "";
    private String main_des = "";
    private String feedback_button_text = "";
    private String rate_button_text = "";
    private String feedback_title = "";
    private String edit_text_hint = "";
    private String cancel_button_text = "";
    private String send_button_text = "";
    private int primary_color = 0;
    private int secondary_color = 0;
    private int background_color = 0;
    private int positive_button_text_color = 0;
    private int negative_button_color = 0;
    private boolean isCancelable = true;
    private String contact_email = "";
    private int show_after_launch = 0;

    public HeyRate(Activity activity) {
        this.activity = activity;
        sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

    }

    public void start() {
        boolean isRated = sharedPreferences.getBoolean("isRated", false);
        Log.d("TAG", "start: " + isRated);
        if (!isRated){
            if (show_after_launch == 0) {
                chooseTheme();
            } else {
                int LAUNCH_COUNT = sharedPreferences.getInt("LAUNCHES", 0);
                sharedPreferences.edit().putInt("LAUNCHES", ++LAUNCH_COUNT).apply();
                if (LAUNCH_COUNT >= show_after_launch) {
                    chooseTheme();
                    sharedPreferences.edit().putInt("LAUNCHES", 0).apply();
                }
            }
        }
    }



    private void chooseTheme(){
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
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(isCancelable);

        RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
        RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

        CardView feedbackCard = dialog.findViewById(R.id.feedback_card);
        CardView rateNowCard = dialog.findViewById(R.id.rate_now_card);
        CardView sendCard = dialog.findViewById(R.id.send_card);
        CardView mainCard = dialog.findViewById(R.id.main_card);
        CardView cancelCard = dialog.findViewById(R.id.cancel_card);

        TextView mainTitle, desc, feedBackButtonText, rateNowButtonText, feedbackTitle, cancelButtonText, sendButtonText;
        mainTitle = dialog.findViewById(R.id.main_text);
        desc = dialog.findViewById(R.id.description);
        feedBackButtonText = dialog.findViewById(R.id.feedback_button);
        rateNowButtonText = dialog.findViewById(R.id.rate_button);
        feedbackTitle = dialog.findViewById(R.id.feedback_text);
        cancelButtonText = dialog.findViewById(R.id.cancel_button);
        sendButtonText = dialog.findViewById(R.id.send_button);

        EditText editText = dialog.findViewById(R.id.edit_text);

        if (!edit_text_hint.equals("")) {
            editText.setHint(edit_text_hint);
        }


        ratingLayout.setVisibility(View.VISIBLE);
        feedbackLayout.setVisibility(View.GONE);


        if (!main_title.equals("")) {
            mainTitle.setText(main_title);
        }
        if (!main_des.equals("")) {
            desc.setText(main_des);
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

        if (primary_color != 0) {
            try {
                mainTitle.setTextColor(ContextCompat.getColor(activity, primary_color));
                feedbackTitle.setTextColor(ContextCompat.getColor(activity, primary_color));
                rateNowCard.setCardBackgroundColor(ContextCompat.getColor(activity, primary_color));
                sendCard.setCardBackgroundColor(ContextCompat.getColor(activity, primary_color));

            } catch (Resources.NotFoundException notFoundException) {
                mainTitle.setTextColor(primary_color);
                feedbackTitle.setTextColor(primary_color);
                rateNowCard.setCardBackgroundColor(primary_color);
                sendCard.setCardBackgroundColor(primary_color);

            }

        }
        if (secondary_color != 0) {
            try {
                desc.setTextColor(ContextCompat.getColor(activity, secondary_color));
                feedBackButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                cancelButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
            } catch (Resources.NotFoundException notFoundException) {
                desc.setTextColor(secondary_color);
                feedBackButtonText.setTextColor(secondary_color);
                cancelButtonText.setTextColor(secondary_color);
            }
        }
        if (background_color != 0) {
            try {
                mainCard.setCardBackgroundColor(ContextCompat.getColor(activity, background_color));
            } catch (Resources.NotFoundException notFoundException) {
                mainCard.setCardBackgroundColor(background_color);
            }
        }
        if (positive_button_text_color != 0) {

            try {
                rateNowButtonText.setTextColor(ContextCompat.getColor(activity, positive_button_text_color));
                sendButtonText.setTextColor(ContextCompat.getColor(activity, positive_button_text_color));

            } catch (Resources.NotFoundException notFoundException) {
                rateNowButtonText.setTextColor(positive_button_text_color);
                sendButtonText.setTextColor(positive_button_text_color);
            }

        }
        if (negative_button_color != 0) {
            try {
                cancelCard.setCardBackgroundColor(ContextCompat.getColor(activity, negative_button_color));
                feedbackCard.setCardBackgroundColor(ContextCompat.getColor(activity, negative_button_color));

            } catch (Resources.NotFoundException notFoundException) {
                cancelCard.setCardBackgroundColor(negative_button_color);
                feedbackCard.setCardBackgroundColor(negative_button_color);
            }
        }

        feedbackCard.setOnClickListener(view -> {
            if (ratingLayout.getVisibility() == View.VISIBLE) {
                ratingLayout.setVisibility(View.GONE);
                feedbackLayout.setVisibility(View.VISIBLE);
            } else {
                ratingLayout.setVisibility(View.VISIBLE);
                feedbackLayout.setVisibility(View.GONE);
            }


        });


        cancelCard.setOnClickListener(view -> dialog.dismiss());
        rateNowCard.setOnClickListener(view -> {
            dialog.dismiss();
            launchStore();
        });
        sendCard.setOnClickListener(view -> {
            if (editText.getText().length() < 25) {
                editText.setError("" + (25 - editText.getText().length()) + " more characters to go");
            } else {
                dialog.dismiss();
                sendEmail(editText.getText().toString());
            }
        });


        dialog.show();

    }

    private void showDefaultDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.default_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(isCancelable);

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
        dialog.getWindow().setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(isCancelable);


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

    private void launchStore() {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        sharedPreferences.edit().putBoolean("isRated", true).apply();
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    private void sendEmail(String message) {
        Log.e("TAG", "sendEmail: called" );
        final String packageName = activity.getPackageName();
        PackageManager packageManager = activity.getPackageManager();
        String appName;
        try {
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            appName = "App";
            e.printStackTrace();
        }

        if (!contact_email.equals("")) {
            String[] emails = {contact_email};
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, emails);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback About " + appName);
            try {
                activity.startActivity(Intent.createChooser(intent, "Choose"));
            } catch (ActivityNotFoundException activityNotFoundException) {
                Log.e("TAG", "sendEmail: ", activityNotFoundException);
            }
        } else {
            Log.e("TAG", "sendEmail: Failed, No Email Set");
        }



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

    public HeyRate setPrimaryColor(int primaryColor) {
        primary_color = primaryColor;
        return this;
    }

    public HeyRate setSecondaryColor(int secondaryColor) {
        secondary_color = secondaryColor;
        return this;
    }

    public HeyRate setBackgroundColor(int backgroundColor) {
        background_color = backgroundColor;
        return this;
    }

    public HeyRate setPositiveButtonTextColor(int positiveButtonTextColor) {
        positive_button_text_color = positiveButtonTextColor;
        return this;
    }

    public HeyRate setNegativeButtonColor(int negativeButtonColor) {
        negative_button_color = negativeButtonColor;
        return this;
    }

    public HeyRate setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return this;
    }

    public HeyRate setContactEmail(String contactEmail) {
        contact_email = contactEmail;
        return this;
    }

    public HeyRate setShowAfterLaunch(int showAfterLaunch){
        show_after_launch = showAfterLaunch;
        return this;
    }

}
