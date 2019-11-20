package org.github.base.service.impl

import org.github.base.entity.QrtzSimpropTriggersEntity
import org.github.base.mapper.IQrtzSimpropTriggersMapper
import org.github.base.service.IQrtzSimpropTriggersService
import org.github.base.ServiceImpl
import org.springframework.stereotype.Service

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JYD_XL
 * @since 2019-11-19
 */
@Service
class QrtzSimpropTriggersServiceImpl: ServiceImpl<IQrtzSimpropTriggersMapper, QrtzSimpropTriggersEntity>(), IQrtzSimpropTriggersService