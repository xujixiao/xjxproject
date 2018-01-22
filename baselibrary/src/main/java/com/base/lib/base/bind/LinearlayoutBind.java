package com.base.lib.base.bind;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;


public class LinearlayoutBind {
	@BindingAdapter("android:enable")
	public static void setEnable(LinearLayout linearLayout,boolean flag){
		linearLayout.setEnabled(flag);
	}
}
