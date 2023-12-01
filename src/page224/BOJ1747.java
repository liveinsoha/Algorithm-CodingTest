package page224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1747 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int pnRange;
    static int sqrt;
    static boolean[] notPn;
    static List<Integer> pn = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);

    }

    static void solution() throws IOException {
        init();
        getPn();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        pnRange = 10 * n;
        notPn = new boolean[pnRange + 1];
        sqrt = (int) Math.sqrt(pnRange);
    }

    static void solve() {
        for (int i : pn) {
            if (i >= n && isPal(i)) {
                sb.append(i);
                return;
            }
        }
    }

    static boolean isPal(int i) {
        String str = String.valueOf(i);
        int len = str.length();
        for (int k = 0; k < len / 2; k++) {
            if (str.charAt(+k) != str.charAt(len - 1 - k)) {
                return false;
            }
        }
        return true;
    }

    static void getPn() {
        for (int i = 3; i <= sqrt; i = i + 2) {
            int times = i + i;
            while (times <= pnRange) {
                notPn[times] = true;
                times += i;
            }
        }
        pn.add(2);
        for (int i = 3; i <= pnRange; i = i + 2) {
            if (!notPn[i] && i >= n) {
                pn.add(i);
            }
        }
    }
}
