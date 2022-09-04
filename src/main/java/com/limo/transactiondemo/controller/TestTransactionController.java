package com.limo.transactiondemo.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.limo.transactiondemo.entity.TestTransaction;
import com.limo.transactiondemo.service.ITestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        List<TestTransaction> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new TestTransaction().setLength(Long.valueOf(i))
                    .setWidth(Long.valueOf(i)).setHeight(Long.valueOf(i)).setWeight(Long.valueOf(i * i * i)));
        }
        iTestTransactionService.saveBatch(list);
        return "success";
    }

    @RequestMapping("/update")
    public String update(Long weight) {
        LambdaUpdateWrapper<TestTransaction> wrapper = Wrappers.lambdaUpdate(TestTransaction.class).eq(TestTransaction::getId, 1L);
        iTestTransactionService.update(wrapper.set(TestTransaction::getWeight, weight));
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


    @RequestMapping("/rollbackForBusinessException")
    public String rollbackForBusinessException(Long weight) {
        int i = 0;
        StringJoiner sj = new StringJoiner("\n");
        sj.add("");
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "rollbackForBusinessException"));
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "wrapper.eq(TestTransaction::getId, 1L)"));
        LambdaUpdateWrapper<TestTransaction> wrapper = Wrappers.lambdaUpdate(TestTransaction.class).eq(TestTransaction::getId, 1L);
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        try {
            iTestTransactionService.rollbackForBusinessException(wrapper, weight);
        } catch (Exception e) {
            sj.add(JSONUtil.toJsonStr(++i + "↓    " + "\n" +
                    "    @Override\n" +
                    "    @Transactional(rollbackFor = BusinessException.class)\n" +
                    "    public void rollbackForBusinessException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {\n" +
                    "        update(wrapper.set(TestTransaction::getWeight, weight));\n" +
                    "        errorBusinessException();\n" +
                    "    }"));
        }
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        return sj.toString();
    }

    @RequestMapping("/rollbackForException")
    public String rollbackForException(Long weight) {
        int i = 0;
        StringJoiner sj = new StringJoiner("\n ");
        sj.add("");
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "rollbackForException"));
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "wrapper.eq(TestTransaction::getId, 1L)"));
        LambdaUpdateWrapper<TestTransaction> wrapper = Wrappers.lambdaUpdate(TestTransaction.class).eq(TestTransaction::getId, 1L);
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        try {
            iTestTransactionService.rollbackForException(wrapper, weight);
        } catch (Exception e) {
            sj.add(JSONUtil.toJsonStr(++i + "↓    " + "\n" +
                    "    @Override\n" +
                    "    @Transactional(rollbackFor = BusinessException.class)\n" +
                    "    public void rollbackForException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) throws Exception {\n" +
                    "        update(wrapper.set(TestTransaction::getWeight, weight));\n" +
                    "        errorException();\n" +
                    "    }\n"));
        }
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        return sj.toString();
    }

    @RequestMapping("/rollbackForAnyException")
    public String rollbackForAnyException(Long weight) {
        int i = 0;
        StringJoiner sj = new StringJoiner("\n ");
        sj.add("");
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "rollbackForAnyException"));
        sj.add(JSONUtil.toJsonStr(++i + "↓    " + "wrapper.eq(TestTransaction::getId, 1L)"));
        LambdaUpdateWrapper<TestTransaction> wrapper = Wrappers.lambdaUpdate(TestTransaction.class).eq(TestTransaction::getId, 1L);
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        try {
            iTestTransactionService.rollbackForAnyException(wrapper, weight);
        } catch (Exception e) {
            sj.add(JSONUtil.toJsonStr(++i + "↓    " + "\n" +
                    "    @Override\n" +
                    "    @Transactional(rollbackFor = Exception.class)\n" +
                    "    public void rollbackForAnyException(LambdaUpdateWrapper<TestTransaction> wrapper, Long weight) {\n" +
                    "        update(wrapper.set(TestTransaction::getWeight, weight));\n" +
                    "        List<Integer> list = Arrays.asList(1, 1);\n" +
                    "        list.get(10);\n" +
                    "    }"));
        }
        sj.add(++i + "↓    " + JSONUtil.toJsonStr(iTestTransactionService.getOne(wrapper)));
        return sj.toString();
    }

}
