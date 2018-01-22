package com.base.lib.base.bind;

import android.databinding.BindingAdapter;
import android.text.InputType;
import android.widget.EditText;

public class EditTextBind {
	/**
	 * 控制edittext的密码是否可见
	 *
	 * @param editText
	 * @param visible
	 */
	@BindingAdapter("android:passwordVisible")
	public static void setPasswordVisible(EditText editText, boolean visible) {

		if (visible) {
			editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		} else {
			editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		if (editText.getText() != null) {
			editText.setSelection(editText.getText().toString().length());
		}
	}



	@BindingAdapter("android:requestFocus")
	public static void requestFocus(EditText editText, boolean isFocus) {
		if (isFocus) {
			editText.requestFocus();
		}
	}

}
