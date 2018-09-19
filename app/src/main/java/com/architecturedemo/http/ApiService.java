package com.architecturedemo.http;


import com.architecturedemo.base.BaseDto;
import com.architecturedemo.dto.LoginDto;

import java.util.Map;


import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * api接口
 *
 * @author weishixiong
 * @Time 2018-04-2
 */

public interface ApiService {


    /**
     * 登录
     * post
     * 表单提交
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Flowable<BaseDto<LoginDto>> login(@FieldMap Map<String, String> map);

}


