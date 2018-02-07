package com.al.tobangla.utils;

/**
 * Created by User on 24/1/2018.
 */

public enum ProcessType {

    DATE(1),
    NUMBER(2),
    TIME(3),
    AMOUNT(4),
    ORDINAL_INDICATOR_FOR_DATE(5),
    ORDINAL_INDICATOR_FOR_NUMERIC_ORDER(6),
    ORDINAL_INDICATOR_TODAY(7),

    TODAY(8),
    TIME_NOW(9),
    NOW_DATE_TIME(10);

    int value;

    ProcessType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
