package com.szit.eurekamanager.service;

import com.szit.eurekamanager.pojo.Admin;

public interface AdminService {
    /**
     * 工作人员登录
     * @param admin 工作人员实体对象
     * @return 返回登录是否成功
     */
    boolean adminLogin(Admin admin);

}
