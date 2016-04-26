package cc.fish.coreui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 16-4-25.
 */
abstract public class BaseFragmentActivity extends Activity {

    private Class<BaseFragment>[] fCls = null;

    //fragment repository res id
    private int flMainId;
    //bottom buttons
    private LinearLayout llBottom = null;

    private BaseFragment[] fragments = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fCls = putFragments();
        fragments = new BaseFragment[fCls.length];
        flMainId = getFLid();
        llBottom = getBottomLayout();
        initBaseView();
        initView();
    }

    private void initBaseView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        for (int i = 0; i < fCls.length; i++) {
            final int index = i;
            View v = getBottomItemView(index);
            v.setOnClickListener(view -> {
                setTabSel(view, index);
            });
            llBottom.addView(v, params);
        }
    }



    private void setTabSel(View item, int index) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (int i = 0; i < fCls.length; i++) {
            checkBottomItem(llBottom.getChildAt(i), i, false);
            if (i == index) {
                checkBottomItem(llBottom.getChildAt(index), index, true);
                if (fragments[index] == null) {
                    try {
                        BaseFragment bf = fCls[index].newInstance();
                        fragments[index] = bf;
                        ft.add(flMainId, bf);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ft.show(fragments[index]);
                    fragments[index].initData();
                }
            } else if (fragments[i] != null) {
                ft.hide(fragments[i]);
            }
        }
        ft.commitAllowingStateLoss();
    }

    protected LayoutInflater getBottomLayoutInflater() {
        return LayoutInflater.from(this);
    }

    protected abstract void initView();

    protected abstract Class<BaseFragment>[] putFragments();

    protected abstract View getBottomItemView(int index);

    protected abstract int getFLid();

    protected abstract LinearLayout getBottomLayout();

    protected abstract void checkBottomItem(View item, int position, boolean isChecked);



}
