package com.sunxj.leetcode.ArrayLeet;

public class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        int flag = 1;
        for(int i = digits.length-1;i>=0;i--){
            int temp = (digits[i]+flag)%10;
            flag = (digits[i]+flag)/10;
            digits[i] = temp;
        }

        if(flag==1){
            int[] digits1 = new int[digits.length+1];
            digits1[0] = 1;
            for(int i = 0;i<digits.length-1;i++){
                digits1[i+1] = digits[i];
            }
            return digits1;
        }
        return digits;
    }
}
