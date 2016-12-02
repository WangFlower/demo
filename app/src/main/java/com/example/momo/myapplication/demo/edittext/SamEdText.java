package com.example.momo.myapplication.demo.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by MOMO on 16/11/18.
 */

public class SamEdText extends EditText {


    public SamEdText(Context context) {
        super(context,null);
    }

    public SamEdText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public SamEdText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setEditableFactory(new Editable.Factory() {
            @Override
            public Editable newEditable(CharSequence source) {
                return new SpannableStringBuilder(source) {
                    @Override
                    public SpannableStringBuilder replace(int start, int end, CharSequence tb, int tbstart, int tbend) {
                        Log.i("wangrenguang",""+tb);
                        /**
                         *修复Bug：https://www.fabric.io/momo6/android/apps/com.immomo.momo/issues/55e36273f5d3a7f76bab7999
                         */
                        if (start < 0) {
                            start = 0;
                        }
                        return super.replace(start, end, tb, tbstart, tbend);
                    }

                    @Override
                    public SpannableStringBuilder append(char text) {
                        return super.append(text);
                    }
                };
            }
        });
    }


    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new InputView2(super.onCreateInputConnection(outAttrs),false);
    }


    private class InputView2 extends InputConnectionWrapper  {

        public InputView2(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            Log.i("wangrenguang","commitText :"+text);

            if(text.toString().endsWith(".gif")
                    ||text.toString().endsWith(".png")){
                Toast.makeText(SamEdText.this.getContext(),""+text,Toast.LENGTH_LONG).show();
                return true;
            }
            return super.commitText(text,newCursorPosition);
        }


    }
}
