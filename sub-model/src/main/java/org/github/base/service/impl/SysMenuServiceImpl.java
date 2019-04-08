package org.github.base.service.impl;

import org.github.base.entity.SysMenuEntity;
import org.github.base.mapper.ISysMenuMapper;
import org.github.base.service.ISysMenuService;
import org.github.base.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sys_menu_table 服务实现类
 * </p>
 *
 * @author JYD_XL
 * @since 2019-04-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<ISysMenuMapper, SysMenuEntity> implements ISysMenuService {}
