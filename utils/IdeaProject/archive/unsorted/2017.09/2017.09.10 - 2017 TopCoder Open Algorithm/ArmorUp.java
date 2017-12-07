package coding;

public class ArmorUp {
    public double minimalDamage(int maxHP, int currentHP, int k) {
        double left = 0.0;
        double right = 1. * currentHP / (1 - (0.5 * (maxHP - currentHP) / maxHP));
        for (int it = 0; it < 100; it++) {
            double mid = (left + right) * .5;
            double cur = maxHP - currentHP;
            double alpha = -mid / (2. * maxHP);
//            double sumc = (Math.pow(alpha, k + 1) - alpha) / (alpha - 1);
//            double sum2 = 0;
//            for (int i = 0; i < k; i++) {
//                double e = 1.;
//                for (int j = i; j < k; j++) {
//                    sum2 += mid * e;
//                    e *= alpha;
//                }
//            }
            double have = alpha;
            double sumc = 0;
            for (int i = 0; i < k; i++) {
                sumc += cur * have;
                have = have * alpha + alpha;
            }
//            cur += cur * (sumc - k) / (alpha - 1) * alpha;
            cur += sumc;
            double sum2 = 0;
            cur += mid * k;
            for (int i = 0; i < k; i++) {
                have = alpha;
                for (int j = i + 1; j < k; j++) {
                    sum2 += mid * have;
                    have = have * alpha + alpha;
                }
            }
//            cur += mid * k;
//            double sum2 = sumc * cur + mid * (sumc - k) / (alpha - 1);
//            double sum2 = 0;
//            System.out.println(mid + " " + alpha + " " + cur + " " + sumc + " " + sum2 + " " + ((sumc - k) / (alpha - 1)));
            System.out.println(mid + " " + sumc + " " + sum2);
            cur += sum2;
            if (cur >= maxHP) right = mid; else left = mid;
        }
        double dmg = (left + right) * .5 + 0.5;
        double c = maxHP - currentHP;
        for (int i = 0; i < k; i++) {
            c += dmg - (dmg * c * .5 / maxHP);
        }
        System.out.println(c);
        return (left + right) * .5;
    }
}
