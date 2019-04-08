package org.github.base.service.impl;

import org.github.base.entity.SysUserEntity;
import org.github.base.mapper.ISysUserMapper;
import org.github.base.service.ISysUserService;
import org.github.base.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sys_user_table 服务实现类
 * </p>
 *
 * @author JYD_XL
 * @since 2019-04-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUserEntity> implements ISysUserService {}
