package com.women.JOLI.base;

import android.support.annotation.CallSuper;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.women.JOLI.app.App;
import com.women.JOLI.callback.RequestCallback;
import com.women.JOLI.utils.NetUtil;
import com.socks.library.KLog;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {

    private RequestCallback<T> mRequestCallback;

    public BaseSubscriber(RequestCallback<T> requestCallback) {
        mRequestCallback = requestCallback;
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (mRequestCallback != null) {
            mRequestCallback.beforeRequest();
        }
    }

    @CallSuper
    @Override
    public void onCompleted() {
        if (mRequestCallback != null) {
            mRequestCallback.requestComplete();
        }
    }

    @CallSuper
    @Override
    public void onError(Throwable e) {
        if (mRequestCallback != null) {
            String errorMsg = null;
            if (e instanceof HttpException) {
                switch (((HttpException) e).code()) {
                    case 403:
                        errorMsg = "没有权限访问此链接！";
                        break;
                    case 504:
                        if (!NetUtil.isConnected(App.getContext())) {
                            errorMsg = "没有联网哦！";
                        } else {
                            errorMsg = "网络连接超时！";
                        }
                        break;
                    default:
                        errorMsg = ((HttpException) e).message();
                        break;
                }
            } else if (e instanceof UnknownHostException) {
                errorMsg = "不知名主机！";
            } else if (e instanceof SocketTimeoutException) {
                errorMsg = "网络连接超时！";
            }else if (e instanceof JsonMappingException){
                errorMsg = "未知异常！";
            }
            KLog.e(e.toString());
            mRequestCallback.requestError(errorMsg);
        }
    }

    @CallSuper
    @Override
    public void onNext(T t) {
        if (mRequestCallback != null) {
            mRequestCallback.requestSuccess(t);
        }
    }
}
