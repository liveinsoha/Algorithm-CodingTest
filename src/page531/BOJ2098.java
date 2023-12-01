package page531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2098 { //잘못된 논리. 반례 1,3,4까지 3번만에 오는 최단거리에 모두 2를 방문한 경우 NULLPOINTER
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static DP[][] dp;
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
        weight = new int[n + 1][n + 1];
        dp = new DP[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        long min = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            DP dpp = getDP(n, i);
            min = Math.min(dpp.val + weight[i][dpp.startIdx], min);
        }
        sb.append(min);
    }

    static DP getDP(int numOfCity, int here) {
        if (dp[numOfCity][here] != null) {
            return dp[numOfCity][here];
        }
        if (numOfCity == 1) {
            return dp[numOfCity][here] = new DP(0, here);
        }
        long min = Long.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (i == here) {
                continue;
            }
            DP dp = getDP(numOfCity - 1, i);
            if (!dp.visited[here] && weight[i][here] != 0 && dp.val + weight[i][here] < min) {
                min = dp.val + weight[i][here];
                idx = i;
            }
        }
        DP minDP = dp[numOfCity - 1][idx];
        minDP.val = min;
        minDP.visited[here] = true;
        return dp[numOfCity][here] = minDP;
    }

    static class DP {
        long val;
        boolean[] visited = new boolean[n + 1];
        int startIdx;

        DP(long val, int idx) {
            this.val = val;
            this.startIdx = idx;
            visited[idx] = true;
        }
    }
}
