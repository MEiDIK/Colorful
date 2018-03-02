package org.polaric.colorful;

import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

public abstract class ColorfulActivity extends AppCompatActivity {
    private String themeString;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeString = Colorful.getThemeString();
        setTheme(Colorful.getThemeDelegate().getStyleResBase());
        getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResPrimary(), true);
        getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResAccent(), true);
//        if (Colorful.getThemeDelegate().getPrimaryColor() == Colorful.ThemeColor.WHITE) {
//            Util.setLightStatusBarCompat(this, true);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Colorful.getThemeDelegate().isTranslucent()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(null, null, getResources().getColor(Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
            setTaskDescription(tDesc);
        }
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        this.toolbar = toolbar;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        if (Colorful.getThemeDelegate().getPrimaryColor() == Colorful.ThemeColor.WHITE) {
//            Util.setToolbarTitleColorCompat(this, Color.BLACK, toolbar);
//            // TODO: 2018/3/2 Detect if custom icon
//            Util.setToolbarNavigationIconColorCompat(this, Color.BLACK, toolbar);
//        }
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
//        if (Colorful.getThemeDelegate().getPrimaryColor() == Colorful.ThemeColor.WHITE) {
//            Util.setToolbarTitleColorCompat(this, Color.BLACK, toolbar);
//            // TODO: 2018/3/2 Detect if custom icon
//            Util.setToolbarNavigationIconColorCompat(this, Color.BLACK, toolbar);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Colorful.getThemeString().equals(themeString)) {
            Log.d(Constants.LOG_TAG, "Theme change detected, restarting activity");
            recreate();
        }
    }
}
