package com.antsiferov.calculator;

import java.util.LinkedList;
import java.lang.String;

public class SortFacility {

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int priority(char oper) {
        if(oper == '*' || oper == '/') {
            return 1;
        }

        else if(oper == '+' || oper == '-') {
            return 0;
        }

        else {
            return -1;
        }
    }

    public void letGo(LinkedList<Double> st, char oper) {

        Double someOne = st.removeLast();
        Double someTwo = st.removeLast();

        switch(oper) {
            case '+':
                st.add(someTwo + someOne);
                break;
            case '-':
                st.add(someTwo - someOne);
                break;
            case '*':
                st.add(someTwo * someOne);
                break;
            case '/':
                st.add(someTwo / someOne);
                break;
            default:
                System.out.println("Oops");
        }
    }

    public Double eval(String s) {
        LinkedList<Double> someInts = new LinkedList<>();
        LinkedList<Character> someOpers = new LinkedList<>();

        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if(c == '(') {
                someOpers.add('(');
            }

            else if (c == ')') {
                while(someOpers.getLast() != '(') {
                    letGo(someInts, someOpers.removeLast());
                }
                someOpers.removeLast();
            }

            else if (isOperator(c)) {
                while(!someOpers.isEmpty() &&
                        priority(someOpers.getLast()) >= priority(c)) {
                    letGo(someInts, someOpers.removeLast());
                }

                someOpers.add(c);
            }

            else {
                String operand = "";

                while(i < s.length() &&
                        Character.isDigit(s.charAt(i))) {

                    operand += s.charAt(i++);
                }

                --i;
                someInts.add(Double.parseDouble(operand));
            }
        }

        while(!someOpers.isEmpty()) {
            letGo(someInts, someOpers.removeLast());
        }

        return someInts.get(0);
    }
}
