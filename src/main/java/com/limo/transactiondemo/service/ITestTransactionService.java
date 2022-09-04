package com.limo.transactiondemo.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
public interface ITestTransactionService extends IService<TestTransaction> {

    void rollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    void rollbackForException(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight) throws Exception;

    void rollbackForAnyException(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    /**
     * 制造异常
     */
    void errorBusinessException();
    void errorException() throws Exception;
}
