package com.example.pupil_test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Subject {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;

    private int firstNum;
    private int secondNum;

    private int signal;

    private double answer;
    private double result;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String subject;

    Subject(int id, String subject, double result, double answer) {
        this.setId(id);
        this.setSubject(subject);
        this.setResult(result);
        this.setAnswer(answer);
    }

    public Subject(String subject, double result, double answer) {
        this.setSubject(subject);
        this.setResult(result);
        this.setAnswer(answer);
    }

    public Subject() {
    }

    public String generateSubject() {
        Random random = new Random();
        firstNum = random.nextInt(501);
        secondNum = random.nextInt(101);
        signal = random.nextInt(2);

        switch (signal) {
            case 0: {
                answer = firstNum * secondNum;
                return firstNum + "*" + secondNum + "=";
            }
            case 1: {
                while (firstNum < secondNum) {
                    secondNum = random.nextInt(101);
                }
                /*answer = (double) firstNum / (double) secondNum;
                answer = Math.round(answer * 100.0) / 100.0;
                NumberFormat nf = new DecimalFormat("#0.00");
                answer = Double.parseDouble(nf.format(answer));
                return firstNum + "/" + secondNum + "=";*/
                BigDecimal answer0 = BigDecimal.valueOf((double) firstNum / (double) secondNum).setScale(2, RoundingMode.HALF_UP);
                answer = answer0.doubleValue();
                return firstNum + "/" + secondNum + "=";
            }
            default:
                break;
        }
        return null;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }
}
