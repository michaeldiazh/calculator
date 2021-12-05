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

    @Test
    public void Test_1_3_ExpressionOne(){
        Assertions.assertEquals(5.285714285714286,testController.calculate("2+3-4+5*6/7"));
    }

    //-2+3.5*6--5+4*-7
    @Test
    public void Test_1_4_ExpressionOne(){
        Assertions.assertEquals(-4.0,testController.calculate("-2+3.5*6--5+4*-7"));
    }

    @Test
    public void Test_1_5_ExpressionSix(){
        Assertions.assertEquals(-4.0,testController.calculate("((-3+2)+3)*4-(6*2)"));
    }

    @Test
    public void Test_1_6_ExpressionSeven(){
        Assertions.assertEquals(3.0,testController.calculate("1+2"));
    }
}
