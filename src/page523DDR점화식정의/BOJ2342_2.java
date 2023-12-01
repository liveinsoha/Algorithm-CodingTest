package page523DDR점화식정의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2342_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] quest;
    static long minPower;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(minPower);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = st.countTokens() - 1;
        quest = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            quest[i] = Integer.parseInt(st.nextToken());
        }
        minPower = Long.MAX_VALUE;
    }

    static void solve() {
        dfs(0, 0, 0, 0);
    }

    static void dfs(int i, int j, long power, int idx) {
        if (idx == n) {
            minPower = Math.min(minPower, power);
            return;
        }

        dfs(quest[idx + 1], j, power + getPower(i, quest[idx + 1]), idx + 1);
        dfs(i, quest[idx + 1], power + getPower(j, quest[idx + 1]), idx + 1);
    }

    static int getPower(int before, int after) {
        if (before == 0) {
            return 2;
        } else if (Math.abs(before - after) == 1 || Math.abs(before - after) == 3) {
            return 3;
        } else if (Math.abs(before - after) == 2) {
            return 4;
        } else {
            return 1;
        }
    }
}
