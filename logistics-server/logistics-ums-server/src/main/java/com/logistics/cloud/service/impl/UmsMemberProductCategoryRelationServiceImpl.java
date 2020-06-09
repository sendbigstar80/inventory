package com.logistics.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.cloud.domain.UmsMemberProductCategoryRelation;
import com.logistics.cloud.mapper.UmsMemberProductCategoryRelationMapper;
import com.logistics.cloud.service.IUmsMemberProductCategoryRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author zhangwx
 * @since 2020-06-05
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements IUmsMemberProductCategoryRelationService {

}
