package page515최대정사각형점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] original;
    static int[][] maxSquare;
    static int n;
    static int m;
    static int realMaxSquareLen;

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
        m = Integer.parseInt(st.nextToken());
        original = new int[n + 1][m + 1];
        maxSquare = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                original[i][j] = str.charAt(j - 1) - '0';
            }
        }
        realMaxSquareLen = 0;
        initMaxSquare();
    }

    static void solve() {
        sb.append(realMaxSquareLen * realMaxSquareLen);
    }

    static void initMaxSquare() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (original[i][j] == 1) {
                    int max = Math.min(maxSquare[i - 1][j], maxSquare[i][j - 1]);
                    maxSquare[i][j] = Math.min(maxSquare[i - 1][j - 1], max) + 1;
                    realMaxSquareLen = Math.max(realMaxSquareLen, maxSquare[i][j]);
                } else {
                    maxSquare[i][j] = 0;
                }
            }
        }
    }
}
