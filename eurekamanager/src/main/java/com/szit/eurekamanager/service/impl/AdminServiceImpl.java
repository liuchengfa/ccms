package com.szit.eurekamanager.service.impl;

import com.szit.eurekamanager.mapper.AdminMapper;
import com.szit.eurekamanager.pojo.Admin;
import com.szit.eurekamanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean adminLogin(Admin admin) {
        boolean isLogin=false;
        Admin returnAdmin=adminMapper.getAdminByAnum(admin.getAnum());
        if(returnAdmin.getaPassword().equals(admin.getaPassword())){
            admin.setPid(returnAdmin.getPid());
            isLogin=true;
        }
        return isLogin;
    }
}
