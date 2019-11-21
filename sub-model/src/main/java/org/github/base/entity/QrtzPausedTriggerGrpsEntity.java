package org.github.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.github.base.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author JYD_XL
 * @since 2019-11-21
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("qrtz_paused_trigger_grps")
public class QrtzPausedTriggerGrpsEntity extends AbstractEntity {
    /** UID */
    private static final long serialVersionUID = 1L;

    @TableId("sched_name")
    private String schedName;

    @TableField("trigger_group")
    private String triggerGroup;
}
