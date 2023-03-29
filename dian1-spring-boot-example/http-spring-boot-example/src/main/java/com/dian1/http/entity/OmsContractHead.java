package com.dian1.http.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangzhi
 * @date 2023/3/29
 */
@NoArgsConstructor
@Data
public class OmsContractHead {

    private List<OrdersDTO> orders;
    private Boolean isSearchCount;
    private Integer size;
    private Integer start;
    private DataScopeSearchFormDTO dataScopeSearchForm;
    private Boolean optimizeCountSql;
    private List<ListDTO> list;
    private String countId;
    private Integer totalCount;
    private Boolean hitCount;
    private Integer maxLimit;

    @NoArgsConstructor
    @Data
    public static class DataScopeSearchFormDTO {
        private Integer size;
        private String alias;
        private String endTime;
        private Integer start;
        private String sortField;
        private List<Integer> scopeId;
        private Boolean openDataScope;
        private String sortType;
        private Integer dataScope;
        private Boolean paging;
        private String scopeName;
        private String startTime;
    }

    @NoArgsConstructor
    @Data
    public static class OrdersDTO {
        private String column;
        private Boolean asc;
    }

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private String remark;
        private String customerName;
        private String productName;
        private String auditStatus;
        private String custCode;
        private String templateName;
        private String expirationTime;
        private String custName;
        private List<DetailsDTO> details;
        private String templateCode;
        private String effectiveTime;
        private String updateTime;
        private String createTime;
        private String productCode;
        private Integer updateUserId;
        private String contractCode;
        private Integer id;
        private Integer createUserId;

        @NoArgsConstructor
        @Data
        public static class DetailsDTO {
            private String expenseAccountName;
            private Integer minAmountTh;
            private Integer id;
            private Integer updateUserId;
            private String createTime;
            private String expenseAccountType;
            private String remark;
            private List<PackageFeesDTO> packageFees;
            private String expenseAccountCode;
            private Integer price;
            private Integer createUserId;
            private String serviceType;
            private String contractCode;
            private Integer contractId;
            private String unit;
            private String updateTime;
            private String currency;
            private Integer minCountTh;
            private List<StorageFeesDTO> storageFees;

            @NoArgsConstructor
            @Data
            public static class PackageFeesDTO {
                private Integer ticketAmount;
                private String contractCode;
                private Integer id;
                private Integer detailId;
                private Integer renewalWeightPrice;
                private String unit;
                private Integer createUserId;
                private String packageType;
                private Integer maxWeight;
                private String updateTime;
                private Integer updateUserId;
                private String createTime;
                private String remark;
                private Integer minWeight;
            }

            @NoArgsConstructor
            @Data
            public static class StorageFeesDTO {
                private String contractCode;
                private String updateTime;
                private Integer id;
                private Integer endDays;
                private Integer peakSeasonPrice;
                private Integer price;
                private String specCode;
                private String createTime;
                private Integer detailId;
                private Integer minVolume;
                private Integer beginDays;
                private Integer updateUserId;
                private String remark;
                private Integer createUserId;
            }
        }
    }
}
