package com.architecturedemo.http;


import com.architecturedemo.base.BaseDto;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.utils.HttpUtil;
import com.mujin.keji.architecturedemo.vo.LoginVo;

import io.reactivex.Flowable;

public class Api {

    /**
     * 登录接口
     *
     * @param vo
     * @return
     */

    public static Flowable<BaseDto<LoginDto>> login(LoginVo vo) {
        return RequetRetrofit.getInstance().login(HttpUtil.convertVo2Params(vo));
    }

}
