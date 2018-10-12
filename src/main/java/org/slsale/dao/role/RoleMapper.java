package org.slsale.dao.role;

import org.slsale.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    /**
     * 获取角色列表
     * @return
     * @throws Exception
     */
    public List<Role> getRoleList()throws  Exception;
}
