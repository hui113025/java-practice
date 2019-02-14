package com.zheng.example;

/**
 * Enum, 谢绝滥用,禁止直接作为 PO 的字段。
 */
public enum YearIncomeGrade {
    // 枚举常量必须放在最开始的地方
    A("A", null, 500000),
    B("B", 500001, 1000000),
    C("C", 1000001, 2000000),
    D("D", 2000001, 3000000),
    E("E", 3000001, 4000000),
    F("F", 4000001, 5000000),
    G("G", 5000001, null)
    ;
    // 构造函数
    YearIncomeGrade(String gradeLevel, Integer minValue, Integer maxValue) {
        this.gradeLevel = gradeLevel;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    // 收入等级 - ABCDEF
    public String gradeLevel;
    // 最小收入
    public Integer minValue;
    // 最大收入
    public Integer maxValue;

    // + getter
    // + setter?

    // 解析
    public static YearIncomeGrade parseByIncome(int income) {
        //
//        YearIncomeGrade[] enumConstants = YearIncomeGrade.class.getEnumConstants();
        for (YearIncomeGrade yearIncomeGrade : values()) {
            Integer min = yearIncomeGrade.minValue;
            Integer max = yearIncomeGrade.maxValue;
            //
            if (null != min) {
                if (income < min) {
                    continue; // 不符合
                }
            }
            //
            if (null != max) {
                if (income > max) {
                    continue; // 不符合
                }
            }
            return yearIncomeGrade;
        }
        return null;
    }

    //
    public static void main(String[] a){
        int income1 = 34685;
        int income2 = 4464465;
        int income3 = 24546585;
        int income4 = 1454685;
        int income5 = 3465485;
        //
        System.out.println("" + income1 + " => " + YearIncomeGrade.parseByIncome(income1).gradeLevel);
        System.out.println("" + income2 + " => " + YearIncomeGrade.parseByIncome(income2).gradeLevel);
        System.out.println("" + income3 + " => " + YearIncomeGrade.parseByIncome(income3).gradeLevel);
        System.out.println("" + income4 + " => " + YearIncomeGrade.parseByIncome(income4).gradeLevel);
        System.out.println("" + income5 + " => " + YearIncomeGrade.parseByIncome(income5).gradeLevel);
    }
}
