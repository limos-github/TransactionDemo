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
public interface ISubTransactionService extends IService<TestTransaction> {

    void propagationREQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight) throws Exception;

    void propagationREQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight) throws Exception;

    void propagationSUPPORTS(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    void propagationNOT_SUPPORTED(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    void propagationNEVER(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    void propagationNESTED(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    void propagationMANDATORY(LambdaUpdateWrapper<TestTransaction> wrapper,Long weight);

    /**
     * 制造异常
     */
    void errorBusinessException();
    void errorException() throws Exception;
}
