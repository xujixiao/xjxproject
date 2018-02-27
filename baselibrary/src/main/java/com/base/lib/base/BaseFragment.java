package com.base.lib.base;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.base.lib.base.constants.BaseLogConstant;

/**
 */

public class BaseFragment extends Fragment {

    /**
     * 柯聪的框架中接口调用的进度框都是activity的
     */
    public void showProgress() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.showProgress();
        }
    }

    public void dismissProgress() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.dismissProgress();
        }
    }

    public void finishCurrent() {
        if (getActivity() != null) {
            if (getActivity() instanceof BaseActivity) {
                BaseActivity activity = (BaseActivity) getActivity();
                activity.finishCurrent();
            }
        }
    }


    public BaseActivity obtainActivity() {
        return (BaseActivity) getActivity();
    }

    public void handleError(Throwable throwable) {
        Log.d(BaseLogConstant.tag, throwable.getMessage());
    }

}
