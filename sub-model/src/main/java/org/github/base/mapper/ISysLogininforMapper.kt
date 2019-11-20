package org.github.base.mapper

import org.github.base.IMapper
import org.github.base.entity.SysLogininforEntity
import org.github.mybatis.MyBatisMapper

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author JYD_XL
 * @since 2019-11-19
 */
@MyBatisMapper
interface ISysLogininforMapper: IMapper<SysLogininforEntity>
