package com.limo.transactiondemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limo.transactiondemo.entity.TestTransaction;

/**
 * <p>
 * 测试事务 服务类
 * </p>
 *
 * @author zyy
 * @since 2022 -09-01
 */
public interface ISubTransactionService extends IService<TestTransaction> {

    String rollbackForBusinessException();

    String rollbackForException();

    /**
     * 制造异常
     */
    void errorBusinessException();
    void errorException() throws Exception;
}
