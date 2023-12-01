package page275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][][] visited;
    static int[] water = new int[3];
    static int[] capa = new int[3];
    static List<Integer> ans = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        solution();
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        capa[0] = Integer.parseInt(st.nextToken());
        capa[1] = Integer.parseInt(st.nextToken());
        capa[2] = Integer.parseInt(st.nextToken());
        water[2] = capa[2];
        visited = new boolean[capa[0] + 1][capa[1] + 1][capa[2] + 1];
    }

    static void solve() {
        dfs();
        Collections.sort(ans);
    }

    static void dfs() {
        if (visited[water[0]][water[1]][water[2]]) {
            return;
        }
        visited[water[0]][water[1]][water[2]] = true;
        if (water[0] == 0) {
            ans.add(water[2]);
        }
        int temp0 = water[0];
        int temp1 = water[1];
        int temp2 = water[2];
        for (int s = 0; s < 3; s++) {
            if (!hasWater(s)) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                if (i == s) {
                    continue;
                }
                if (canPour(i)) {
                    pour(s, i);
                    dfs();
                    reset(temp0, temp1, temp2);
                }
            }
        }
    }

    static boolean hasWater(int i) {
        return water[i] > 0;
    }

    static void reset(int temp0, int temp1, int temp2) {
        water[0] = temp0;
        water[1] = temp1;
        water[2] = temp2;
    }

    static boolean canPour(int i) {
        return getRest(i) > 0;
    }

    static int getRest(int i) {
        return capa[i] - water[i];
    }

    static void pour(int s, int i) {
        if (water[s] > getRest(i)) {
            water[s] -= getRest(i);
            water[i] = capa[i];
        } else {
            water[i] += water[s];
            water[s] = 0;
        }
    }
}
