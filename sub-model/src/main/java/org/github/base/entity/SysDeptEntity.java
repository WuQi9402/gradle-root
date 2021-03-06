package org.github.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.github.base.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author JYD_XL
 * @since 2019-12-12
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
public class SysDeptEntity extends AbstractEntity {
    /** UID */
    private static final long serialVersionUID = 1L;

    /** 部门id */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /** 父部门id */
    @TableField("parent_id")
    private Long parentId;

    /** 祖级列表 */
    @TableField("ancestors")
    private String ancestors;

    /** 部门名称 */
    @TableField("dept_name")
    private String deptName;

    /** 显示顺序 */
    @TableField("order_num")
    private Integer orderNum;

    /** 负责人 */
    @TableField("leader")
    private String leader;

    /** 联系电话 */
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @TableField("email")
    private String email;

    /** 部门状态（0正常 1停用） */
    @TableField("status")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @TableField("del_flag")
    private String delFlag;

    /** 创建者 */
    @TableField("create_by")
    private String createBy;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 更新者 */
    @TableField("update_by")
    private String updateBy;

    /** 更新时间 */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
