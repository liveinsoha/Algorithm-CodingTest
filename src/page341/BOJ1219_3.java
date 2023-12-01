package page341;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1219_3 { // 틀림.
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static List<Edge> edges = new ArrayList<>();
    static int v;
    static int e;
    static int startV;
    static int endV;
    static long[] getMoney;
    static long[] maxMoney;
    static long endMoney;


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
        v = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(st.nextToken());
        endV = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        getMoney = new long[v];
        maxMoney = new long[v];
        for (int i = 0; i < v; i++) {
            maxMoney[i] = Integer.MIN_VALUE;
        }


        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int fee = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, fee));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < v; i++) {
            getMoney[i] = Long.parseLong(st.nextToken());
        }
    }

    static void solve() {
        bellmanFord();
    }

    static void bellmanFord() {
        maxMoney[startV] = getMoney[startV];

        for (int i = 1; i <= v - 1; i++) {
            oneCycle();
        }
        endMoney = maxMoney[endV];
        if (endMoney == Integer.MIN_VALUE) {
            sb.append("gg");
            return;
        }
        for (int i = 0; i < 100; i++) {
            oneCycle();
        }
        if (endMoney != maxMoney[endV]) {
            sb.append("Gee");
            return;
        }
        sb.append(endMoney);
    }

    static void oneCycle() {
        for (Edge edge : edges) {
            if (maxMoney[edge.start] != Integer.MIN_VALUE
                    && maxMoney[edge.start] - edge.fee + getMoney[edge.end] > maxMoney[edge.end]) {
                maxMoney[edge.end] = maxMoney[edge.start] - edge.fee + getMoney[edge.end];
            }
        }
    }

    static class Edge {
        int start;
        int end;
        long fee;

        Edge(int start, int end, long fee) {
            this.start = start;
            this.end = end;
            this.fee = fee;
        }
    }
}

