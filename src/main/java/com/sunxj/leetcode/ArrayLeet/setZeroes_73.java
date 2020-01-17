package com.sunxj.leetcode.ArrayLeet;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.json4s.JsonUtil;

import java.lang.reflect.Array;
import java.util.HashSet;

public class setZeroes_73 {
    /**
     * 先记录，后修改
     *
     * 记录的话有两种。第一种通过
     * @param args
     */
    public static void main(String[] args) {
       int[][] matrix = new int[9][9];
//        System.out.println(matrix[0].length);
//        HashSet<Integer> s1 = new HashSet<>();
//        HashSet<Integer> s2 = new HashSet<>();
        int[] s1 = new int[matrix.length];
        int[] s2 = new int[matrix.length];
        matrix[3][3] = 1;
        //选择
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0;(j < matrix.length) &&(s2[j]!= 1);j++){
                if(matrix[i][j]==1){
                    System.out.println(i+"  "+j);
                    s1[i] = s2[i] = 1;
                }
            }

        }
        //涂色
        for (int i = 0; i < matrix.length; i++) {
            if(s1[i]==1){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = 1;
                }
            }
            if(s2[i] == 1){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 1;
                }
            }

        }


    }

}
