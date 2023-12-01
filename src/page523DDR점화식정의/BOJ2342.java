package page523DDR점화식정의;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2342 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[][][] dp;
    static int n;
    static int[] quest;


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
        n = st.countTokens() - 1;
        quest = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            quest[i] = Integer.parseInt(st.nextToken());
        }

        initDP();
    }

    static void solve() {
        sb.append(getMin());
    }

    static long getMin() {
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (dp[n][i][j] != Long.MAX_VALUE) {
                    min = Math.min(min, dp[n][i][j]);
                }
            }
        }
        return min;
    }

    static void initDP() {
        dp = new long[n + 1][5][5];

        for (int k = 1; k <= n; k++) {
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    dp[k][i][j] = Long.MAX_VALUE;
                }
            }
        }

        dp[1][0][quest[1]] = 2;
        dp[1][quest[1]][0] = 2;


        for (int k = 2; k <= n; k++) {
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (dp[k - 1][i][j] != Long.MAX_VALUE) {
                        if (quest[k] != j) {
                            dp[k][quest[k]][j] = Math.min(dp[k][quest[k]][j], dp[k - 1][i][j] + getPower(i, quest[k]));
                        }
                        if (quest[k] != i) {
                            dp[k][i][quest[k]] = Math.min(dp[k][i][quest[k]], dp[k - 1][i][j] + getPower(j, quest[k]));
                        }
                    }
                }
            }
        }
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
