package page227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1016 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long min;
    static long max;
    static int count;
    static boolean[] timesOfSquare;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve3();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        timesOfSquare = new boolean[(int) (max - min + 1)];
        count = 0;
    }


    static void checkTimesOfSquare() {
        for (long i = 2; i * i <= max; i++) {
            long pow = i * i;
            long base = min / pow;
            if (min % pow != 0) {
                base++;
            }
            for (long j = base; j * pow <= max; j++) {
                timesOfSquare[(int) (j * pow - min)] = true;
            }
        }
    }

    static void solve3() {
        checkTimesOfSquare();
        for (int i = 0; i <= max - min; i++) {
            if (!timesOfSquare[i]) {
                count++;
            }
        }
        sb.append(count);
    }
}
