package org.github.base.mapper;

import org.github.base.entity.SysRoleMenuEntity;
import org.github.base.IMapper;
import org.github.mybatis.MyBatisMapper;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author JYD_XL
 * @since 2019-12-12
 */
@MyBatisMapper
public interface ISysRoleMenuMapper extends IMapper<SysRoleMenuEntity> {}
