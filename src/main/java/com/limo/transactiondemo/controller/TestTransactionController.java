package com.limo.transactiondemo.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.service.ITestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 测试事务 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-09-01
 */
@RestController
@RequestMapping("//test-transaction")
public class TestTransactionController {

    @Autowired
    private ITestTransactionService iTestTransactionService;

    @RequestMapping("/add")
    public String add() {

        return "success";
    }

    @RequestMapping("/update")
    public String update() {

        return "success";
    }

    @RequestMapping("/select")
    public String select() {
        List<TestTransaction> list = iTestTransactionService.list(Wrappers.lambdaQuery(TestTransaction.class));
        return JSONUtil.toJsonStr(list);
    }

    @RequestMapping("/delete")
    public String delete() {

        return "success";
    }
}
