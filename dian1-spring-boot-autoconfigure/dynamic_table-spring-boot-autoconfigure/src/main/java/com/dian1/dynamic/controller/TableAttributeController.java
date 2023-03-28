package com.dian1.dynamic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dian1.dynamic.entity.TableAttribute;
import com.dian1.dynamic.service.TableAttributeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TableAttribute)表控制层
 *
 * @author zhangzhi
 * @since 2022-10-09 09:15:34
 */
@RestController
@RequestMapping("tableAttribute")
public class TableAttributeController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private TableAttributeService tableAttributeService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param tableAttribute 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableAttribute> page, TableAttribute tableAttribute) {
        return success(this.tableAttributeService.page(page, new QueryWrapper<>(tableAttribute)));
    }

    /**
     * 根据类型查询所有数据
     *
     * @param tableCode 类型
     * @return 所有数据
     */
    @GetMapping("/tableCode/{tableCode}")
    public R tableCode(@PathVariable("tableCode") String tableCode) {
        return success(this.tableAttributeService.tableCode(tableCode));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableAttributeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableAttribute 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableAttribute tableAttribute) {
        return success(this.tableAttributeService.save(tableAttribute));
    }

    /**
     * 修改数据
     *
     * @param tableAttribute 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableAttribute tableAttribute) {
        return success(this.tableAttributeService.updateById(tableAttribute));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableAttributeService.removeByIds(idList));
    }
}

