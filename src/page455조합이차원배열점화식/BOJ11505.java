package page455조합이차원배열점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11505 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int r;
    static int n;
    static int[][] combination;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);

        for (int[] arr : combination) {
            System.out.println(Arrays.toString(arr));
        }
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        initCombinationArr();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        sb.append(combination[n][r]);
    }

    static void initCombinationArr() {
        combination = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combination[i][i] = 1;
            combination[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                combination[i][j] = combination[i - 1][j] + combination[i - 1][j - 1];
            }
        }
    }
}
