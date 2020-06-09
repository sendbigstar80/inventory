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
 * 会员等级表
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsMemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer growthPoint;

    /**
     * 是否为默认等级：0->不是；1->是
     */
    private Integer defaultStatus;

    /**
     * 免运费标准
     */
    private BigDecimal freeFreightPoint;

    /**
     * 每次评价获取的成长值
     */
    private Integer commentGrowthPoint;

    /**
     * 是否有免邮特权
     */
    private Integer priviledgeFreeFreight;

    /**
     * 是否有签到特权
     */
    private Integer priviledgeSignIn;

    /**
     * 是否有评论获奖励特权
     */
    private Integer priviledgeComment;

    /**
     * 是否有专享活动特权
     */
    private Integer priviledgePromotion;

    /**
     * 是否有会员价格特权
     */
    private Integer priviledgeMemberPrice;

    /**
     * 是否有生日特权
     */
    private Integer priviledgeBirthday;

    private String note;


}
