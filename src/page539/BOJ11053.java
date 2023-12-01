package page539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] arr;
    static int[] dp;
    static int n;
    static int maxLen;

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
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        maxLen = 0;
    }

    static void solve() {
        for (int i = 1; i <= n; i++) {
            initDP(i);
        }
        sb.append(maxLen);
    }

    static void initDP(int k) {
        int max = 0;
        for (int i = k - 1; i >= 1; i--) {
            if (arr[i] < arr[k] && dp[i] > max) {
                max = dp[i];
            }
        }
        maxLen = Math.max(maxLen, dp[k] = max + 1);
    }
}
