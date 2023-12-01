package page220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1456 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long a;
    static long b;
    static boolean[] notPn;
    static List<Integer> pn = new ArrayList<>();
    static int count;

    public static void main(String[] args) throws IOException {
       solution();
        System.out.println(count);

    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        int pnRange = (int) Math.sqrt(b);
        notPn = new boolean[pnRange + 1];
        count = 0;
        checkNonPn();
        getPn();
    }

    static void solve() {
        for (int i : pn) {
            long mul = getMulAboveA(i);
            while (mul <= b) {
                count++;
                if (getLength(mul) + getLength(i) > 15) {
                    break;
                }
                mul *= i;
            }
        }
    }

    static long getMulAboveA(int pn) {
        long mul = (long) pn * pn;
        while (mul < a) {
            mul *= pn;
        }
        return mul;
    }

    static void getPn() {
        pn.add(2);
        for (int i = 3; i < notPn.length; i = i + 2) {
            if (!notPn[i]) {
                pn.add(i);
            }
        }
    }

    static void checkNonPn() {
        for (int i = 3; i <= (int) Math.sqrt(notPn.length); i = i + 2) {
            if (!notPn[i]) {
                int mul = i + i;
                while (mul < notPn.length) {
                    notPn[mul] = true;
                    mul += i;
                }
            }
        }
    }

    static int getLength(long s) {
        int count = 0;
        while (s > 0) {
            s = s / 10;
            count++;
        }
        return count;
    }
}
