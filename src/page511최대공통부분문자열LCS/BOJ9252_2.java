package page511최대공통부분문자열LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9252_2 { // 점화식 정의
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static String str1;
    static String str2;
    static int[][] LCS;
    static int LCSLen;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        str1 = br.readLine().trim();
        str2 = br.readLine().trim();
        n = str1.length();
        m = str2.length();
        LCS = new int[n + 1][m + 1];
        initLCS();
    }

    static void solve() {
        sb.append(LCSLen = LCS[n][m]).append("\n");
        sb.append(getLCSWord());
    }

    static void initLCS() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i][j - 1], LCS[i - 1][j]);
                }
            }
        }
    }

    static String getLCSWord() {
        char[] word = new char[LCSLen];
        int idx = LCSLen - 1;
        int i = n;
        int j = m;
        while (i >= 1 && j >= 1) {
            if (LCS[i][j] == LCS[i - 1][j]) {
                i--;
            } else if (LCS[i][j] == LCS[i][j - 1]) {
                j--;
            } else {
                word[idx--] = str1.charAt(i - 1);
                i--;
                j--;
            }
        }
        return String.valueOf(word);
    }
}
