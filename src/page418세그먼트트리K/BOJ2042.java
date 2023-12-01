package page418세그먼트트리K;

import java.io.*;
import java.util.*;

public class BOJ2042 { //별로.. 2번 봐라
    static long[] arr;
    static int n;
    static int m;
    static int k;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Tree tree;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        getOriginalArr();
        tree = new Tree(n + 1);
        tree.init(1, 1, n);

        work();
    }

    static void getOriginalArr() throws Exception {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
    }


    static void work() throws Exception {

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                tree.change(1, 1, n, b, getDiff(b, c));
            } else {
                sb.append(tree.getSum(1, 1, n, b, (int) c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static long getDiff(int originalIdx, long newVal) {
        return newVal - arr[originalIdx];
    }


    static class Tree {
        long[] treeArr;

        Tree(int length) {
            this.treeArr = new long[4 * length];
        }

        long init(int node, int s, int e) {
            if (s == e)
                return treeArr[node] = arr[s];

            return treeArr[node] = init(2 * node, s, (s + e) / 2)
                    + init(2 * node + 1, (s + e) / 2 + 1, e);
        }

        long getSum(int node, int s, int e, int lo, int hi) {

            if (e < lo || hi < s)
                return 0;

            if (lo <= s && e <= hi)
                return treeArr[node];

            return getSum(node * 2, s, (s + e) / 2, lo, hi)
                    + getSum(node * 2 + 1, (s + e) / 2 + 1, e, lo, hi);
        }

        public void change(int node, int s, int e, int idx, long diff) {

            if (e < idx || idx < s)
                return;

            treeArr[node] += diff;

            if (s != e) {
                change(2 * node, s, (s + e) / 2, idx, diff);
                change(2 * node + 1, (s + e) / 2 + 1, e, idx, diff);
            }
        }
    }
}
