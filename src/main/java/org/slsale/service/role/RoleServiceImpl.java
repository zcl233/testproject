package org.slsale.service.role;

import org.slsale.dao.role.RoleMapper;
import org.slsale.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleList() throws Exception {
        return roleMapper.getRoleList();
    }
}
