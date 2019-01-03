package org.github.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * sys_role_table
 * </p>
 *
 * @author JYD_XL
 * @since 2019-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "role_id", type = IdType.ID_WORKER_STR)
    private String roleId;

    /**
     * role_name
     */
    private String roleName;

    /**
     * role_desc
     */
    private String roleDesc;

    /**
     * enable
     */
    private Boolean enable;

    /**
     * create_time
     */
    private LocalDateTime createdAt;

    /**
     * update_time
     */
    private LocalDateTime updatedAt;


}
