package com.architecturedemo.base;


import com.architecturedemo.Constant;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.utils.SPUtil;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository基类
 *
 * @author weishixiong
 * @Time 2018-03-30
 */

public class BaseRepository<T> {
    /**
     * RxJava Subscriber回调
     */
    private BaseHttpSubscriber<T> baseHttpSubscriber;
    /**
     * 解决背压
     */
    private Flowable<BaseDto<T>> flowable;

    /**
     * 发送请求服务器事件
     */
    public BaseHttpSubscriber<T> send() {
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(baseHttpSubscriber);
        return baseHttpSubscriber;
    }

    /**
     * 初始化
     * commonHttpSubscriber = new Common
     */
    public BaseRepository() {
        baseHttpSubscriber = new BaseHttpSubscriber<>();
    }

    /**
     * 初始化flowable
     *
     * @param flowable
     * @return
     */
    public BaseRepository<T> request(Flowable<BaseDto<T>> flowable) {
        this.flowable = flowable;
        return this;
    }

}
