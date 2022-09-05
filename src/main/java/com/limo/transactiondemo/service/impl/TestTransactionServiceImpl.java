package com.limo.transactiondemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.exception.BusinessException;
import com.limo.transactiondemo.mapper.TestTransactionMapper;
import com.limo.transactiondemo.service.ISubTransactionService;
import com.limo.transactiondemo.service.ITestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 测试事务 服务实现类
 * </p>
 *
 * @author limo
 * @since 2022-09-01
 */
@Service
public class TestTransactionServiceImpl extends ServiceImpl<TestTransactionMapper, TestTransaction> implements ITestTransactionService {

    @Autowired
    private ISubTransactionService iSubTransactionService;

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 3)
    public void timeOut(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
        update(wrapper.set(TestTransaction::getWeight, weight));
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void rollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
        update(wrapper.set(TestTransaction::getWeight, weight));
        errorBusinessException();
    }

    @Override
    @Transactional(noRollbackFor = BusinessException.class, rollbackFor = Exception.class)
    public void noRollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
        update(wrapper.set(TestTransaction::getWeight, weight));
        errorBusinessException();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void rollbackForException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception {
        update(wrapper.set(TestTransaction::getWeight, weight));
        errorException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rollbackForAnyException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
        update(wrapper.set(TestTransaction::getWeight, weight));
        List<Integer> list = Arrays.asList(1, 1);
        list.get(10);
    }

    @Override
    public void propagation(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception {
        update(wrapper.set(TestTransaction::getWeight, weight));
        iSubTransactionService.propagationREQUIRED(wrapper, weight);
    }

    @Override
    public void propagationAToA_REQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception {
        update(wrapper.set(TestTransaction::getWeight, weight));
        propagationREQUIRED(wrapper, weight);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagationA_REQUIRESToB_REQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception {
        update(wrapper.set(TestTransaction::getWeight, weight));
        iSubTransactionService.propagationREQUIRES_NEW(wrapper, weight);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagationA_REQUIRESTryToB_REQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
        update(wrapper.set(TestTransaction::getWeight, weight));
        try {
            iSubTransactionService.propagationREQUIRES_NEW(wrapper, weight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagationREQUIRED(LambdaUpdateWrapper<TestTransaction> wrapper, Long width) throws Exception {
        update(wrapper.set(TestTransaction::getWidth, width));
        errorException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void propagationREQUIRES_NEW(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void propagationSUPPORTS(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void propagationNOT_SUPPORTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void propagationNEVER(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void propagationNESTED(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void propagationMANDATORY(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {

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
