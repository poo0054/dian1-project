package com.dian1.gen.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dian1.gen.constant.JAVATempleConstant;
import com.dian1.gen.constant.TypeEnum;
import com.dian1.gen.entity.po.GenTablePO;
import com.dian1.gen.entity.po.GenTableRowPO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
public class TemplateUtils {

    public static byte[] download(Configuration configuration, GenTablePO genTablePO) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = null;
        try {
            zip = new ZipOutputStream(byteArrayOutputStream);
            for (TypeEnum value : TypeEnum.values()) {
                for (String ftlName : value.getFtls()) {
                    StringWriter writer = getStringWriter(configuration, genTablePO, value, ftlName);
                    zip.putNextEntry(new ZipEntry(FilePathUtils.getPath(value, ftlName, genTablePO)));
                    zip.write(writer.toString().getBytes(StandardCharsets.UTF_8));
                    zip.flush();
                    zip.closeEntry();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != zip) {
                try {
                    zip.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Map<String, Object> getMapData(Configuration configuration, GenTablePO genTablePO) {
        Map<String, Object> hashMap = new HashMap<>();
        for (TypeEnum value : TypeEnum.values()) {
            for (String ftlName : value.getFtls()) {
                StringWriter writer = getStringWriter(configuration, genTablePO, value, ftlName);
                hashMap.put(ftlName, writer.toString());
            }
        }
        return hashMap;
    }


    public static Map<String, Object> getMapData(GenTablePO table) {
        Map<String, Object> dataMap = BeanUtil.beanToMap(table);
        //将下划线方式命名的字符串转换为驼峰式
        dataMap.put("tableClassName", StrUtil.toCamelCase(table.getTableName()));
        handleData(table.getModelColumn());
        return dataMap;
    }

    private static void handleData(List<GenTableRowPO> rowPOS) {
        for (GenTableRowPO rowPO : rowPOS) {
            if (StrUtil.isBlank(rowPO.getJavaType())) {
                String columnType = rowPO.getColumnType();
                String javaType = JdbcTypeUtils.getJavaType(columnType);
                rowPO.setJavaType(javaType);
            }
        }
    }

    private static StringWriter getStringWriter(Configuration configuration, GenTablePO genTablePO, TypeEnum value, String ftlName) {
        StringWriter writer = new StringWriter();
        try {
            Template template = configuration.getTemplate(
                    value.getName() + "/" + ftlName + JAVATempleConstant.suffix);
            template.process(getMapData(genTablePO), writer);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer;
    }
}
