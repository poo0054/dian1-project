package com.dian1.gen.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dian1.gen.entity.GenTableRow;
import com.dian1.gen.entity.ResponseResult;
import com.dian1.gen.service.GenTableRowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 自动生成代码-行(GenTableRow)表控制层
 *
 * @author
 * @since 2023-03-16 10:11:00
 */
@RestController
@RequestMapping("genTableRow")
public class GenTableRowController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private GenTableRowService genTableRowService;

    /**
     * 根据tableId查询所有
     *
     * @return 所有数据
     */
    @GetMapping("table/{tableId}")
    public ResponseResult table(@PathVariable("tableId") Long tableId) {
        return new ResponseResult(this.genTableRowService.getByTable(tableId));
    }

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param genTableRow 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseResult selectAll(Page<GenTableRow> page, GenTableRow genTableRow) {
        return new ResponseResult(this.genTableRowService.page(page, new QueryWrapper<>(genTableRow)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseResult selectOne(@PathVariable Serializable id) {
        return new ResponseResult(this.genTableRowService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param genTableRow 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult insert(@RequestBody GenTableRow genTableRow) {
        return new ResponseResult(this.genTableRowService.save(genTableRow));
    }

    /**
     * 修改数据
     *
     * @param genTableRow 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseResult update(@RequestBody GenTableRow genTableRow) {
        return new ResponseResult(this.genTableRowService.updateById(genTableRow));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseResult delete(@RequestParam("idList") List<Long> idList) {
        return new ResponseResult(this.genTableRowService.removeByIds(idList));
    }
}

