package com.limo.transactiondemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.exception.BusinessException;
import com.limo.transactiondemo.mapper.TestTransactionMapper;
import com.limo.transactiondemo.service.ISubTransactionService;
import com.limo.transactiondemo.service.ITestTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 测试事务 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2022-09-01
 */
@Service
public class SubTransactionServiceImpl extends ServiceImpl<TestTransactionMapper, TestTransaction> implements ISubTransactionService {

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public String rollbackForBusinessException() {
        String result = "";
        LambdaUpdateWrapper<TestTransaction> wrapper = Wrappers.lambdaUpdate(TestTransaction.class).eq(TestTransaction::getHeight, 1L);
        TestTransaction one = getOne(wrapper);
        System.out.println("前"+ one.toString());
        update(wrapper.set(TestTransaction::getWidth, 8L));
        one = getOne(wrapper);
        System.out.println("后"+ one.toString());
        result = result + one.toString();
        errorBusinessException();
        return result;
    }

    @Override
    public String rollbackForException() {
        return null;
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
