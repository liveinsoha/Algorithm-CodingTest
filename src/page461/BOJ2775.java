package page461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2775 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] apart;
    static int k;
    static int n;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            solution();
        }
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        initApart();
    }

    static void solve() {
        sb.append(apart[k][n]).append("\n");
    }

    static void initApart() {
        apart = new int[k + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            apart[0][i] = i;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                apart[i][j] = apart[i][j - 1] + apart[i - 1][j];
            }
        }
    }
}

