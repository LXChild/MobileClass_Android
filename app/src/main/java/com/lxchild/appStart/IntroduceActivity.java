package com.lxchild.appStart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.lxchild.mobileclass.R;
import com.lxchild.sharePreference.AppPref;
import com.lxchild.signin.view.SignInActivity;

/**
 * Created by LXChild on 16/10/2016.
 */

public class IntroduceActivity extends AppIntro {
    public static void launch(Context context) {
        context.startActivity(new Intent(context, IntroduceActivity.class));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
//        addSlide(firstFragment);
//        addSlide(secondFragment);
//        addSlide(thirdFragment);
//        addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(
                "title1",
                "description1",
                R.drawable.ic_slide1,
                getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(
                "title2",
                "description2",
                R.drawable.ic_slide2,
                getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("title3",
                "description3",
                R.drawable.ic_slide3,
                getResources().getColor(R.color.colorPrimary)));

        // OPTIONAL METHODS
        // Override bar/separator color.
//        setBarColor(Color.parseColor("#3F51B5"));
//        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        goSignIn();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        goSignIn();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void goSignIn() {
        AppPref.setAlreadyRun(this);
        SignInActivity.launch(this);
        finish();
    }
}
