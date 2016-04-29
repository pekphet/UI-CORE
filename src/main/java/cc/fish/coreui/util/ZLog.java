package cc.fish.coreui.util;

import android.util.Log;

/**
 * Created by fish on 16-4-27.
 */
public class ZLog {
    final private static boolean VERBOSE = true;
    final private static boolean DEBUG = true;
    final private static boolean INFO = true;
    final private static boolean WARNING = true;
    final private static boolean ERROR = true;

    public static void v(String tag, String msg) {
        if (VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (WARNING) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ERROR) {
            Log.e(tag, msg);
        }
    }

}
