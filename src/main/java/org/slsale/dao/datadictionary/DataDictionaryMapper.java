package org.slsale.dao.datadictionary;

import org.slsale.pojo.DataDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DataDictionaryMapper {
    public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary)throws Exception;
}
