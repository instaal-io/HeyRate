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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;


public class HeyRate {


    protected HeyRate() {
    }

    public static class BasicTheme {

        private final Activity activity;
        SharedPreferences sharedPreferences;
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


        public BasicTheme(Activity activity) {
            this.activity = activity;
            sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

        }

        public BasicTheme setTitle(String title) {
            main_title = title;
            return this;
        }

        public BasicTheme setFeedbackTitle(String feedbackTitle) {
            feedback_title = feedbackTitle;
            return this;
        }

        public BasicTheme setDescription(String description) {
            main_des = description;
            return this;
        }

        public BasicTheme setFeedbackButtonText(String feedbackButtonText) {
            feedback_button_text = feedbackButtonText;
            return this;
        }

        public BasicTheme setRateButtonText(String rateButtonText) {
            rate_button_text = rateButtonText;
            return this;
        }

        public BasicTheme setEditTextHint(String editTextHint) {
            edit_text_hint = editTextHint;
            return this;
        }

        public BasicTheme setCancelButtonText(String cancelButtonText) {
            cancel_button_text = cancelButtonText;
            return this;
        }

        public BasicTheme setSendButtonText(String sendButtonText) {
            send_button_text = sendButtonText;
            return this;
        }

        public BasicTheme setPrimaryColor(int primaryColor) {
            primary_color = primaryColor;
            return this;
        }

        public BasicTheme setSecondaryColor(int secondaryColor) {
            secondary_color = secondaryColor;
            return this;
        }

        public BasicTheme setBackgroundColor(int backgroundColor) {
            background_color = backgroundColor;
            return this;
        }

        public BasicTheme setPositiveButtonTextColor(int positiveButtonTextColor) {
            positive_button_text_color = positiveButtonTextColor;
            return this;
        }

        public BasicTheme setNegativeButtonColor(int negativeButtonColor) {
            negative_button_color = negativeButtonColor;
            return this;
        }


        public BasicTheme setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public BasicTheme setContactEmail(String contactEmail) {
            contact_email = contactEmail;
            return this;
        }

        public BasicTheme setShowAfterLaunch(int showAfterLaunch) {
            show_after_launch = showAfterLaunch;
            return this;
        }


        public void start() {
            boolean isRated = sharedPreferences.getBoolean("isRated", false);
            Log.d("TAG", "start: " + isRated);
            if (!isRated) {
                if (show_after_launch == 0) {
                    showBasicDialog();
                } else {
                    int LAUNCH_COUNT = sharedPreferences.getInt("LAUNCHES", 0);
                    sharedPreferences.edit().putInt("LAUNCHES", ++LAUNCH_COUNT).apply();
                    if (LAUNCH_COUNT >= show_after_launch) {
                        showBasicDialog();
                        sharedPreferences.edit().putInt("LAUNCHES", 0).apply();
                    }
                }
            }
        }

        private void showBasicDialog() {

            Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.heyrate_basic_layout);
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
                    editText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                } catch (Resources.NotFoundException notFoundException) {
                    desc.setTextColor(secondary_color);
                    feedBackButtonText.setTextColor(secondary_color);
                    cancelButtonText.setTextColor(secondary_color);
                    editText.setTextColor(secondary_color);
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
            Log.e("TAG", "sendEmail: called");
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


    }


    public static class DefaultTheme {

        public static final String ICON_1 = "icon1";
        public static final String ICON_2 = "icon2";
        public static final String ICON_3 = "icon3";
        public static final String ICON_4 = "icon4";
        public static final String ICON_5 = "icon5";
        public static final String ICON_6 = "icon6";
        public static final String ICON_7 = "icon7";
        public static final String ICON_8 = "icon8";
        public static final String ICON_9 = "icon9";

        private final Activity activity;
        SharedPreferences sharedPreferences;
        private int app_icon = 0;
        private String main_title = "";
        private String feedback_button_text = "";
        private String feedback_title = "";
        private String edit_text_hint = "";
        private String cancel_button_text = "";
        private String send_button_text = "";
        private int primary_color = 0;
        private int secondary_color = 0;
        private int background_color = 0;
        private boolean isCancelable = true;
        private String contact_email = "";
        private int show_after_launch = 0;
        private String later_button_text = "";
        private int rating_required = 4;
        private int corner_radius = 10;
        private boolean hide_feedback_button = false;
        private boolean hide_later_button = false;
        private String pre_installed_icon = "";


        public DefaultTheme(Activity activity) {
            this.activity = activity;
            sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

        }

        public DefaultTheme setTitle(String title) {
            main_title = title;
            return this;
        }

        public DefaultTheme setFeedbackTitle(String feedbackTitle) {
            feedback_title = feedbackTitle;
            return this;
        }


        public DefaultTheme setFeedbackButtonText(String feedbackButtonText) {
            feedback_button_text = feedbackButtonText;
            return this;
        }


        public DefaultTheme setEditTextHint(String editTextHint) {
            edit_text_hint = editTextHint;
            return this;
        }

        public DefaultTheme setCancelButtonText(String cancelButtonText) {
            cancel_button_text = cancelButtonText;
            return this;
        }

        public DefaultTheme setSendButtonText(String sendButtonText) {
            send_button_text = sendButtonText;
            return this;
        }

        public DefaultTheme setPrimaryColor(int primaryColor) {
            primary_color = primaryColor;
            return this;
        }

        public DefaultTheme setSecondaryColor(int secondaryColor) {
            secondary_color = secondaryColor;
            return this;
        }

        public DefaultTheme setBackgroundColor(int backgroundColor) {
            background_color = backgroundColor;
            return this;
        }


        public DefaultTheme setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public DefaultTheme setContactEmail(String contactEmail) {
            contact_email = contactEmail;
            return this;
        }

        public DefaultTheme setShowAfterLaunch(int showAfterLaunch) {
            show_after_launch = showAfterLaunch;
            return this;
        }

        public DefaultTheme setAppIcon(int appIcon) {
            app_icon = appIcon;
            return this;
        }

        public DefaultTheme setPreInstalledIcon(String preInstalledIcon) {
            pre_installed_icon = preInstalledIcon;
            return this;
        }

        public DefaultTheme setRatingRequired(int ratingRequired) {
            rating_required = ratingRequired;
            return this;
        }


        public DefaultTheme setCornerRadius(int cornerRadius) {
            corner_radius = cornerRadius;
            return this;
        }

        public DefaultTheme setHideFeedbackButton(boolean hideFeedbackButton) {
            hide_feedback_button = hideFeedbackButton;
            return this;
        }

        public DefaultTheme setHideLaterButton(boolean hideLaterButton) {
            hide_later_button = hideLaterButton;
            return this;
        }

        public DefaultTheme setLaterButtonText(String laterButtonText) {
            later_button_text = laterButtonText;
            return this;
        }

        public void start() {
            boolean isRated = sharedPreferences.getBoolean("isRated", false);
            Log.d("TAG", "start: " + isRated);
            if (!isRated) {
                if (show_after_launch == 0) {
                    showDefaultDialog();
                } else {
                    int LAUNCH_COUNT = sharedPreferences.getInt("LAUNCHES", 0);
                    sharedPreferences.edit().putInt("LAUNCHES", ++LAUNCH_COUNT).apply();
                    if (LAUNCH_COUNT >= show_after_launch) {
                        showDefaultDialog();
                        sharedPreferences.edit().putInt("LAUNCHES", 0).apply();
                    }
                }
            }
        }

        private void showDefaultDialog() {
            Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.heyrate_default_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(isCancelable);

            RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_relative_layout);
            RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_relative_layout);


            CardView mainCard = dialog.findViewById(R.id.main_card);
            mainCard.setRadius(corner_radius * 4);

            ImageView imageView = dialog.findViewById(R.id.app_icon);

            if (app_icon == 0) {
                switch (pre_installed_icon) {
                    case ICON_2:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_2);
                        break;
                    case ICON_3:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_3);
                        break;
                    case ICON_4:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_4);
                        break;
                    case ICON_5:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_5);
                        break;
                    case ICON_6:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_6);
                        break;
                    case ICON_7:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_7);
                        break;
                    case ICON_8:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_8);
                        break;
                    case ICON_9:
                        imageView.setImageResource(R.drawable.heyrate_icon_main_9);
                        break;
                    case ICON_1:
                    default:
                        imageView.setImageResource(R.drawable.heyrate_icon_main);
                        break;

                }
            }

            if (app_icon != 0) {
                imageView.setImageResource(app_icon);
            }


            TextView mainTitle, feedBackButtonText, laterButtonText, feedbackTitle, cancelButtonText, sendButtonText;
            mainTitle = dialog.findViewById(R.id.main_text);
            feedBackButtonText = dialog.findViewById(R.id.feedback_button);
            laterButtonText = dialog.findViewById(R.id.later_button);
            feedbackTitle = dialog.findViewById(R.id.feedback_text);
            cancelButtonText = dialog.findViewById(R.id.cancel_button);
            sendButtonText = dialog.findViewById(R.id.send_button);

            if (hide_feedback_button) {
                feedBackButtonText.setVisibility(View.GONE);
            } else {
                feedBackButtonText.setVisibility(View.VISIBLE);
            }

            if (hide_later_button) {
                laterButtonText.setVisibility(View.GONE);
            } else {
                laterButtonText.setVisibility(View.VISIBLE);
            }

            EditText editText = dialog.findViewById(R.id.edit_text);


            if (!edit_text_hint.equals("")) {
                editText.setHint(edit_text_hint);
            }

            ratingLayout.setVisibility(View.VISIBLE);
            feedbackLayout.setVisibility(View.GONE);


            if (!main_title.equals("")) {
                mainTitle.setText(main_title);
            }

            if (!feedback_button_text.equals("")) {
                feedBackButtonText.setText(feedback_button_text);
            }
            if (!later_button_text.equals("")) {
                laterButtonText.setText(later_button_text);
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
                    feedBackButtonText.setTextColor(ContextCompat.getColor(activity, primary_color));
                    sendButtonText.setTextColor(ContextCompat.getColor(activity, primary_color));

                } catch (Resources.NotFoundException notFoundException) {
                    mainTitle.setTextColor(primary_color);
                    feedbackTitle.setTextColor(primary_color);
                    feedBackButtonText.setTextColor(primary_color);
                    sendButtonText.setTextColor(primary_color);
                }

            }
            if (secondary_color != 0) {
                try {
                    laterButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                    cancelButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                    editText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                } catch (Resources.NotFoundException notFoundException) {
                    laterButtonText.setTextColor(secondary_color);
                    cancelButtonText.setTextColor(secondary_color);
                    editText.setTextColor(secondary_color);
                }
            }
            if (background_color != 0) {
                try {
                    mainCard.setCardBackgroundColor(ContextCompat.getColor(activity, background_color));
                } catch (Resources.NotFoundException notFoundException) {
                    mainCard.setCardBackgroundColor(background_color);
                }
            }


            feedBackButtonText.setOnClickListener(view -> {
                if (ratingLayout.getVisibility() == View.VISIBLE) {
                    ratingLayout.setVisibility(View.GONE);
                    feedbackLayout.setVisibility(View.VISIBLE);
                } else {
                    ratingLayout.setVisibility(View.VISIBLE);
                    feedbackLayout.setVisibility(View.GONE);
                }


            });


            cancelButtonText.setOnClickListener(view -> dialog.dismiss());

            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
                int rating = (int) v;
                if (rating >= rating_required) {
                    dialog.dismiss();
                    launchStore();
                } else {
                    if (ratingLayout.getVisibility() == View.VISIBLE) {
                        ratingLayout.setVisibility(View.GONE);
                        feedbackLayout.setVisibility(View.VISIBLE);
                    } else {
                        ratingLayout.setVisibility(View.VISIBLE);
                        feedbackLayout.setVisibility(View.GONE);
                    }
                }

            });


            sendButtonText.setOnClickListener(view -> {
                if (editText.getText().length() < 25) {
                    editText.setError("" + (25 - editText.getText().length()) + " more characters to go");
                } else {
                    dialog.dismiss();
                    sendEmail(editText.getText().toString());
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
            Log.e("TAG", "sendEmail: called");
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


    }


    public static class AdvanceTheme {

        public static final String ANIMATION_1 = "anim1";
        public static final String ANIMATION_2 = "anim2";
        public static final String ANIMATION_3 = "anim3";
        public static final String ANIMATION_4 = "anim4";
        public static final String ANIMATION_5 = "anim5";
        public static final String ANIMATION_6 = "anim6";
        public static final String ANIMATION_7 = "anim7";
        public static final String ANIMATION_8 = "anim8";
        public static final String ANIMATION_9 = "anim9";
        public static final String IMAGE_1 = "img1";
        public static final String IMAGE_2 = "img2";
        public static final String IMAGE_3 = "img3";
        public static final String IMAGE_4 = "img4";
        public static final String IMAGE_5 = "img5";
        public static final String IMAGE_6 = "img6";
        public static final String IMAGE_7 = "img7";
        public static final String IMAGE_8 = "img8";
        public static final String IMAGE_9 = "img9";
        private final Activity activity;
        SharedPreferences sharedPreferences;
        private String main_title = "";
        private String feedback_button_text = "";
        private String feedback_title = "";
        private String edit_text_hint = "";
        private String cancel_button_text = "";
        private String send_button_text = "";
        private int primary_color = 0;
        private int secondary_color = 0;
        private int background_color = 0;
        private boolean isCancelable = true;
        private String contact_email = "";
        private int show_after_launch = 0;
        private String later_button_text = "";
        private int rating_required = 4;
        private int corner_radius = 10;
        private boolean hide_feedback_button = false;
        private boolean hide_later_button = false;
        private String animation_select = "0";
        private String image_select = "0";
        private boolean isImageOn = false;
        private int custom_image = 0;
        private int custom_animation = 0;


        public AdvanceTheme(Activity activity) {
            this.activity = activity;
            sharedPreferences = this.activity.getApplicationContext().getSharedPreferences("HeyRate", Activity.MODE_PRIVATE);

        }

        public AdvanceTheme setTitle(String title) {
            main_title = title;
            return this;
        }

        public AdvanceTheme setFeedbackTitle(String feedbackTitle) {
            feedback_title = feedbackTitle;
            return this;
        }


        public AdvanceTheme setFeedbackButtonText(String feedbackButtonText) {
            feedback_button_text = feedbackButtonText;
            return this;
        }


        public AdvanceTheme setEditTextHint(String editTextHint) {
            edit_text_hint = editTextHint;
            return this;
        }

        public AdvanceTheme setCancelButtonText(String cancelButtonText) {
            cancel_button_text = cancelButtonText;
            return this;
        }

        public AdvanceTheme setSendButtonText(String sendButtonText) {
            send_button_text = sendButtonText;
            return this;
        }

        public AdvanceTheme setPrimaryColor(int primaryColor) {
            primary_color = primaryColor;
            return this;
        }

        public AdvanceTheme setSecondaryColor(int secondaryColor) {
            secondary_color = secondaryColor;
            return this;
        }

        public AdvanceTheme setBackgroundColor(int backgroundColor) {
            background_color = backgroundColor;
            return this;
        }


        public AdvanceTheme setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public AdvanceTheme setContactEmail(String contactEmail) {
            contact_email = contactEmail;
            return this;
        }

        public AdvanceTheme setShowAfterLaunch(int showAfterLaunch) {
            show_after_launch = showAfterLaunch;
            return this;
        }


        public AdvanceTheme setRatingRequired(int ratingRequired) {
            rating_required = ratingRequired;
            return this;
        }


        public AdvanceTheme setCornerRadius(int cornerRadius) {
            corner_radius = cornerRadius;
            return this;
        }

        public AdvanceTheme setHideFeedbackButton(boolean hideFeedbackButton) {
            hide_feedback_button = hideFeedbackButton;
            return this;
        }

        public AdvanceTheme setHideLaterButton(boolean hideLaterButton) {
            hide_later_button = hideLaterButton;
            return this;
        }

        public AdvanceTheme setLaterButtonText(String laterButtonText) {
            later_button_text = laterButtonText;
            return this;
        }


        public AdvanceTheme setImageInsteadAnim(boolean imageInsteadAnim) {
            isImageOn = imageInsteadAnim;
            return this;
        }


        public AdvanceTheme setHeaderAnimation(String headerAnimation) {
            animation_select = headerAnimation;
            return this;
        }


        public AdvanceTheme setHeaderImage(String headerImage) {
            image_select = headerImage;
            return this;

        }

        public AdvanceTheme setCustomHeaderAnim(int customHeaderAnim) {
            custom_animation = customHeaderAnim;
            return this;
        }

        public AdvanceTheme setCustomHeaderImage(int customHeaderImage) {
            custom_image = customHeaderImage;
            return this;
        }


        public void start() {
            boolean isRated = sharedPreferences.getBoolean("isRated", false);
            Log.d("TAG", "start: " + isRated);
            if (!isRated) {
                if (show_after_launch == 0) {
                    showAdvancedDialog();
                } else {
                    int LAUNCH_COUNT = sharedPreferences.getInt("LAUNCHES", 0);
                    sharedPreferences.edit().putInt("LAUNCHES", ++LAUNCH_COUNT).apply();
                    if (LAUNCH_COUNT >= show_after_launch) {
                        showAdvancedDialog();
                        sharedPreferences.edit().putInt("LAUNCHES", 0).apply();
                    }
                }
            }
        }

        private void showAdvancedDialog() {
            Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.heyrate_advance_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(isCancelable);


            RelativeLayout ratingLayout = dialog.findViewById(R.id.rating_layout);
            RelativeLayout feedbackLayout = dialog.findViewById(R.id.feedback_layout);

            ratingLayout.setVisibility(View.VISIBLE);
            feedbackLayout.setVisibility(View.GONE);

            CardView mainCard = dialog.findViewById(R.id.main_card);
            mainCard.setRadius(corner_radius * 4);

            LottieAnimationView lottieAnimationView = dialog.findViewById(R.id.animation_view);

            if (custom_animation == 0) {
                switch (animation_select) {
                    case ANIMATION_2:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_2);
                        break;
                    case ANIMATION_3:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_3);
                        break;
                    case ANIMATION_4:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_4);
                        break;
                    case ANIMATION_5:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_5);
                        break;
                    case ANIMATION_6:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_6);
                        break;
                    case ANIMATION_7:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_7);
                        break;
                    case ANIMATION_8:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_8);
                        break;
                    case ANIMATION_9:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_9);
                        break;
                    case ANIMATION_1:
                    default:
                        lottieAnimationView.setAnimation(R.raw.heyrate_anim_1);
                        break;
                }
            } else {
                lottieAnimationView.setAnimation(custom_animation);
            }


            ImageView imageView = dialog.findViewById(R.id.image_view);

            if (custom_image == 0) {
                switch (image_select) {
                    case IMAGE_2:
                        imageView.setImageResource(R.drawable.heyrate_img_2);
                        break;
                    case IMAGE_3:
                        imageView.setImageResource(R.drawable.heyrate_img_3);
                        break;
                    case IMAGE_4:
                        imageView.setImageResource(R.drawable.heyrate_img_4);
                        break;
                    case IMAGE_5:
                        imageView.setImageResource(R.drawable.heyrate_img_5);
                        break;
                    case IMAGE_6:
                        imageView.setImageResource(R.drawable.heyrate_img_6);
                        break;
                    case IMAGE_7:
                        imageView.setImageResource(R.drawable.heyrate_img_7);
                        break;
                    case IMAGE_8:
                        imageView.setImageResource(R.drawable.heyrate_img_8);
                        break;
                    case IMAGE_9:
                        imageView.setImageResource(R.drawable.heyrate_img_9);
                        break;
                    case IMAGE_1:
                    default:
                        imageView.setImageResource(R.drawable.heyrate_img_1);
                        break;
                }
            } else {
                imageView.setImageResource(custom_image);
            }

            if (isImageOn) {
                lottieAnimationView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            } else {
                lottieAnimationView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }


            TextView mainTitle, feedBackButtonText, laterButtonText, feedbackTitle, cancelButtonText, sendButtonText;
            mainTitle = dialog.findViewById(R.id.main_text);
            feedBackButtonText = dialog.findViewById(R.id.feedback_button);
            laterButtonText = dialog.findViewById(R.id.later_button);
            feedbackTitle = dialog.findViewById(R.id.feedback_text);
            cancelButtonText = dialog.findViewById(R.id.cancel_button);
            sendButtonText = dialog.findViewById(R.id.send_button);

            if (hide_feedback_button) {
                feedBackButtonText.setVisibility(View.GONE);
            } else {
                feedBackButtonText.setVisibility(View.VISIBLE);
            }

            if (hide_later_button) {
                laterButtonText.setVisibility(View.GONE);
            } else {
                laterButtonText.setVisibility(View.VISIBLE);
            }

            EditText editText = dialog.findViewById(R.id.edit_text);


            if (!edit_text_hint.equals("")) {
                editText.setHint(edit_text_hint);
            }

            if (!main_title.equals("")) {
                mainTitle.setText(main_title);
            }

            if (!feedback_button_text.equals("")) {
                feedBackButtonText.setText(feedback_button_text);
            }
            if (!later_button_text.equals("")) {
                laterButtonText.setText(later_button_text);
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
                    feedBackButtonText.setTextColor(ContextCompat.getColor(activity, primary_color));
                    sendButtonText.setTextColor(ContextCompat.getColor(activity, primary_color));

                } catch (Resources.NotFoundException notFoundException) {
                    mainTitle.setTextColor(primary_color);
                    feedbackTitle.setTextColor(primary_color);
                    feedBackButtonText.setTextColor(primary_color);
                    sendButtonText.setTextColor(primary_color);
                }

            }
            if (secondary_color != 0) {
                try {
                    laterButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                    cancelButtonText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                    editText.setTextColor(ContextCompat.getColor(activity, secondary_color));
                } catch (Resources.NotFoundException notFoundException) {
                    laterButtonText.setTextColor(secondary_color);
                    cancelButtonText.setTextColor(secondary_color);
                    editText.setTextColor(secondary_color);
                }
            }
            if (background_color != 0) {
                try {
                    mainCard.setCardBackgroundColor(ContextCompat.getColor(activity, background_color));
                } catch (Resources.NotFoundException notFoundException) {
                    mainCard.setCardBackgroundColor(background_color);
                }
            }


            feedBackButtonText.setOnClickListener(view -> {
                if (ratingLayout.getVisibility() == View.VISIBLE) {
                    ratingLayout.setVisibility(View.GONE);
                    feedbackLayout.setVisibility(View.VISIBLE);
                } else {
                    ratingLayout.setVisibility(View.VISIBLE);
                    feedbackLayout.setVisibility(View.GONE);
                }


            });


            cancelButtonText.setOnClickListener(view -> dialog.dismiss());

            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
                int rating = (int) v;
                if (rating >= rating_required) {
                    dialog.dismiss();
                    launchStore();
                } else {
                    if (ratingLayout.getVisibility() == View.VISIBLE) {
                        ratingLayout.setVisibility(View.GONE);
                        feedbackLayout.setVisibility(View.VISIBLE);
                    } else {
                        ratingLayout.setVisibility(View.VISIBLE);
                        feedbackLayout.setVisibility(View.GONE);
                    }
                }

            });


            sendButtonText.setOnClickListener(view -> {
                if (editText.getText().length() < 25) {
                    editText.setError("" + (25 - editText.getText().length()) + " more characters to go");
                } else {
                    dialog.dismiss();
                    sendEmail(editText.getText().toString());
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
            Log.e("TAG", "sendEmail: called");
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


    }


}
