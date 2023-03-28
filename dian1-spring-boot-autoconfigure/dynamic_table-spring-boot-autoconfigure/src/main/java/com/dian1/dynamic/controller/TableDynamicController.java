package com.dian1.dynamic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dian1.dynamic.entity.TableDynamic;
import com.dian1.dynamic.service.TableDynamicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TableDynamic)表控制层
 *
 * @author zhangzhi
 * @since 2022-10-09 10:10:26
 */
@RestController
@RequestMapping("tableDynamic")
public class TableDynamicController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private TableDynamicService tableDynamicService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param tableDynamic 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TableDynamic> page, TableDynamic tableDynamic) {
        return success(this.tableDynamicService.page(page, new QueryWrapper<>(tableDynamic)));
    }

    /**
     * 根据类型查询所有数据
     *
     * @param tableCode 类型
     * @return 所有数据
     */
    @GetMapping("/tableCode/{tableCode}")
    public R tableCode(@PathVariable("tableCode") String tableCode) {
        return success(this.tableDynamicService.tableCode(tableCode));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.tableDynamicService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tableDynamic 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TableDynamic tableDynamic) {
        return success(this.tableDynamicService.save(tableDynamic));
    }

    /**
     * 修改数据
     *
     * @param tableDynamic 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TableDynamic tableDynamic) {
        return success(this.tableDynamicService.updateById(tableDynamic));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.tableDynamicService.removeByIds(idList));
    }
}

