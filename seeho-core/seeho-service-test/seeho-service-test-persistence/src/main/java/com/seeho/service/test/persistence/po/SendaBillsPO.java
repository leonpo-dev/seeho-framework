package com.seeho.service.test.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 账单明细表
 * </p>
 *
 * @author Leonpo
 * @since 2025-10-28
 */
@Getter
@Setter
@TableName("senda_bills")
public class SendaBillsPO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 目的地来源1订单2建包发3中心发4签收变更
     */
    private Byte dispSource;

    /**
     * 面单使用网点
     */
    private Long billUseSiteId;

    /**
     * 面单发放人id
     */
    private Long billProvideManId;

    /**
     * 面单发放人name
     */
    private String billProvideManName;

    /**
     * 面单发放类型0网点1业务员2客户
     */
    private Byte billProvideType;

    /**
     * 线下单子面单0否1是
     */
    private Byte isOfflineEle;

    /**
     * 承包区/中转费
     */
    private BigDecimal szCenterFee;

    /**
     * 面单费
     */
    private BigDecimal szBillFee;

    /**
     * 附加费/操作费
     */
    private BigDecimal operAddFee;

    /**
     * 普通散客费
     */
    private BigDecimal commonFee;

    /**
     * 均重费
     */
    private BigDecimal avgWeightFee;

    /**
     * 均重加收费
     */
    private BigDecimal avgAddWeightFee;

    /**
     * 是否计算均重费0否1是
     */
    private Byte avgStatus;

    /**
     * 业务员提成
     */
    private BigDecimal userRoyalty;

    /**
     * 二级中转费
     */
    private BigDecimal twoLevelFee;

    /**
     * 面单补贴
     */
    private BigDecimal billSubsidy;

    /**
     * 中天派费
     */
    private BigDecimal dispFee;

    /**
     * 中天基础派费
     */
    private BigDecimal basicDispFee;

    /**
     * 续重派费
     */
    private BigDecimal dispAddFee;

    /**
     * 面单成本
     */
    private BigDecimal billCost;

    /**
     * 中心中转费
     */
    private BigDecimal centerRecFee;

    /**
     * 中心收中费次数
     */
    private Integer centerRecCount;

    /**
     * 寄件账单类型
     */
    private Byte type;

    /**
     * 账单状态0未计算1待计算2已计算4已核销
     */
    private Byte billStatus;

    /**
     * 运输方式
     */
    private Byte classType;

    /**
     * 物品类型1物品2文件
     */
    private Byte goodsType;

    /**
     * 面单类型0线上1普通
     */
    private Byte billType;

    /**
     * 退件状态0否1是
     */
    private Byte returnStatus;

    /**
     * 问题件时间
     */
    private LocalDateTime otherTime;

    /**
     * 数据来源
     */
    private Byte dataSource;

    /**
     * 签收时间
     */
    private LocalDateTime signTime;

    /**
     * 签收网点id
     */
    private Long signSiteId;

    /**
     * 签收状态
     */
    private Byte signStatus;

    /**
     * 派件时间
     */
    private LocalDateTime dispTime;

    /**
     * 派件网点id
     */
    private Long dispSiteId;

    /**
     * 派件重量
     */
    private BigDecimal dispWeight;

    /**
     * 第一次中心操作时间
     */
    private LocalDateTime firstCenterOperTime;

    /**
     * 第一次中心操作网点
     */
    private Long firstCenterOperSiteId;

    /**
     * 第一次中心发件下一站
     */
    private Long firstCenterNextId;

    /**
     * 核销人
     */
    private String hexiaoMan;

    /**
     * 核销时间
     */
    private LocalDateTime hexiaoTime;

    /**
     * 核销金额
     */
    private BigDecimal hexiaoFee;

    /**
     * 费用修改时间
     */
    private LocalDateTime feeModifieTime;

    /**
     * 锁定费用0未锁定1锁定
     */
    private Byte lockStatus;

    /**
     * 锁定或解锁人
     */
    private Long lockMan;

    /**
     * 锁定或解锁时间
     */
    private LocalDateTime lockTime;

    /**
     * 订单来源
     */
    private String orderSource;

    /**
     * 揽收业务员来源
     */
    private Byte userDataSource;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单partner_id
     */
    private String orderPartnerId;

    /**
     * 合作商交易流水号
     */
    private String partnerTradeId;

    /**
     * 订单第三方重量
     */
    private BigDecimal parcelWeight;

    /**
     * 账单版本号
     */
    private Integer billVersion;

    /**
     * 签收派费版本号
     */
    private Integer signDispFeeVersion;

    /**
     * 中心中转费版本号
     */
    private Integer centerRecFeeVersion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 马上寄类型0否1是
     */
    private Byte msgType;

    /**
     * 罚款
     */
    private BigDecimal fine;

    /**
     * 罚款版本号
     */
    private Integer fineVersion;

    /**
     * 面单使用网点与账单网点一致
     */
    private Byte billProvideDiff;

    /**
     * 代收到付类型0代收1到付
     */
    private Byte goodsPaymentType;

    /**
     * 增值服务类型
     */
    private Byte valueAddedType;

    /**
     * 寄件人信息
     */
    private String senderInfo;

    /**
     * 收货人信息
     */
    private String consigneeInfo;

    /**
     * 录单时间
     */
    private LocalDateTime recoOrderTime;

    /**
     * 中心到信息
     */
    private String centerComeInfo;

    /**
     * 扫描人id
     */
    private Long scanManId;

    /**
     * 核销结算对象id
     */
    private Long hexiaoUserId;

    /**
     * 核销流水号
     */
    private String accountWater;

    /**
     * 报价对象ID
     */
    private Long quoteObjectId;
}
