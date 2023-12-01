package page427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11505 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[] tree;
    static int n;
    static int m;
    static int p;
    static int k;
    private static final long that = 1000000007L;

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
        p = Integer.parseInt(st.nextToken());
        k = getK(n);
        tree = new long[getSize(n)];
        for (int i = 1; i <= n; i++) {
            tree[treeIdx(i)] = Integer.parseInt(br.readLine());
        }
        for (int i = (int) Math.pow(2, k) - 1; i >= 1; i--) {
            tree[i] = (tree[2 * i] * tree[2 * i + 1]) % that;
        }
    }

    static void solve() throws IOException {
        for (int i = 0; i < m + p; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                update(b, c);
            } else {
                sb.append(getMul(b, (int) c)).append("\n");
            }
        }
    }

    static void update(int idx, long val) {
        int treeIdx = treeIdx(idx);
        tree[treeIdx] = val;
        while (treeIdx > 1) {
            treeIdx = treeIdx / 2;
            tree[treeIdx] = (tree[treeIdx * 2] * tree[treeIdx * 2 + 1] )% that;
        }
    }

    static long getMul(int i, int j) {
        int startIdx = treeIdx(i);
        int endIdx = treeIdx(j);
        long mul = 1;
        while (startIdx <= endIdx) {
            if (startIdx % 2 == 0) {
                startIdx = startIdx / 2;
            } else {
                mul = mul * tree[startIdx] % that;
                startIdx = startIdx / 2 + 1;
            }
            if (endIdx % 2 == 1) {
                endIdx = endIdx / 2;
            } else {
                mul = mul * tree[endIdx] % that;
                endIdx = endIdx / 2 - 1;
            }
        }
        return mul;
    }

    static int treeIdx(int i) {
        return i + (int) Math.pow(2, k) - 1;
    }

    static int getSize(int n) {
        return (int) Math.pow(2, getK(n) + 1);
    }

    static int getK(int n) {
        if (isPowerOf2(n)) {
            return getExponentOf2(n);
        }
        return getExponentOf2(n) + 1;
    }

    static boolean isPowerOf2(int n) {
        if (n == 1) {
            return true;
        }
        if (n % 2 == 0) {
            return isPowerOf2(n / 2);
        }
        return false;
    }


    static int getExponentOf2(int n) {
        int exponent = 0;
        while (n >= 2) {
            n = n / 2;
            exponent++;
        }
        return exponent;
    }
}
