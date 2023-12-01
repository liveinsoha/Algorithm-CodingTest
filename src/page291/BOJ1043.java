package page291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] unf;
    static int n;
    static int m;
    static int king;

    static List<int[]> parties = new ArrayList<>();


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
        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int numOfTruthMan = Integer.parseInt(st.nextToken());
        if (numOfTruthMan != 0) {
            king = Integer.parseInt(st.nextToken());
            for (int i = 1; i < numOfTruthMan; i++) {
                union(king, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfMan = Integer.parseInt(st.nextToken());
            int[] party = new int[numOfMan];
            int firstMan = Integer.parseInt(st.nextToken());
            party[0] = firstMan;
            for (int j = 1; j < numOfMan; j++) {
                union(firstMan, party[j] = Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }
    }

    static void solve() {
        long ans = parties.stream().filter(party -> find(party[0]) != find(king)).count();
        sb.append(ans);
    }

    static int find(int x) {
        if (unf[x] == x) {
            return x;
        }
        return unf[x] = find(unf[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fb] = fa;
        }
    }
}
