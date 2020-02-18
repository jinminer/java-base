package com.jinm.base.chapter5.enumsandannotations.constantspecific;

// Enum type with constant-specific class bodies and data
public enum Operation {

    //加法
    PLUS("+") {
        @Override
        public double apply(double x, double y) { return x + y; }
    },
    //减法
    MINUS("-") {
        @Override
        public double apply(double x, double y) { return x - y; }
    },
    //乘法
    TIMES("*") {
        @Override
        public double apply(double x, double y) { return x * y; }
    },
    //除法
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) { return x / y; }
    };
    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }
    @Override public String toString() { return symbol; }
    public abstract double apply(double x, double y);
}
