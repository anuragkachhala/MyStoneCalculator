package com.tekit.software.mystonecalculator.Interface;

import android.text.Editable;
import android.text.NoCopySpan;
import android.view.View;

public interface MultiTextWatcherListener extends NoCopySpan {
    public void afterTextChanged(View v, Editable s);

    public void onTextChanged(View v, CharSequence s, int start, int before, int count);

    public void beforeTextChanged(View v, CharSequence s, int start, int count, int after);
}
