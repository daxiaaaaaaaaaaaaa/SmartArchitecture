package com.architecturedemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.architecturedemo.Constant;
import com.architecturedemo.base.BaseDto;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.repository.ILoginRepository;
import com.architecturedemo.repository.LoginRepository;
import com.architecturedemo.utils.SPUtil;
import com.mujin.keji.architecturedemo.vo.LoginVo;


/**
 * 登录viewmodel
 *
 * @author weishixiong
 * @Time 2018-04-2
 */
public class LoginViewModel extends ViewModel {
    private LiveData<BaseDto<LoginDto>> loginDtoLiveData;
    private ILoginRepository loginRepository;
    public LiveData<BaseDto<LoginDto>> getLoginDtoLiveData() {
     return loginDtoLiveData;
     }
     /**
     * 登录
     * @param username
     * @param password
     */
    public void  login(String username,String password){
        SPUtil.clearData(Constant.SP.SP);
        loginRepository = new LoginRepository();
        LoginVo vo = new LoginVo();
        vo.setPassword(password);
        vo.setUsername(username);
        this.loginDtoLiveData = loginRepository.login(vo);
    }

}
