package com.architecturedemo.base;

import android.arch.lifecycle.MutableLiveData;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.architecturedemo.Constant;
import com.architecturedemo.exception.ApiException;
import com.architecturedemo.exception.ExceptionEngine;
import com.architecturedemo.exception.ServerException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/**
 * 自定义请服务器被观察者
 *
 * @author weishixiong
 * @Time 2018-04-12
 */
public class BaseHttpSubscriber<T> implements Subscriber<BaseDto<T>> {

    //异常类
    private ApiException ex;

    public BaseHttpSubscriber() {
        data = new MutableLiveData();

    }

    private MutableLiveData<BaseDto<T>> data;

    public MutableLiveData<BaseDto<T>> get() {
        return data;
    }

    public void set(BaseDto<T> t) {
        this.data.setValue(t);
    }

    public void onFinish(BaseDto<T> t) {
        set(t);
    }

    @Override
    public void onSubscribe(Subscription s) {
        // 观察者接收事件 = 1个
        s.request(1);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onNext(BaseDto<T> t) {
        if (t.getCode() == Constant.Server.SUCCESS_CODE) {
            onFinish(t);
        }  else {
            ex = ExceptionEngine.handleException(new ServerException(t.getCode(), t.getMsg()));
            getErrorDto(ex);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onError(Throwable t) {
        ex = ExceptionEngine.handleException(t);
        getErrorDto(ex);
    }

    /**
     * 初始化错误的dto
     *
     * @param ex
     */
    private void getErrorDto(ApiException ex) {
        BaseDto dto = new BaseDto();
        dto.setCode(ex.getCode());
        dto.setMsg(ex.getMsg());
        onFinish((BaseDto<T>) dto);
    }

    @Override
    public void onComplete() {
    }
}
