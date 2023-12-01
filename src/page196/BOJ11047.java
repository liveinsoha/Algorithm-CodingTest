package page196;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int rest;
    static int totalCount;
    static int[] coins;
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
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        rest = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        totalCount = 0;
    }

    static void solve() {
        for (int i = n - 1; i >= 0; i--) {
            totalCount += getCount(coins[i]);
        }
        sb.append(totalCount);
    }

    static int getCount(int coin) {
        int count = 0;
        count = rest / coin;
        rest -= coin * count;
        return count;
    }
}
