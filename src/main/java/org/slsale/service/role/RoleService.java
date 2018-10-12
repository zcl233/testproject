package org.slsale.service.role;

import org.slsale.pojo.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获取角色列表
     * @return
     * @throws Exception
     */
    public List<Role> getRoleList()throws  Exception;
}
