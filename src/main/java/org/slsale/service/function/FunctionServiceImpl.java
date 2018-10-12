package org.slsale.service.function;

import org.slsale.dao.function.FunctionMapper;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FunctionServiceImpl implements FunctionService {
    @Resource
    private FunctionMapper functionMapper;
    @Override
    public List<Function> getMainFunctionList(Authority authority) throws Exception {
        return functionMapper.getMainFunctionList(authority);
    }

    @Override
    public List<Function> getSubFunctionList(Function function) throws Exception {
        return functionMapper.getSubFunctionList(function);
    }
}
