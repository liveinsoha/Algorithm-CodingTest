package page539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12015 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] lis;
    static int[] num;
    static int n;
    static int lastIdx;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        lis = new int[n];
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        setLIS();
        sb.append(lastIdx + 1);
    }

    static void setLIS() {
        lis[0] = num[0];
        lastIdx = 0;

        for (int i = 1; i < n; i++) {
            if (lis[lastIdx] < num[i]) { //추가
                lastIdx++;
                lis[lastIdx] = num[i];
            } else if (lis[lastIdx] == num[i]) {
                continue;
            } else { //대치
                int lo = 0;
                int hi = lastIdx;
                int mid;
                while (lo < hi) {
                    mid = (lo + hi) / 2;
                    if (num[i] > lis[mid]) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                lis[lo] = num[i];
            }

        }
    }
}

