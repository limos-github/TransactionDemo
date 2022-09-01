package com.limo.transactiondemo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试事务
 * </p>
 *
 * @author zyy
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TEST_TRANSACTION")
public class TestTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private BigDecimal id;

    /**
     * 长
     */
    @TableField("LENGTH")
    private BigDecimal length;

    /**
     * 宽
     */
    @TableField("WIDTH")
    private BigDecimal width;

    /**
     * 高
     */
    @TableField("HEIGHT")
    private BigDecimal height;

    /**
     * 重量
     */
    @TableField("WEIGHT")
    private BigDecimal weight;


}
