package com.ancient.dimension.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ancient.dimension.R;
import com.white.progressview.HorizontalProgressView;

public class DownloadDialog extends Dialog {

    private TextView mTextView;
    private HorizontalProgressView mProgressBar;

    public DownloadDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public DownloadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected DownloadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress, null);
        setContentView(view);
        mProgressBar = (HorizontalProgressView) view.findViewById(R.id.progress100);
        mProgressBar.setTextVisible(true);
        mProgressBar.setReachBarSize(4);
        mProgressBar.setProgressPosition(HorizontalProgressView.TOP);
    }

    public void setProgressBar(int progress) {
        mProgressBar.setProgress(progress);
    }
}
