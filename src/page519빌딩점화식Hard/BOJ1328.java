package page519빌딩점화식Hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1328 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[][][] numOfCase;
    static int n;
    static int l;
    static int r;
    final static long mod = 1000000007L;

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
        r = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        initNumOfCase();
    }

    static void solve() {
        sb.append(numOfCase[n][l][r]);
    }

    static void initNumOfCase() {
        numOfCase = new long[n + 1][n + 1][n + 1];
        numOfCase[1][1][1] = 1;
        if (n <= 1) {
            return;
        }
        numOfCase[2][1][2] = 1;
        numOfCase[2][2][1] = 1;

        for (int k = 3; k <= n; k++) {
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= k; j++) {
                    numOfCase[k][i][j] = (
                            (numOfCase[k - 1][i - 1][j])
                                    + (numOfCase[k - 1][i][j - 1])
                                    + (k - 2) * (numOfCase[k - 1][i][j])
                    )
                            % mod;
                }
            }
        }
    }
}
