package com.diazmic.calculator.controller;

import com.diazmic.calculator.engine.BinaryCalculationEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorControllerIntegrationTest {
    @Autowired
    private CalculatorController testController;

    @Autowired
    private BinaryCalculationEngine engine;


    // Expression 1: 10+7
    @Test
    public void Test_1_1_ExpressionOne(){
        Assertions.assertEquals(17.0,testController.calculate("10+7"));
    }

    //Expression 2: 10+7*10
    @Test
    public void Test_1_2_ExpressionTwo(){
        Assertions.assertEquals(80.0,testController.calculate("10+7*10"));
    }

    //Expression 2: 10+7*20/2
    @Test
    public void Test_1_3_ExpressionOne(){
        Assertions.assertEquals(5.28571428571,testController.calculate("2+3-4+5*6/7"));
    }
}
