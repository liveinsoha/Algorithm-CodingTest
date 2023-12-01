package page511최대공통부분문자열LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9252 { // 다이나믹 인덱스 0부터 할 것 //틀림
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] LCS;
    static String str1;
    static String str2;
    static int n;
    static int m;
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
        initLCS();
    }

    static void solve() {
        if (n == 0 || m == 0) {
            sb.append(0);
            return;
        }
        sb.append(LCSLen = LCS[n - 1][m - 1]).append("\n");
        if (LCSLen > 0) {
            sb.append(getLCSWord());
        }
    }

    static void initLCS() {
        if (n == 0 || m == 0) {
            return;
        }
        LCS = new int[n][m];
        if (str1.charAt(0) == str2.charAt(0)) {
            LCS[0][0] = 1;
        } else {
            LCS[0][0] = 0;
        }

        for (int i = 1; i <= n - 1; i++) {
            if (LCS[i - 1][0] == 1) {
                LCS[i][0] = 1;
            } else if (LCS[i - 1][0] != 1 && str2.charAt(0) == str1.charAt(i)) {
                LCS[i][0] = 1;
            } else {
                LCS[i][0] = 0;
            }
        }

        for (int j = 1; j <= m - 1; j++) {
            if (LCS[0][j - 1] == 1) {
                LCS[0][j] = 1;
            } else if (LCS[0][j - 1] != 1 && str1.charAt(0) == str2.charAt(j)) {
                LCS[0][j] = 1;
            } else {
                LCS[0][j] = 0;
            }
        }

        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= m - 1; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

    }

    static String getLCSWord() {
        char[] LCSWord = new char[LCSLen];
        int idx = LCSLen - 1;
        int i = n - 1;
        int j = m - 1;
        while (i >= 1 && j >= 1) {
            if (LCS[i][j - 1] == LCS[i][j]) {
                j--;
            } else if (LCS[i - 1][j] == LCS[i][j]) {
                i--;
            } else {
                LCSWord[idx--] = str1.charAt(i);
                i--;
                j--;
            }
        }
        if (j == 0) {
            LCSWord[idx] = str2.charAt(0);
        } else if (i == 0) {
            LCSWord[idx] = str1.charAt(0);
        }

        return String.valueOf(LCSWord);
    }
}
