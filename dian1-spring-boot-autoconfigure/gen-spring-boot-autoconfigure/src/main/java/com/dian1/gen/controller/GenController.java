package com.dian1.gen.controller;

import com.dian1.gen.entity.ResponseResult;
import com.dian1.gen.entity.po.GenTablePO;
import com.dian1.gen.service.GenTableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
@RestController
@RequestMapping("gen")
public class GenController {

    /**
     * 服务对象
     */
    @Resource
    private GenTableService genTableService;

    /**
     * 查询所有表
     *
     * @return 表名
     */
    @GetMapping("showTables")
    public ResponseResult<java.util.List<String>> showTables() {
        return new ResponseResult<>(genTableService.showTables());
    }

    /**
     * 查询所有表
     *
     * @return 表名
     */
    @GetMapping("/tableName/{tableName}")
    public ResponseResult<GenTablePO> getTableNameInfo(@PathVariable("tableName") String tableName) {
        return new ResponseResult<>(genTableService.getTableNameInfo(tableName));
    }

    /**
     * 新增
     *
     * @return 表名
     */
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody GenTablePO genTablePO) {
        return new ResponseResult<>(genTableService.add(genTablePO));
    }

    /**
     * 修改
     *
     * @return 结果
     */
    @PutMapping
    public ResponseResult<Boolean> put(@RequestBody GenTablePO genTablePO) {
        return new ResponseResult<>(genTableService.put(genTablePO));
    }

    /**
     * 预览
     *
     * @return 表名
     */
    @GetMapping("preview/{tableId}")
    public ResponseResult<java.util.Map<String, Object>> preview(@PathVariable("tableId") Long tableId) {
        return new ResponseResult<>(genTableService.preview(tableId));
    }

    /**
     * 下载
     */
    @PostMapping("download/{tableId}")
    public void download(@PathVariable("tableId") Long tableId, HttpServletResponse response) throws IOException {
        byte[] data = genTableService.download(tableId);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"poo0054.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
    }
}
