package com.zodiac.polit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zodiac.polit.R;
import com.zodiac.polit.ui.MainActivity;
import com.zodiac.polit.ui.SplashActivity;
import com.zodiac.polit.util.PreferenceUtils;
import com.zodiac.polit.view.welcome.WelcomeCoordinatorLayout;

/**
 * Created by john on 2018/10/28.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        WelcomeCoordinatorLayout coordinator = findViewById(R.id.coordinator);
        coordinator.addPage(R.layout.wel1,
                R.layout.wel2);
    }

    public void onDone(View view){
        PreferenceUtils.getInstance().putBooleanValue("isFirstIn" , false);
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }

}
