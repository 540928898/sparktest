package com.sunxj.leetcode.ArrayLeet;

import java.lang.reflect.Array;
import java.util.Arrays;

public class canJump_55 {
    static Boolean canJump(int[] nums) {
        int now = 0;
        while (true) {
            boolean flag = true;
            for (int i = 1; i <= nums[now]; i++) {
                if (now+i>=nums.length-1){
                    return true;
                }
                if (i + nums[now + i] >  nums[now]) {
                    now = now + i;
                    flag = false;
                    break;
                }
            }
            //now = now + nums[now];
            if(flag ){
                now = now+nums[now] ;
            }
            if (now >= nums.length-1) {
                return true;
            }
            if (nums[now] == 0)
                return false;

        }
    }
    static boolean canJump2(int[] nums){
        int res = nums.length-1;
        for(int i = nums.length - 1;i>=0;i--){
            if(i+nums[i] >= res){
                res = i;
            }
        }
        return (res == 0);

    }
    public static void main(String[] args) {
        Boolean t1 = canJump2(new int[]{3,2,1,0,4});
        System.out.println(t1);
    }
}