package ${tablePackage}.${modelName}.${functionName}. controller;

import cn.dian1.core.base.MyPage;
import ${tablePackage}.${modelName}.${functionName}. service.${tableClassName?cap_first}Service;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}SearchForm;
import ${tablePackage}.${modelName}.${functionName}. form.${tableClassName?cap_first}Form;
import ${tablePackage}.${modelName}.${functionName}. vo.${tableClassName?cap_first}VO;
import cn.dian1.oms.valid.ValidGroups;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.dian1.utils.BeanUtil;
import cn.dian1.utils.LangErrorUtils;
import cn.dian1.oms.util.excel.EasyExcelUtils;

import java.util.List;
import javax.validation.Valid;


/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Slf4j
@RestController
@RequestMapping("${tableClassName}")
public class ${tableClassName?cap_first}Controller {

private ${tableClassName?cap_first}Service ${tableClassName}Service;

@Autowired
public void set${tableClassName?cap_first}Service(${tableClassName?cap_first}Service ${tableClassName}Service) {
this.${tableClassName}Service = ${tableClassName}Service;
}


/**
* 新增
* @param form 新增数据
* @return 主键
*/
@PostMapping
@PreAuthorize("hasAuthority('${functionName}:${tableClassName}:add')")
public Long add${tableClassName?cap_first}(@RequestBody @Validated(ValidGroups.Add.class) ${tableClassName?cap_first}Form form) {
return ${tableClassName}Service.add${tableClassName?cap_first}(form);
}

/**
* 根据id查询详情
*
* @param id ${tableAnnotation}主键
* @return ${tableClassName}VO
*/
@GetMapping(value = "/{id}")
@PreAuthorize("hasAuthority('${functionName}:${tableClassName}:get')")
public ${tableClassName?cap_first}VO get${tableClassName?cap_first}ById(@PathVariable("id") Long id) {
return ${tableClassName}Service.get${tableClassName?cap_first}ById(id);
}


/**
* 根据主键修改
* @param form 修改条件
* @return 修改状态。true标识修改成功，false标识修改失败
*/
@PutMapping
@PreAuthorize("hasAuthority('${functionName}:${tableClassName}:update')")
public boolean update${tableClassName?cap_first}ById(@RequestBody @Validated(ValidGroups.Update.class) ${tableClassName?cap_first}Form form) {
return ${tableClassName}Service.update${tableClassName?cap_first}ById(form);
}

/**
* 根据主键删除
* @param idList 主键
* @return 删除状态。true标识修改成功，false标识修改失败
*/
@DeleteMapping
@PreAuthorize("hasAuthority('${functionName}:${tableClassName}:delete')")
public boolean delete${tableClassName?cap_first}ById(@RequestParam("idList") List
<Long> idList) {
    return ${tableClassName}Service.removeByIds(idList);
    }

    /**
    * 分页查询
    *
    * @param form 查询条件
    * @return 分页结果
    */
    @PostMapping(value = "/page")
    @PreAuthorize("hasAuthority('${functionName}:${tableClassName}:get')")
    public MyPage
    <${tableClassName?cap_first}VO> page(@RequestBody ${tableClassName?cap_first}SearchForm form) {
        return ${tableClassName}Service.page(form);
        }

        /**
        * 下载模板
        */
        @GetMapping(value = "/downloadTemplate")
        @PreAuthorize("hasAuthority('${functionName}:${tableClassName}:get')")
        public void downloadTemplate() {
        EasyExcelUtils.writeWeb(${tableClassName?cap_first}Form.class, LangErrorUtils.convert("download_template",
        "下载模板"),
        LangErrorUtils.convert("export_template", "导出模板"));
        }

        /**
        * 导入
        *
        * @return 是否成功
        */
        @PostMapping(value = "/download")
        @PreAuthorize("hasAuthority('${functionName}:${tableClassName}:add')")
        public boolean download(MultipartFile file) throws IOException {
        List
        <${tableClassName?cap_first}VO> ${tableClassName}VOs = EasyExcelUtils.readWeb(file, ${tableClassName?cap_first}
            VO.class);
            return ${tableClassName}Service.saveBatch${tableClassName?cap_first}(${tableClassName}VOs);
            }

            /**
            * 导出
            */
            @GetMapping(value = "/export")
            @PreAuthorize("hasAuthority('${functionName}:${tableClassName}:get')")
            public void export(@RequestBody ${tableClassName?cap_first}SearchForm form) {
            MyPage
            <${tableClassName?cap_first}VO> page = ${tableClassName}Service.page(form);
                EasyExcelUtils.writeWeb(page.getList(),${tableClassName?cap_first}VO.class,
                LangErrorUtils.convert("download_template", "下载模板"),
                LangErrorUtils.convert("export_template", "导出模板"));
                }
                }