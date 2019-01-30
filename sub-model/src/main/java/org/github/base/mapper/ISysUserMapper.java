package org.github.base.mapper;

import org.github.base.entity.SysUserEntity;
import org.github.mybatis.MyBatisMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * sys_user_table Mapper 接口
 * </p>
 *
 * @author JYD_XL
 * @since 2019-01-30
 */
@MyBatisMapper
public interface ISysUserMapper extends BaseMapper<SysUserEntity> {}