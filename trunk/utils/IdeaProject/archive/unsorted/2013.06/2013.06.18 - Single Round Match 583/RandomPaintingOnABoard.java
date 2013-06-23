package coding;

public class RandomPaintingOnABoard {
    public double expectedSteps(String[] prob) {
        double[][] p = new double[prob.length][prob[0].length()];
        double sum = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] = prob[i].charAt(j) - '0';
                sum += p[i][j];
            }
        }
        int z = 0;
        for (int i = 1; i <= 21; i++) {
            for (int j = 1; j <= 21; j++) {
                if (i * j <= 150) {
                    z = Math.max(z, i + j);
                }
            }
        }
        System.out.println(z);
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] /= sum;
            }
        }
        double ans = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                double s1 = 0;
                for (int k = 0; k < p[i].length; k++) {
                    s1 += p[i][k];
                }
                double s2 = 0;
                for (int k = 0; k < p.length; k++) {
                    s2 += p[k][j];
                }
                double p1 = (s1 - p[i][j]) / s1;
                double p2 = (s2 - p[i][j]) / s2;
                double other = 1. - s1 - s2 + p[i][j];
                //if (1 - p1 * p2 > 0.999) continue;
//                double a = (1 - s1) * p[i][j] / s1 / s1;
//                double b = (1 - s2) * p[i][j] / s2 / s2;
//                double c = (1 + other * p[i][j] * p[i][j] / s1 / s2) / other;
                double a = exp(s1, p[i][j] / s1);
                double b = exp(s2, p[i][j] / s2);
                double c = exp(s1 + s2 - p[i][j], p[i][j] * p[i][j] / s1 / s2);
                ans += a + b - c;
                System.out.println(a + " " + b + " " + c);
                //ans += (other / (p1 * p2) / (1 - other) + 1) * p[i][j];
                //System.out.println(p1 + " " + p2 + " " + other);
            }
        }
        return ans;
    }

    double exp(double us, double other) {
        if (us > 1 - 1e-8) return 1.;
        return ((1 - us) * other + 1) / (1 - us);
    }
}
