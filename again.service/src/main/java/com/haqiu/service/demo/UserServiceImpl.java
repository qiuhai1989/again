package com.haqiu.service.demo;

import com.haqiu.contract.demo.UserFacade;
import com.haqiu.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/30.
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserFacade {

/*
    @Autowired
    private UserManager userManager;
*/

    @Override
    public void addUser() {
        System.out.println("-------------invoke addUser()---------------");
    }

    @Override
    public void updateUser() {
        System.out.println("-------------invoke addUser()---------------");
    }

    @Override
    public void delUser() {
        System.out.println("-------------invoke delUser()---------------");
    }

    @Override
    public String findUser(String username) {
        System.out.println("-------------invoke delUser()---------------");
        return "return: " + username;
    }
}
