package ${tablePackage}.${modelName}.${functionName}. mapper;

import ${tablePackage}.${modelName}.${functionName}. entity.${tableClassName?cap_first}Entity;
import cn.dian1.core.base.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Mapper
public interface ${tableClassName?cap_first}Mapper extends IBaseMapper
<${tableClassName?cap_first}Entity> {

    }
