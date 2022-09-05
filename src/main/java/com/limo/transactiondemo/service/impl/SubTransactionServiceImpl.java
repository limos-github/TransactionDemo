package com.limo.transactiondemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.exception.BusinessException;
import com.limo.transactiondemo.mapper.TestTransactionMapper;
import com.limo.transactiondemo.service.ISubTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 测试事务 服务实现类
 * </p>
 *
 * @author limo
 * @since 2022-09-01
 */
@Service
public class SubTransactionServiceImpl extends ServiceImpl<TestTransactionMapper, TestTransaction> implements ISubTransactionService {

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagationREQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) throws Exception {
        update(wrapper.set(TestTransaction::getHeight, height));
        errorException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void propagationREQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) throws Exception {
        update(wrapper.set(TestTransaction::getHeight, height));
        errorException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void propagationSUPPORTS(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void propagationNOT_SUPPORTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void propagationNEVER(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void propagationNESTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void propagationMANDATORY(LambdaUpdateWrapper<TestTransaction> wrapper, Long height) {

    }
    @Override
    public void errorBusinessException() throws BusinessException {
        throw new BusinessException(1, "手动异常");
    }

    @Override
    public void errorException() throws Exception {
        throw new Exception();
    }
}
