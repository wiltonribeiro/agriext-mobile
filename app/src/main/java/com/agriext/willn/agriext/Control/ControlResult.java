package com.agriext.willn.agriext.Control;
import android.os.Handler;

import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.Entity.Result;

public class ControlResult implements CallBack {
    public static Result result;

    public ControlResult() {

    }

    public void calculate(final Culture culture, final CallBack callBack){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                result = new Result(culture, 50);callBack.methodToCallBack();
            }
        }, 5000);
    }

    public void methodToCallBack() {

    }
}
