package org.slsale.service.function;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;

import java.util.List;

public interface FunctionService {
    /**
     * getMainFunctionList
     * @param authority
     * @return
     * @throws Exception
     */
    public List<Function> getMainFunctionList(Authority authority)throws Exception;

    /**
     * getSubFunctionList
     * @param function
     * @return
     * @throws Exception
     */
    public List<Function> getSubFunctionList(Function function)throws Exception;
}
