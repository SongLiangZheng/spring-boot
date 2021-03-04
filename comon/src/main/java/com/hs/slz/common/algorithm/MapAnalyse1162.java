package com.hs.slz.common.algorithm;

import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MapAnalyse1162 {
    public static void main(String[] args) {
//        输入：[[1,0,1],[0,0,0],[1,0,1]]
//        输出：2
        System.out.println(new MapAnalyse1162().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
//        输入：[[1,0,0],[0,0,0],[0,0,0]]
//        输出：4
        System.out.println(new MapAnalyse1162().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        //-1
        System.out.println(new MapAnalyse1162().maxDistance(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
//[[0,0,1,1,1],[0,1,1,0,0],[0,0,1,1,0],[1,0,0,0,0],[1,1,0,0,1]]
        //2
        System.out.println(new MapAnalyse1162().maxDistance(new int[][]{{0, 0, 1, 1, 1}, {0, 1, 1, 0, 0}, {0, 0, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 1}}));


    }

    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                set.add(grid[i][j]);
            }
        }
        if (set.size() < 2) {
            return -1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else {
                    int a = Integer.MAX_VALUE;
                    int b = Integer.MAX_VALUE;
                    if (i - 1 >= 0) {
                        if (grid[i][j] == grid[i - 1][j]) {
                            a = dp[i - 1][j] == 0 ? 0 : dp[i - 1][j] + 1;
                        } else {
                            a = 1;
                            dp[i - 1][j] = 1;
                        }

                    }
                    if (j - 1 >= 0) {
                        if (grid[i][j] == grid[i][j - 1]) {
                            b = dp[i][j - 1] == 0 ? 0 : dp[i][j - 1] + 1;
                        } else {
                            b = 1;
                            dp[i][j - 1] = 1;
                        }
                    }
                    dp[i][j] = Math.min(a, b);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
//        Console.log(dp);
        return max;
    }

}
