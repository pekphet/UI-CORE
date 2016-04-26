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

import cc.fish.coreui.annotation.Injector;

/**
 * Created by fish on 16-4-25.
 */
abstract public class BaseFragmentActivity extends Activity {
    final public static String DECLARED_FIELD_DEFAULT_PAGE = "mDefaultPage";

    private Class<BaseFragment>[] fCls = null;

    //fragment repository res id
    private int flMainId;
    //bottom buttons
    private LinearLayout llBottom = null;

    private BaseFragment[] fragments = null;

    private int mDefaultPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Injector.initFragmentActivity(this);
        super.onCreate(savedInstanceState);
        fCls = putFragments();
        fragments = new BaseFragment[fCls.length];
        flMainId = getFLid();
        llBottom = getBottomLayout();
        initBaseView();
        initView();
        setTabSel(llBottom.getChildAt(mDefaultPage), mDefaultPage);
    }

    private void initBaseView() {
        for (int i = 0; i < fCls.length; i++) {
            final int index = i;
            View v = getBottomItemView(index);
            v.setOnClickListener(view -> {
                setTabSel(view, index);
            });
            llBottom.addView(v);
        }
    }



    protected void setTabSel(View item, int index) {
        onItemClick(item, index);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (int i = 0; i < fCls.length; i++) {
            checkAllBottomItem(llBottom.getChildAt(i), i, false);
            if (i == index) {
                checkAllBottomItem(llBottom.getChildAt(index), index, true);
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

    protected abstract void onItemClick(View item, int index);

    protected LayoutInflater getBottomLayoutInflater() {
        return LayoutInflater.from(this);
    }

    protected abstract void initView();

    protected abstract Class<BaseFragment>[] putFragments();

    protected abstract View getBottomItemView(int index);

    protected abstract int getFLid();

    protected abstract LinearLayout getBottomLayout();

    protected abstract void checkAllBottomItem(View item, int position, boolean isChecked);

}
