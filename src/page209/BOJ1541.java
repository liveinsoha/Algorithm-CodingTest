package page209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1541 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<String> pm = new ArrayList<>();
    static int total;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), "+-", true);
        total = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            pm.add(st.nextToken());
            nums.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        int i = 0;
        while (i < pm.size() && pm.get(i).equals("+")) {
            total += nums.get(i);
            i++;
        }

        while (i < pm.size() && pm.get(i).equals("-")) {
            int sum = nums.get(i);
            i++;
            while (i < pm.size() && pm.get(i).equals("+")) {
                sum += nums.get(i);
                i++;
            }
            total -= sum;
        }
        sb.append(total);
    }
}
