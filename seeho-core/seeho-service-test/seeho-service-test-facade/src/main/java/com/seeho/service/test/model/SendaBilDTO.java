package com.seeho.service.test.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Leonpo
 * @since 2025-10-28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendaBilDTO {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单号
     */
    private String billCode;

    /**
     * 是否包号:0不是1是
     */
    private Byte isBag;

    /**
     * 包号
     */
    private String bagCode;

    /**
     * 收件时间
     */
    private LocalDateTime recTime;

    /**
     * 收件网点
     */
    private Long recSiteId;

    /**
     * 收件信息
     */
    private String recInfo;

    /**
     * 到件信息
     */
    private String comeInfo;

    /**
     * 扫描时间
     */
    private LocalDateTime scanTime;

    /**
     * 扫描网点
     */
    private Long scanSiteId;

    /**
     * 扫描人
     */
    private Long scanUserId;

    /**
     * 扫描人所属承包区
     */
    private Long areaId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 结算对象
     */
    private Long accountUserId;

    /**
     * 账单日期(yyyy-mm-dd)
     */
    private LocalDate billDate;

    /**
     * 账单时间(取收件时分秒)
     */
    private LocalDateTime billTime;

    /**
     * 网点重量
     */
    private BigDecimal recWeight;

    /**
     * 中心重量
     */
    private BigDecimal centerWeight;

    /**
     * 结算重量
     */
    private BigDecimal accountWeight;

    /**
     * 第一次称重时间
     */
    private LocalDateTime firstWeightTime;

    /**
     * 目的地省份ID
     */
    private Long dispProvId;

    /**
     * 目的地城市ID
     */
    private Long dispCityId;
}
