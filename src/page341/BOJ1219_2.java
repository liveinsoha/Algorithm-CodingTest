package page341;

import java.io.*;
import java.util.*;

public class BOJ1219_2 {

    static List<Edge> edgeList = new ArrayList<>();
    static int[] getMoney;
    static long[] maxMoney;
    static int n;
    static int arrival;
    final static long mINF = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        solution();
    }

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        arrival = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        getMoney = new int[n];
        maxMoney = new long[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            edgeList.add(new Edge(a, b, -w));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            getMoney[i] = Integer.parseInt(st.nextToken());
            maxMoney[i] = mINF;
        }
        for (Edge edge : edgeList) {
            edge.w = edge.w + getMoney[edge.e];
        }

        maxMoney[S] = getMoney[S];


        bellmanford();

        if(maxMoney[arrival] == Long.MAX_VALUE)
            System.out.println("Gee");
        else{
            if(maxMoney[arrival] == Long.MIN_VALUE)
                System.out.println("gg");
            else
                System.out.println(maxMoney[arrival]);
        }


    }

    private static void bellmanford() {

        for (int i = 1; i <= n + 50; i++) {

            for (Edge edge : edgeList) {
                if (maxMoney[edge.s] == mINF)
                    continue;

                if (maxMoney[edge.s] == Long.MAX_VALUE)
                    maxMoney[edge.e] = Long.MAX_VALUE;

                else if (maxMoney[edge.s] + edge.w > maxMoney[edge.e]) {
                    maxMoney[edge.e] = maxMoney[edge.s] + edge.w;

                    if (i >= n)
                        maxMoney[edge.e] = Long.MAX_VALUE;
                }
            }
        }

    }

    private static class Edge {
        int s;
        int e;
        long w;

        private Edge(int s, int e, long w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}