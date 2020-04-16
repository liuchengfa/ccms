package com.szit.eurekamanager.mapper;

import com.szit.eurekamanager.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("adminMapper")
public interface AdminMapper {
    /**
     * 通过账号查看工作人员信息
     * @param anum 账号
     * @return Admin实体对象
     */
    Admin getAdminByAnum(@Param("anum") String anum);

}
