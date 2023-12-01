package page531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2098_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] dp;
    static int n;
    static int[][] weight;

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
        int visit = (int) Math.pow(2, n);
        dp = new int[n + 1][visit];
        weight = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < visit; j++) {
                dp[i][j] = -1;
            }
        }
    }

    static void solve() {
        sb.append(dfsDP(1, 1));
    }

    static int dfsDP(int here, int visit) {

        if ((visit + 1) == 1 << n) {
            if (weight[here][1] != 0) {
                return dp[here][visit] = weight[here][1];
            } else {
                return 17000001;
            }
        }

        if (dp[here][visit] != -1) {
            return dp[here][visit];
        }

        int min = 17000000;
        for (int i = 1; i <= n; i++) {
            if ((visit & 1 << (i - 1)) == 0 && weight[here][i] != 0) {
                min = Math.min(dfsDP(i, visit | (1 << (i - 1))) + weight[here][i], min);
            }
        }
        return dp[here][visit] = min;
    }
}
