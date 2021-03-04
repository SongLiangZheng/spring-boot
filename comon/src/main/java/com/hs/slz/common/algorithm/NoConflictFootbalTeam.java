package com.hs.slz.common.algorithm;

import cn.hutool.core.lang.Console;

import java.util.List;
import java.util.Map;

public class NoConflictFootbalTeam {
    public static void main(String[] args) {
        new NoConflictFootbalTeam().bestTeamScore(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1});
        new NoConflictFootbalTeam().bestTeamScore(new int[]{9,2,8,8,2}, new int[]{4,1,3,3,5});
        new NoConflictFootbalTeam().bestTeamScore(new int[]{675,541,232,428,760,11,136,890,984,695,249,558}
        , new int[]{93,100,100,73,68,14,68,74,76,18,7,54});


    }
    public int bestTeamScore(int[] scores, int[] ages) {
        Console.log(scores);
        Console.log(ages);
        Console.log();

        int n=scores.length;
        int[] lastMember=new int[n];
        int[] dp=new int[n];
        for(int i=0;i<n;i++){
            if(i==0){
                dp[i]=scores[i];
                lastMember[i]=1;
            }else{
                int[] tempMember=lastMember.clone();
                int conflictSum=0;
                for(int j=0;j<i;j++){
                    if(tempMember[j]==1){
                        if((ages[j]<ages[i]&& scores[j]>scores[i])
                                || (ages[j]>ages[i]&& scores[j]<scores[i]) ){
                            conflictSum+=scores[j];
                            tempMember[j]=0;
                        }
                    }
                }
                int t=scores[i]-conflictSum;
                if(t>0){
                    dp[i]=dp[i-1]+t;
                    lastMember=tempMember;
                    lastMember[i]=1;
                }else{
                    dp[i]=dp[i-1];
                }
            }
            Console.log(lastMember);
        }
        Console.log(dp);
        return dp[n-1];
    }
}
