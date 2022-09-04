package com.limo.transactiondemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.exception.BusinessException;
import com.limo.transactiondemo.mapper.TestTransactionMapper;
import com.limo.transactiondemo.service.ITestTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 测试事务 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2022-09-01
 */
@Service
public class TestTransactionServiceImpl extends ServiceImpl<TestTransactionMapper, TestTransaction> implements ITestTransactionService {

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void rollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {
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
    public void errorBusinessException() throws BusinessException {
        throw new BusinessException(1, "手动异常");
    }

    @Override
    public void errorException() throws Exception {
        throw new Exception();
    }
}
