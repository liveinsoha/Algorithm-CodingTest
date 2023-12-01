package page529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049_2 { //탑다운
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
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
    }

    static void solve() {
        sb.append(minCount(1, n));
    }

    static long minCount(int a, int b) {
        if (dp[a][b] != -1) {
            return dp[a][b];
        } else if (a == b) {
            return dp[a][b] = 0;
        } else if (b - a == 1) {
            return dp[a][b] = getMulCount(a, b, b);
        } else {
            long min = Long.MAX_VALUE;
            for (int i = a; i < b; i++) {
                min = Math.min(min, minCount(a, i) + minCount(i + 1, b) + getMulCount(a, i + 1, b));
            }
            return dp[a][b] = min;
        }
    }

    static long getMulCount(int a, int b, int c) {
        return (long) row[a] * row[b] * column[c];
    }
}
