package com.heng.service;

import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception
    {
        System.out.print("请输入第一个数:");
        double num1 = inputNumber();
        System.out.print("请输入运算符:");
        String op = inputOperator();
        System.out.print("请输入第二个数:");
        double num2 = inputNumber();

        double result = Calculator.twoNumberCalcuate(num1, num2, op);
        System.out.println("【结果】" + num1 + op + num2 + "=" + result);
    }

    /**
     * 输入一个数，循环直到正确输入
     * @return
     */
    private static double inputNumber()
    {
        String str_num;
        do
        {
            str_num = scanner.next();
            if(isNumber(str_num)) break;
            else System.out.print("输入错误，请重新输入数字:");
        } while (true);

        return Double.valueOf(str_num);
    }

    /**
     * 输入一个数，循环直到正确输入
     * @return
     */
    private static String inputOperator()
    {
        String str_op;
        do
        {
            str_op = scanner.next();
            if(isOperator(str_op)) break;
            else System.out.print("输入错误，请重新输入运算符:");
        } while (true);
        return str_op;
    }

    /**
     * 判断数字
     * @param str_num 待判断数字
     * @return
     */
    private static boolean isNumber(String str_num)
    {
        try
        {
            Double.valueOf(str_num);
            return true;
        } catch (Exception e)
        {
//            System.out.println(str_num + "不是一个数字");
            return false;
        }
    }

    /**
     * 判断运算符
     * @param op 待判断运算符
     * @return
     */
    private static boolean isOperator(String op)
    {
        String[] str_op = {"+", "-", "*", "/"};
        for (String str : str_op)
        {
            if(Objects.equals(op, str)) return true;
        }
//        System.out.println("运算符必须是" + Arrays.toString(str_op));
        return false;
    }

    private static double twoNumberCalcuate(double num1, double num2, String op) throws Exception
    {
        double result = 0;
        switch (op)
        {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                // TODO: 考虑除0异常
                result = num1 / num2;
                break;
            default:
                throw new Exception("暂时不支持" + op + "运算");
        }
        return result;
    }

}
