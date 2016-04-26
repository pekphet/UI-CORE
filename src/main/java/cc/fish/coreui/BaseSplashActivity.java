package cc.fish.coreui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import cc.fish.coreui.annotation.Injector;

/**
 * Created by fish on 16-4-25.
 */
public class BaseSplashActivity extends Activity{
    final public static String  METHOD_SET_CONFIG = "setConfig";

    private int mDelay;
    private Class<Activity> mClz;

    private Handler h = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Injector.initSplash(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        goMainDelayed(mDelay);
    }

    public void setConfig(int delay, Class clz) {
        this.mDelay = delay;
        this.mClz = clz;
    }

    public void goMainDelayed(int delay) {
        h.postDelayed(() -> {
            startActivity(new Intent(this, mClz));
            finish();
        }, delay);

    }
}
