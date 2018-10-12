package org.slsale.service.DataDictionary;

import org.slsale.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary)throws Exception;
}
