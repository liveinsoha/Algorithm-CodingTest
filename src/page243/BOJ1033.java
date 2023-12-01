package page243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1033 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<long[]> list = new ArrayList<>();
    static long[] ratio;
    static int n;

    public static void main(String[] args) throws IOException {

        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            long gcd = getGCD(p, q);
            p = p / gcd;
            q = q / gcd;

            list.add(new long[]{a, b, p, q});
        }
        ratio = new long[n];
    }

    static void solve() {
        if (ratio.length == 1) {
            sb.append(1);
            return;
        }
        fillRatio();
        long gcd = ratio[0];
        for (int i = 1; i < n; i++) {
            gcd = getGCD(ratio[i], gcd);
        }
        for (int i = 0; i < n; i++) {
            ratio[i] /= gcd;
        }
        for (int i = 0; i < n; i++) {
            sb.append(ratio[i]).append(" ");
        }
    }

    static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }

    static void fillRatio() {
        long[] first = list.remove(0);
        ratio[(int) first[0]] = first[2];
        ratio[(int) first[1]] = first[3];

        while (!list.isEmpty()) {

            long[] next = getNext();

            if (ratio[(int) next[0]] != 0) {
                long mul = ratio[(int) next[0]];
                for (int i = 0; i < n; i++) {
                    ratio[i] *= next[2];
                }
                ratio[(int) next[1]] = mul * next[3];
            } else {
                long mul = ratio[(int) next[1]];
                for (int i = 0; i < n; i++) {
                    ratio[i] *= next[3];
                }
                ratio[(int) next[0]] = mul * next[2];
            }
        }
    }

    static long[] getNext() {
        long[] can = null;
        for (int i = 0; i < list.size(); i++) {
            if (ratio[(int) list.get(i)[0]] != 0 || ratio[(int) list.get(i)[1]] != 0) {
                can = list.remove(i);
                break;
            }
        }
        return can;
    }
}
