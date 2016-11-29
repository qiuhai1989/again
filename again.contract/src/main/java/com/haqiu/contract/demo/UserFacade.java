package com.haqiu.contract.demo;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface UserFacade {

    public void addUser();

    public void updateUser();

    public void delUser();

    public String findUser(String username);

}
