package cc.fish.coreui.annotation;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cc.fish.coreui.BaseSplashActivity;

/**
 * Created by fish on 16-4-25.
 */
public class Injector {



    public static void initSplash(BaseSplashActivity bsa){
        Class<? extends BaseSplashActivity> clz = bsa.getClass();
        Splash splash = clz.getAnnotation(Splash.class);
        if (splash == null) {
            return;
        }
        bsa.setConfig(splash.delay(), splash.clz());
    }
}
