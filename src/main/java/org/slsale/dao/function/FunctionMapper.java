package org.slsale.dao.function;

import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FunctionMapper {
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
