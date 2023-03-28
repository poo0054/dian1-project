package com.dian1.gen.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.dian1.gen.constant.JAVATempleConstant;
import com.dian1.gen.constant.TypeEnum;
import com.dian1.gen.constant.VUETempleConstant;
import com.dian1.gen.entity.po.GenTablePO;

/**
 * @author zhangzhi
 * @date 2023/3/20
 */
public class FilePathUtils {
    public static final String BASE_PATH = "src/main/java/";


    public static String getPath(TypeEnum value, String ftlName, GenTablePO genTablePO) {
        StringBuffer buffer = new StringBuffer();
        if (value.equals(TypeEnum.java)) {
            //java
            buffer.append(TypeEnum.java.getName()).append("/");
            buffer.append(BASE_PATH);
            buffer.append(genTablePO.getTablePackage().replace(".", "/"));
            buffer.append("/");
            buffer.append(genTablePO.getModelName().replace(".", "/"));
            buffer.append("/");
            buffer.append(genTablePO.getFunctionName().replace(".", "/"));
            buffer.append("/");
            buffer.append(ftlName.substring(0, ftlName.lastIndexOf("/") + 1));
            buffer.append(CharSequenceUtil.upperFirst(StrUtil.toCamelCase(genTablePO.getTableName())));
            String substring = ftlName.substring(ftlName.lastIndexOf("/") + 1);
            //全部大写
            if (substring.equals("vo")) {
                buffer.append(CharSequenceUtil.swapCase(substring));
            } else {
                //首字母大写
                buffer.append(CharSequenceUtil.upperFirst(substring));
            }
            buffer.append(".");
            if (ftlName.equals(JAVATempleConstant.XML)) {
                buffer.append("xml");
            } else {
                buffer.append(value.getName());
            }
        } else {
            //vue
            buffer.append(TypeEnum.vue.getName()).append("/");
            buffer.append(ftlName);
            buffer.append(".");
            if (ftlName.equals(VUETempleConstant.COLUMN)) {
                buffer.append("js");
            } else {
                buffer.append(value.getName());
            }
        }

        return buffer.toString();
    }
}
