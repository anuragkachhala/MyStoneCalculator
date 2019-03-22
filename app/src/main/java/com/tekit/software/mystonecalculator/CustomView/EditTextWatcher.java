package com.tekit.software.mystonecalculator.CustomView;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tekit.software.mystonecalculator.Interface.MultiTextWatcherListener;

public class EditTextWatcher extends EditText {

    private MultiTextWatcherListener mListeners = null;

    public EditTextWatcher(Context context)
    {
        super(context);
    }

    public EditTextWatcher(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public EditTextWatcher(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void addTextChangedListener(MultiTextWatcherListener watcher)
    {
        if (mListeners == null)
        {
            mListeners = watcher;
        }
    }

    public void removeTextChangedListener(MultiTextWatcherListener watcher)
    {
        if (mListeners != null)
        {
            mListeners = null;
        }
    }

    void  sendBeforeTextChanged(CharSequence text, int start, int before, int after)
    {
        if (mListeners != null)
        {
            mListeners.beforeTextChanged(this, text, start, before, after);
        }
    }

    void  sendOnTextChanged(CharSequence text, int start, int before,int after)
    {
        if (mListeners != null)
        {
            mListeners.onTextChanged(this, text, start, before, after);
        }
    }

    void  sendAfterTextChanged(Editable text)
    {
        if (mListeners != null)
        {
            mListeners.afterTextChanged(this, text);
        }
    }

}
