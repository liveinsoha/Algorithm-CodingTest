package page477az사전배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ477 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long k;

    static int aCount;
    static int zCount;
    static long[][] combination;

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
        aCount = Integer.parseInt(st.nextToken());
        zCount = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        initCombination();
    }

    static void solve() {
        getString();
    }

    static void initCombination() {
        int n = aCount + zCount;
        combination = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combination[i][i] = 1;
            combination[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (combination[i - 1][j - 1] + combination[i - 1][j] < 0) {
                    combination[i][j] = Long.MAX_VALUE; //초기화시 오버플로우면 무한대로 설정.
                } else {
                    combination[i][j] = combination[i - 1][j - 1] + combination[i - 1][j];
                }
            }
        }
    }

    static void getString() {
        if (k > combination[aCount + zCount][aCount]) { //k가 크면 리턴
            sb.append(-1);
            return;
        }

        while (aCount > 0 && zCount > 0) {
            if (k > combination[aCount - 1 + zCount][aCount - 1]) {
                sb.append("z");
                k -= combination[aCount - 1 + zCount][aCount - 1];
                zCount--;
            } else {
                sb.append("a");
                aCount--;
            }
        }
        if (aCount == 0) {
            while (zCount > 0) {//z 모두 소비
                sb.append("z");
                zCount--;
            }
        } else {
            while (aCount > 0) {//a 모두 소비
                sb.append("a");
                aCount--;
            }
        }

    }
}
