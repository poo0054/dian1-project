package ${tablePackage}.${modelName}.${functionName}. service;

import cn.dian1.core.base.IBaseService;
import ${tablePackage}.${modelName}.${functionName}. entity.${tableClassName?cap_first}Entity;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}SearchForm;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}Form;
import ${tablePackage}.${modelName}.${functionName}. vo.${tableClassName?cap_first}VO;
import cn.dian1.core.base.MyPage;

import java.util.List;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
public interface ${tableClassName?cap_first}Service extends IBaseService
<${tableClassName?cap_first}Entity> {
    /**
    * 新增
    *
    * @param form 新增对象
    * @return 主单号
    */
    Long add${tableClassName?cap_first}(${tableClassName?cap_first}Form form);


    /**
    * 根据id查询详情
    *
    * @param id ${tableAnnotation}主键
    * @return ${tableClassName}VO
    */
    ${tableClassName?cap_first}VO get${tableClassName?cap_first}ById(Long id);


    /**
    * 根据主键修改
    * @param form 修改条件
    * @return 修改状态。true标识修改成功，false标识修改失败
    */
    boolean update${tableClassName?cap_first}ById(${tableClassName?cap_first}Form form);


    /**
    * 根据主键删除
    * @param idList 主键
    * @return 删除状态。true标识修改成功，false标识修改失败
    */
    boolean delete${tableClassName?cap_first}ById(List
    <Long> idList);

        /**
        * 分页查询
        *
        * @param form 查询条件
        * @return 分页结果
        */
        MyPage
        <${tableClassName?cap_first}VO> page(${tableClassName?cap_first}SearchForm form);

            /**
            * 批量新增
            *
            * @param ${tableClassName}VOs 新增
            * @return 新增结果
            */
            boolean saveBatch${tableClassName?cap_first}(List
            <${tableClassName?cap_first}VO> ${tableClassName}VOs);

                }