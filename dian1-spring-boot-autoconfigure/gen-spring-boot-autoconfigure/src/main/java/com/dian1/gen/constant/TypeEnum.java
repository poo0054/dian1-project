package com.dian1.gen.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
public enum TypeEnum {
    java("java", Arrays.asList(
            JAVATempleConstant.CONTROLLER,
            JAVATempleConstant.ENTITY,
            JAVATempleConstant.FORM,
            JAVATempleConstant.MAPPER,
            JAVATempleConstant.SEARCH_FORM,
            JAVATempleConstant.SERVICE,
            JAVATempleConstant.SERVICE_IMPL,
            JAVATempleConstant.VO,
            JAVATempleConstant.XML
    )),
    vue("vue", Arrays.asList(
            VUETempleConstant.COLUMN,
            VUETempleConstant.EDIT,
            VUETempleConstant.PAGE,
            VUETempleConstant.IMPORT_DIALOG
    ));

    private String name;
    private List<String> ftls;

    TypeEnum(String name, List<String> ftls) {
        this.name = name;
        this.ftls = ftls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFtls() {
        return ftls;
    }

    public void setFtls(List<String> ftls) {
        this.ftls = ftls;
    }
}
