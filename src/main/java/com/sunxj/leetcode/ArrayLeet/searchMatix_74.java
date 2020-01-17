package com.sunxj.leetcode.ArrayLeet;

public class searchMatix_74 {
    static class Solution {
        public  int len(int[][] matrix){
            return matrix.length;
        }
        public int len(int[] matrix) {
            return matrix.length;
        }
        public boolean searchMatrix(int[][] matrix, int target) {
            int up =0;
            int down = matrix.length-1;
            int row = 0;
            int col = 0;
            if ((len(matrix) == 0) || (len(matrix[0])==0)) return false;
            while(up < down){
                int temp = (int)(up+down)/2+1 ;
                if ((matrix[temp][0]) < target)  up = temp;
                if (matrix[temp][0] > target)  down = temp-1;
                if (matrix[temp][0] == target) return true;
            }
            row = up;
            int left = 0;
            int right = len(matrix[0])-1;
            while(left<right){
                int temp = (int) (left+right)/2 +1;
                if (matrix[row][temp]<target) left = temp;
                if (matrix[row][temp]>target) right = temp - 1;
                if (matrix[row][temp] == target) return true;
            }
            col = left;
            return matrix[row][col] == target;
        }

        public void main(String[] args) {
            System.out.println(1);
        }
    }
}
