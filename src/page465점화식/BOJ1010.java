package page465점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] combination;
    static int m;
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
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        initCombination();
    }

    static void solve() {
        sb.append(combination[m][n]).append("\n");
    }

    static void initCombination() {
        combination = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            combination[i][i] = 1;
        }
        for (int i = 0; i <= m; i++) {
            combination[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= i; j++) {
                combination[i][j] = combination[i - 1][j] + combination[i - 1][j - 1];
            }
        }
    }
}
