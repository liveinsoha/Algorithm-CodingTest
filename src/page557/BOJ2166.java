package page557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2166 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Vector[] vectors;
    static int n;
    static double area;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.printf("%.1f", area);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        vectors = new Vector[n];
        st = new StringTokenizer(br.readLine());
        double firstX = Double.parseDouble(st.nextToken());
        double firstY = Double.parseDouble(st.nextToken());
        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            vectors[i] = new Vector(Double.parseDouble(st.nextToken()) - firstX, Double.parseDouble(st.nextToken()) - firstY);
        }
        area = 0;
    }

    static void solve() {
        for (int i = 1; i <= n - 2; i++) {
            area += getTriangleArea(vectors[i], vectors[i + 1]);
        }
        area = Math.abs(area);
    }

    static double getTriangleArea(Vector v1, Vector v2) {
        return (v1.a * v2.b - v1.b * v2.a) / 2;
    }

    static class Vector {
        double a;
        double b;

        Vector(double a, double b) {
            this.a = a;
            this.b = b;
        }
    }
}
