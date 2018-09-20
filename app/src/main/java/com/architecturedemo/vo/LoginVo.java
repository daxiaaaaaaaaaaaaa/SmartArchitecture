package com.architecturedemo.vo;


import com.architecturedemo.base.BaseVo;

/**
 * 登陆vo
 *
 * @author weishixiong
 * @Time 2018-04-2
 */

public class LoginVo extends BaseVo {
    private String holder;
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
