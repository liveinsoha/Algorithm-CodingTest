package page354플로이드워셜모든정점최소거리kij;

import java.io.*;
import java.util.*;

public class BOJ11404 {

    static long[][] dist;
    static int n;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int m;
    final static long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        initDist();
        getValDist();
       // printAll();
        floydwarshall();
        printAll();
    }

    private static void initDist() {
        dist = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;
                dist[i][j] = INF;
            }
        }
    }

    private static void getValDist() throws Exception {
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            if (w < dist[a][b])
                dist[a][b] = w;
        }
    }

    private static void floydwarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    private static void printAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF)
                    sb.append(0).append(" ");
                else
                    sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }


}
