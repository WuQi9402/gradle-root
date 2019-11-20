package org.github.base.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import org.github.base.AbstractEntity
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author JYD_XL
 * @since 2019-11-19
 */
@TableName("sys_post")
class SysPostEntity: AbstractEntity() {

    /** 岗位ID */
    @TableId(value = "post_id", type = IdType.AUTO)
    var postId: Long? = null

    /** 岗位编码 */
    var postCode: String? = null

    /** 岗位名称 */
    var postName: String? = null

    /** 显示顺序 */
    var postSort: Int? = null

    /** 状态（0正常 1停用） */
    var status: String? = null

    /** 创建者 */
    var createBy: String? = null

    /** 创建时间 */
    var createTime: LocalDateTime? = null

    /** 更新者 */
    var updateBy: String? = null

    /** 更新时间 */
    var updateTime: LocalDateTime? = null

    /** 备注 */
    var remark: String? = null

}
