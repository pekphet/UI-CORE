package cc.fish.coreui.util;

import android.content.Context;

/**
 * Created by fish on 16-5-5.
 */
public class DisplayUtil {
    public static int Dp2Px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
