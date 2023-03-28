package com.dian1.gen.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dian1.gen.entity.GenTable;
import com.dian1.gen.entity.ResponseResult;
import com.dian1.gen.service.GenTableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 自动生成代码-表(GenTable)表控制层
 *
 * @author
 * @since 2023-03-16 10:07:47
 */
@RestController
@RequestMapping("genTable")
public class GenTableController {
    /**
     * 服务对象
     */
    @Resource
    private GenTableService genTableService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param genTable 查询实体
     * @return 所有数据
     */
    @PostMapping("page")
    public ResponseResult selectAll(Page<GenTable> page, GenTable genTable) {
        return new ResponseResult(this.genTableService.page(page, new QueryWrapper<>(genTable)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseResult selectOne(@PathVariable Serializable id) {
        return new ResponseResult(this.genTableService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param genTable 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseResult insert(@RequestBody GenTable genTable) {
        return new ResponseResult(this.genTableService.save(genTable));
    }

    /**
     * 修改数据
     *
     * @param genTable 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseResult update(@RequestBody GenTable genTable) {
        return new ResponseResult(this.genTableService.updateById(genTable));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseResult delete(@RequestParam("idList") List<Long> idList) {
        return new ResponseResult(this.genTableService.removeByIds(idList));
    }
}

