package com.doppel.leetcode;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class AssignCookie {
    public static void main(String[] args) {
        int[] children = {1, 2, 5, 6, 8, 19, 7, 9, 2};
        int[] cookies = {1, 3, 10, 7, 4, 20};

        int[] test = {1209,830,712,457,1373,1078,976,1487,1353,699,906,1092,362,385,1247,816,412,606,1325,108,1404,1382,587,350,1421,1071,60,278,571,501,891,471,920,785,387,1028,444,476,273,376,521,1037,1500,54,992,218,1006,381,419,185,74,334,998,1113,72,80,950,1433,808,1460,991,131,677,1214,1230,250,584,590,764,1096,692,953,892,805,784,1501,924,386,881,278,684,239,1391,865,730,975,846,63,1267,1453,289,1083,877,1539,1066,699,1546,1012,1285,1380,868,1026,856,397,861,602,934,915,386,1217,964,863,403,100,1498,1289,352,397,904,1307,847,1526,513,1467,63,1194,829,1051,857,873,1467,131,1515,1156,592,212,1395,223,55,447,1397,42,1004,1452,1264,62,977,663,973,108,1292,693,1479,1392,397,275,1253,992,167,42,981,427,48,751,579,508,574,1505,659,1044,1211,847,1131,655,161,175,101,1195,121,237,1143,1359,702,189,1077,257,658,479,504,1260,414,633,894,1068,209,1531,1054,430,48,1129,542,596,466,1147,148,70};

        int contentChildren = new AssignCookie().findContentChildren(children, cookies);
        System.out.println("contentChildren = " + contentChildren);
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        outer:
        for (int i = 0; i < g.length; i++) {
            for (int j = i; j < s.length; j++) {
                if (s[j] >= g[i]) {
                    result = result + 1;
                    continue outer;
                }
            }
        }
        return result;
    }
}
