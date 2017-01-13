package com.example.momo.myapplication.demo.edittext;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

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
        getInputExtras(true).putInt("SOGOU_EXPRESSION", 1);
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
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            try {
//                FileInputStream fileInputStream = new FileInputStream(new File(text.toString()));
//                BitmapFactory.decodeStream(fileInputStream,null,opts);
                BitmapFactory.decodeFile(text.toString(),opts);
                Log.i("wangrenguang",""+opts.outMimeType);
                Log.i("wangrenguang",""+opts.outWidth);
                Log.i("wangrenguang",""+opts.outHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return super.commitText(text,newCursorPosition);
        }


    }
}
