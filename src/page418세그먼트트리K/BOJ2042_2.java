package page418세그먼트트리K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static long m;
    static long p;
    static int treeSize;
    static long k;
    static long[] tree;

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
        treeSize = getSize(n);
        tree = new long[treeSize];
        for (int i = 1; i <= n; i++) {
            tree[treeIdx(i)] = Long.parseLong(br.readLine());
        }
        for (int i = (int) Math.pow(2, k) - 1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    static void solve() throws IOException {
        for (int i = 0; i < m + p; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                update(b, c);
            } else {
                sb.append(getSum(b, (int)c)).append("\n");
            }
        }
    }

    static void update(int idx, long value) {
        int treeIdx = treeIdx(idx);
        long diff = value - tree[treeIdx];
        tree[treeIdx] = value;
        while (treeIdx > 1) {
            treeIdx = treeIdx / 2;
            tree[treeIdx] += diff;
        }
    }

    static long getSum(int i, int j) {
        int startIdx = treeIdx(i);
        int endIdx = treeIdx(j);
        long sum = 0;
        while (endIdx >= startIdx) {
            if (startIdx % 2 == 0) {
                startIdx = startIdx / 2;
            } else {
                sum += tree[startIdx];
                startIdx = startIdx / 2 + 1;
            }
            if (endIdx % 2 == 1) {
                endIdx = endIdx / 2;
            } else {
                sum += tree[endIdx];
                endIdx = endIdx / 2 - 1;
            }
        }
        return sum;
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
        } else {
            return getExponentOf2(n) + 1;
        }
    }

    static boolean isPowerOf2(int i) {
        if (i == 1) {
            return true;
        }
        if (i % 2 == 0) {
            return isPowerOf2(i / 2);
        }
        return false;
    }

    static int getExponentOf2(int i) {
        int exponent = 0;
        while (i >= 2) {
            i = i / 2;
            exponent++;
        }
        return exponent;
    }
}
