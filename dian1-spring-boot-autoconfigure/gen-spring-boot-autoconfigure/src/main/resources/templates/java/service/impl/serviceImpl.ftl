package ${tablePackage}.${modelName}.${functionName}. service.impl;

import cn.dian1.core.base.BaseServiceImpl;
import cn.dian1.core.base.MyPage;
import cn.dian1.utils.PageUtils;
import cn.dian1.utils.SqlUtils;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}SearchForm;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}Form;
import ${tablePackage}.${modelName}.${functionName}. entity.${tableClassName?cap_first}Entity;
import ${tablePackage}.${modelName}.${functionName}. mapper.${tableClassName?cap_first}Mapper;
import ${tablePackage}.${modelName}.${functionName}. service.${tableClassName?cap_first}Service;
import ${tablePackage}.${modelName}.${functionName}. vo.${tableClassName?cap_first}VO;
import cn.dian1.utils.PageUtils;
import cn.dian1.oms.valid.ValidGroups;

import java.util.List;

import cn.dian1.utils.SqlUtils;
import cn.dian1.utils.LangValidationUtils;
import cn.dian1.utils.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Slf4j
@Service
public class ${tableClassName?cap_first}ServiceImpl extends BaseServiceImpl
<${tableClassName?cap_first}Mapper, ${tableClassName?cap_first}Entity> implements ${tableClassName?cap_first}Service {

/**
* 新增
*
* @param form 新增对象
* @return 主单号
*/
@Override
public Long add${tableClassName?cap_first}(${tableClassName?cap_first}Form form) {
${tableClassName?cap_first}Entity pojo = new ${tableClassName?cap_first}Entity();
BeanUtils.copyProperties(form, pojo);
this.save(pojo);
return pojo.getId();
}

/**
* 根据id查询详情
*
* @param id ${tableAnnotation}主键
* @return ${tableClassName}VO
*/
@Override
public ${tableClassName?cap_first}VO get${tableClassName?cap_first}ById(Long id) {
${tableClassName?cap_first}Entity pojo = this.getById(id);
${tableClassName?cap_first}VO pojoVO = new ${tableClassName?cap_first}VO();
BeanUtils.copyProperties(pojo, pojoVO);
return pojoVO;
}

/**
* 根据主键修改
* @param form 修改条件
* @return 修改状态。true标识修改成功，false标识修改失败
*/
@Override
public boolean update${tableClassName?cap_first}ById(${tableClassName?cap_first}Form form) {
${tableClassName?cap_first}Entity pojo = new ${tableClassName?cap_first}Entity();
BeanUtils.copyProperties(form, pojo);
return this.updateById(pojo);
}

/**
* 根据主键删除
* @param idList 主键
* @return 删除状态。true标识修改成功，false标识修改失败
*/
@Override
public boolean delete${tableClassName?cap_first}ById(List
<Long> idList) {
    return this.removeByIds(idList);
    }

    /**
    * 分页查询
    *
    * @param form 查询条件
    * @return 分页结果
    */
    @Override
    public MyPage
    <${tableClassName?cap_first}VO> page(${tableClassName?cap_first}SearchForm form) {
        LambdaQueryWrapper
        <${tableClassName?cap_first}Entity> queryWrapper = this.getQueryWrapper();
            <#if modelColumn??>
                <#list modelColumn as model>
                    <#if model.isQuery == 1>
                        <#switch model.queryType>
                        <#--                大于-->
                            <#case "gt">
                                queryWrapper.gt(ObjectUtil.isNotEmpty(form.get${model.javaField?cap_first}()), ${tableClassName?cap_first}Entity::get${model.javaField?cap_first}, form.get${model.javaField?cap_first}())
                                ;
                                <#break>
                            <#--                小于-->
                            <#case "lt">
                                queryWrapper.lt(ObjectUtil.isNotEmpty(form.get${model.javaField?cap_first}()), ${tableClassName?cap_first}Entity::get${model.javaField?cap_first}, form.get${model.javaField?cap_first}())
                                ;
                                <#break>
                            <#--                等于  -->
                            <#case "eq">
                                queryWrapper.eq(ObjectUtil.isNotEmpty(form.get${model.javaField?cap_first}()), ${tableClassName?cap_first}Entity::get${model.javaField?cap_first}, form.get${model.javaField?cap_first}())
                                ;
                                <#break>
                            <#--                不等于  -->
                            <#case "ne">
                                queryWrapper.ne(ObjectUtil.isNotEmpty(form.get${model.javaField?cap_first}()), ${tableClassName?cap_first}Entity::get${model.javaField?cap_first}, form.get${model.javaField?cap_first}())
                                ;
                                <#break>
                            <#--                范围  -->
                            <#case "between">
                                if (null != form.get${model.javaField?cap_first}() && ObjectUtil.isAllNotEmpty(form.get${model.javaField?cap_first}())
                                {
                                queryWrapper.between(${tableClassName?cap_first}Entity::get${model.javaField?cap_first},
                                form.get${model.javaField?cap_first}()[0], form.get${model.javaField?cap_first}()[1]);
                                }
                                <#break>
                        </#switch>
                    </#if>
                </#list>
            </#if>
            MyPage
            <${tableClassName?cap_first}Entity> pageList = this.page(new MyPage<>(form.getStart(), form.getSize()),
                queryWrapper);
                return PageUtils.convert(pageList, ${tableClassName?cap_first}VO.class);
                }

                /**
                * 新增
                *
                * @param ${tableClassName}VOs 新增
                * @return 新增结果
                */
                @Override
                public boolean saveBatch${tableClassName?cap_first}(List
                <${tableClassName?cap_first}VO> ${tableClassName}VOs) {
                    List
                    <${tableClassName?cap_first}Entity> ${tableClassName}s = BeanUtil.convertByGson(${tableClassName}
                        VOs,
                        ${tableClassName?cap_first}Entity.class);
                        return saveBatch(${tableClassName}s);
                        }

                        /**
                        * 获取queryLambdaWrapper
                        *
                        * @return queryLambdaWrapper
                        */
                        public LambdaQueryWrapper
                        <${tableClassName?cap_first}Entity> getQueryWrapper() {
                            return SqlUtils.queryLambdaWrapper(${tableClassName?cap_first}Entity.class);
                            }

                            /**
                            * 获取updateLambdaWrapper
                            *
                            * @return updateLambdaWrapper
                            */
                            public LambdaUpdateWrapper
                            <${tableClassName?cap_first}Entity> getUpdateWrapper() {
                                return SqlUtils.updateLambdaWrapper(${tableClassName?cap_first}Entity.class);
                                }

                                }