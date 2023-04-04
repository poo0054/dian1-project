package com.dian1.http.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhangzhi
 * @date 2023/4/4
 */
@Data
public class Request {
    /**
     * 费用科目代码
     */
    @NotNull
    private String expenseAccountCode;
    /**
     * 费用科目类型
     */
    private String expenseAccountType;
    /**
     * 主键
     */
    private Long id;
    /**
     * 产品代码
     */
    private String productCode;
    /**
     * 产品主键
     */
    private Long productId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 服务类型，取数据字典：入库/增值/出库/尾程/退货
     */
    private String serviceType;

    @NotNull
    @Valid
    private OmsContractHead omsContractHead;
}
