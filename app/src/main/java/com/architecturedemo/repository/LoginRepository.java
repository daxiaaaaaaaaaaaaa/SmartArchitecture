package com.architecturedemo.repository;

import android.arch.lifecycle.LiveData;

import com.architecturedemo.base.BaseDto;
import com.architecturedemo.base.BaseRepository;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.http.Api;
import com.mujin.keji.architecturedemo.vo.LoginVo;


/**
 * 登陆Repository
 *
 * @author weishixiong
 * @Time 2018-03-30
 */
public class LoginRepository extends BaseRepository<LoginDto> implements ILoginRepository {
    /**
     * 登录接口
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<LoginDto>> login(LoginVo vo) {
        return request(Api.login(vo)).send().get();
    }
}
