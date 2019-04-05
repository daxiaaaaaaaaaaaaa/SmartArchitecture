package com.architecturedemo.repository;


import android.arch.lifecycle.LiveData;

import com.architecturedemo.base.BaseDto;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.vo.LoginVo;
/**
 * 登陆Repository
 *
 * @author weishixiong
 * @Time 2018-03-30
 */

public interface ILoginRepository {
    /**
     * 登录接口
     *
     * @param baseVo
     * @return
     */
    LiveData<BaseDto<LoginDto>> login(LoginVo baseVo);
}
