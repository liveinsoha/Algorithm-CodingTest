package page423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10868 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static long[] tree;
    static int k;

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

        tree = new long[getTreeSize(n)];
        k = getK(n);

        for (int i = (int) Math.pow(2, k); i < getTreeSize(n); i++) {
            tree[i] = Long.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            tree[treeIdx(i)] = Long.parseLong(br.readLine());
        }
        for (int i = (int) Math.pow(2, k) - 1; i >= 1; i--) {
            tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
        }
    }

    static void solve() throws IOException {
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getMin(a, b)).append("\n");
        }
    }

    static long getMin(int i, int j) {
        int startIdx = treeIdx(i);
        int endIdx = treeIdx(j);
        long min = Long.MAX_VALUE;
        while (startIdx <= endIdx) {
            if (startIdx % 2 == 0) {
                startIdx = startIdx / 2;
            } else {
                min = Math.min(tree[startIdx], min);
                startIdx = startIdx / 2 + 1;
            }
            if (endIdx % 2 == 1) {
                endIdx = endIdx / 2;
            } else {
                min = Math.min(tree[endIdx], min);
                endIdx = endIdx / 2 - 1;
            }
        }
        return min;
    }


    static int treeIdx(int i) {
        return i + (int) Math.pow(2, k) - 1;
    }

    static int getTreeSize(int n) {
        return (int) Math.pow(2, getK(n) + 1);
    }

    static int getK(int n) {
        if (isPowerOf2(n)) {
            return getExponentOf2(n);
        }
        return getExponentOf2(n) + 1;
    }

    static int getExponentOf2(int n) {
        int exponent = 0;
        while (n >= 2) {
            n = n / 2;
            exponent++;
        }
        return exponent;
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
}
