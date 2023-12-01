package page529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11049 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long[][] dp;
    static int[] row;
    static int[] column;
    static int n;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        row = new int[n + 1];
        column = new int[n + 1];
        dp = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            row[i] = Integer.parseInt(st.nextToken());
            column[i] = Integer.parseInt(st.nextToken());
        }
        initDP();
    }

    static void solve() {
        sb.append(dp[1][n]);
    }

    static void initDP() {
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }

        for (int len = 1; len <= n - 1; len++) { //구간 길이가 짧은 것 부터 채워나간다.
            for (int i = 1; i + len <= n; i++) {
                dp[i][i + len] = setDP(i, i + len);
            }
        }

    }

    static long setDP(int a, int b) {
        long min = Long.MAX_VALUE;
        for (int i = a; i < b; i++) {
            min = Math.min(min, dp[a][i] + dp[i + 1][b] + getMulCount(a, i + 1, b));
        }
        return min;
    }

    static long getMulCount(int a, int b, int c) {
        return (long) row[a] * row[b] * column[c];
    }
}
