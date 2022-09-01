package com.limo.transactiondemo.service.impl;

import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.mapper.TestTransactionMapper;
import com.limo.transactiondemo.service.ITestTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
