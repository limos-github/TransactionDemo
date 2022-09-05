package com.limo.transactiondemo.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.limo.transactiondemo.entity.TestTransaction;

/**
 * <p>
 * 测试事务 服务类
 * </p>
 *
 * @author limo
 * @since 2022 -09-01
 */
public interface ITestTransactionService extends IService<TestTransaction> {

    void rollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void rollbackForException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception;

    void rollbackForAnyException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagation(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception;

    void propagationA_REQUIRESToB_REQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception;

    void propagationREQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception;

    void propagationREQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagationSUPPORTS(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagationNOT_SUPPORTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagationNEVER(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagationNESTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    void propagationMANDATORY(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight);

    /**
     * 制造异常
     */
    void errorBusinessException();

    void errorException() throws Exception;

    void propagationAToA_REQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception;
}
