package org.slsale.service.DataDictionary;

import org.slsale.dao.datadictionary.DataDictionaryMapper;
import org.slsale.pojo.DataDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;
    @Override
    public List<DataDictionary> getDataDictionaries(DataDictionary dataDictionary) throws Exception {
        return dataDictionaryMapper.getDataDictionaries(dataDictionary);
    }
}
