package com.logistics.cloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 会员积分成长规则表
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberRuleSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 连续签到天数
     */
    private Integer continueSignDay;

    /**
     * 连续签到赠送数量
     */
    private Integer continueSignPoint;

    /**
     * 每消费多少元获取1个点
     */
    private BigDecimal consumePerPoint;

    /**
     * 最低获取点数的订单金额
     */
    private BigDecimal lowOrderAmount;

    /**
     * 每笔订单最高获取点数
     */
    private Integer maxPointPerOrder;

    /**
     * 类型：0->积分规则；1->成长值规则
     */
    private Integer type;


}
