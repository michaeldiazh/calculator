package com.diazmic.calculator.engine.operation;

import java.util.Comparator;
import java.util.HashMap;

public class OperatorComparator implements Comparator<Character>{
    public final OperatorComparator operatorComparator = new OperatorComparator();
    private HashMap<Character,Short> operatorMap = new HashMap<>();
    public OperatorComparator(){
        operatorMap.put('-',(short)0);
        operatorMap.put('+',(short)1);
        operatorMap.put('/',(short)2);
        operatorMap.put('*',(short)3);
    }

    @Override
    public int compare(Character operatorOne, Character operatorTwo) {
        return operatorMap.get(operatorOne).compareTo(operatorMap.get(operatorTwo));
    }
}
