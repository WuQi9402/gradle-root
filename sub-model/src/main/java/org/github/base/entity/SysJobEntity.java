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
 * 定时任务调度表
 * </p>
 *
 * @author JYD_XL
 * @since 2019-11-21
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("sys_job")
public class SysJobEntity extends AbstractEntity {
    /** UID */
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;

    /** 任务名称 */
    @TableField("job_name")
    private String jobName;

    /** 任务组名 */
    @TableField("job_group")
    private String jobGroup;

    /** 调用目标字符串 */
    @TableField("invoke_target")
    private String invokeTarget;

    /** cron执行表达式 */
    @TableField("cron_expression")
    private String cronExpression;

    /** 计划执行错误策略（1立即执行 2执行一次 3放弃执行） */
    @TableField("misfire_policy")
    private String misfirePolicy;

    /** 是否并发执行（0允许 1禁止） */
    @TableField("concurrent")
    private String concurrent;

    /** 状态（0正常 1暂停） */
    @TableField("status")
    private String status;

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

    /** 备注信息 */
    @TableField("remark")
    private String remark;
}
