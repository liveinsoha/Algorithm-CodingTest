package page375;

import java.io.*;
import java.util.*;

public class BOJ17472 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;

    static int[][] unf;
    static int[][] map;
    static int n;
    static int m;
    static List<Edge> edgeList = new ArrayList<>();
    static int land;


    public static void main(String[] args) throws Exception {
        solution();
    }

    static void solution() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        initMap();
        //printAll(map);
        initUnf();
       // printAll(unf);
        makeLand();
        //printAll(unf);


        findB();
        Collections.sort(edgeList);

        land = countLand();
        System.out.println(mst());
       System.out.println(countLand());


    }

    static void initMap() throws Exception {
        map = new int[n][m];

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void printAll(int[][] arr2d) {
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr2d[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void initUnf() {
        unf = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1)
                    unf[i][j] = 10 * i + j;
            }
        }
    }

    static int find(int i, int j) {
        if (unf[i][j] == 10 * i + j)
            return 10 * i + j;
        return unf[i][j] = find(unf[i][j] / 10, unf[i][j] % 10);
    }

    static void union(int a, int b) {
        int fa = find(a / 10, a % 10);
        int fb = find(b / 10, b % 10);

        if (fa != fb)
            unf[a / 10][a % 10] = fb;
    }

    static void merge(int i, int j) {
        if (i + 1 < n && map[i + 1][j] == 1) {
            union(10 * i + j, 10 * (i + 1) + j);
        }
        if (j + 1 < m && map[i][j + 1] == 1) {
            union(10 * i + j, 10 * i + j + 1);
        }
    }

    static void makeLand() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1)
                    merge(i, j);
            }
        }
    }

    static void findB() {
        for (int j = 0; j < m; j++) {
            getVertB(j);
        }
        for (int i = 0; i < n; i++) {
            getHorizonB(i);
        }
    }

    static void getVertB(int j) {
        for (int i = 0; i < n - 3; i++) {
            if (map[i + 2][j] == 0 && map[i + 1][j] == 0 && map[i][j] != 0) {
                for (int k = i + 2; k + 1 < n; k++) {
                    if (map[k][j] == 0 && map[k + 1][j] != 0 && find(k + 1, j) != find(i, j)) {
                        edgeList.add(new Edge(find(i, j), find((k + 1), j), k - i));
                        break;
                    }
                }
            }
        }
    }

    static void getHorizonB(int i) {
        for (int j = 0; j < m - 3; j++) {
            if (map[i][j + 2] == 0 && map[i][j + 1] == 0 && map[i][j] != 0) {
                for (int k = j + 2; k + 1 < m; k++) {
                    if (map[i][k] == 0 && map[i][k + 1] != 0 && find(i, k + 1) != find(i, j)) {
                        edgeList.add(new Edge(find(i, j), find(i, (k + 1)), k - j));
                        break;
                    }
                }
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

    static int mst() {
        Collections.sort(edgeList);

        int sum = 0;
        int count = 0;

        for (Edge e : edgeList) {
            if (find(e.s / 10, e.s % 10) != find(e.e / 10, e.e % 10)) {
                union(e.s, e.e);
                count++;
                sum += e.w;
            }
            if (count == land - 1)
                break;
        }
        if (count != land - 1)
            return -1;
        return sum;
    }

    static int countLand() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1)
                    set.add(find(i, j));
            }
        }
        return set.size();
    }
}
