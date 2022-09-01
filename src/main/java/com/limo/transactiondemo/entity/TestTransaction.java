package com.limo.transactiondemo.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
 *
 * CREATE SEQUENCE TEST_TRANSACTION_SEQ
 *     START WITH 1
 *     INCREMENT BY 1
 *     MINVALUE 1 NOMAXVALUE
 *     cache 30;
 *
 * @author zyy
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TEST_TRANSACTION")
@KeySequence("TEST_TRANSACTION_SEQ")
public class TestTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    /**
     * 长
     */
    @TableField("LENGTH")
    private Long length;

    /**
     * 宽
     */
    @TableField("WIDTH")
    private Long width;

    /**
     * 高
     */
    @TableField("HEIGHT")
    private Long height;

    /**
     * 重量
     */
    @TableField("WEIGHT")
    private Long weight;


}
