package page385;

import java.io.*;
import java.util.*;

public class BOJ1414 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Edge> edgeList = new ArrayList<>();
    static int n;
    static long mst;
    static long sum;

    static int[] unf;

    public static void main(String[] args) throws Exception {
        solution();

    }

    static void solution() throws Exception {
        n = Integer.parseInt(br.readLine());
        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            char[] cArr = br.readLine().toCharArray();
            getEdge(i, cArr);
        }
        Collections.sort(edgeList);

        if (mst())
            System.out.println(sum - mst);
        else
            System.out.println(-1);

    }

    static void printAllEdge() {
        for (Edge e : edgeList) {
            System.out.println(e.s + " " + e.e + " " + e.w);
        }
    }

    static int find(int i) {
        if (unf[i] == i)
            return i;
        return unf[i] = find(unf[i]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb)
            unf[fa] = fb;
    }

    static boolean mst() {
        int count = 0;
        for (Edge e : edgeList) {
            if (find(e.s) != find(e.e)) {
                union(e.s, e.e);
                mst += e.w;
                count++;
            }
            if (count == n - 1)
                break;
        }

        if (count != n - 1)
            return false;
        return true;
    }

    static int charToInt(char c) {
        if (c >= 'a' && c <= 'z')
            return c - 'a' + 1;
        else
            return c - 'A' + 27;
    }

    static void getEdge(int i, char[] cArr) throws Exception {
        for (int j = 0; j < n; j++) {
            if (cArr[j] != '0') {
                edgeList.add(new Edge(i, j + 1, charToInt(cArr[j])));
                sum += charToInt(cArr[j]);
            }
        }
    }

    static class Edge implements Comparable<Object> {
        int s;
        int e;
        int w;

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int compareTo(Object o) {
            Edge e = (Edge) o;
            return this.w - e.w;
        }
    }
}
