package coding;

import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Locale;
import java.util.Random;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        TaskF.in = in;
        double finishPhi = in.nextDouble();
        double finishLambda = in.nextDouble();
        in.next();
        in.next();
//        interactor = new Interactor(finishLambda, finishPhi);
        currentTime = 0;
        for (int i = 0; i < 10; i++) {
            tryToGetTo(finishPhi, finishLambda);
        }
    }

    static final Random rng = new Random(59L);

    static void tryToGetTo(double finishPhi, double finishLambda) {
        int mainDif = go(rng.nextDouble() * 360, rng.nextDouble() * 40000);
        double step = 40000.0 / 24 * 1.5;
        while (true) {
            double makeTurn = rng.nextDouble() * 360;
            boolean ok = go(makeTurn, step) != mainDif;
            myAssert(go(180, step) == mainDif);
            if (ok) break;
        }
        double l = 0.0;
        double r = step;
        final int ITER = 100;
        for (int it = 0; it < ITER; it++) {
            double mid = (l + r) * .5;
            boolean ok = go(180, mid) == mainDif;
            myAssert(go(180, mid) == mainDif);
            if (ok) l = mid;
            else r = mid;
        }
        go(180, (l + r) * .5);
        l = 0.0;
        r = 180.0;
        for (int it = 0; it < ITER; it++) {
            double mid = (l + r) * .5;
            final double smallDistance = 0.01;
            boolean ok = go(mid, smallDistance) != mainDif;
            if (ok) l = mid;
            else r = mid;
            go(180, smallDistance);
            go(180 - mid, 0);
        }
        go((l + r) * .5, 0);
        l = 0.0;
        r = 20000;
        for (int it = 0; it < ITER; it++) {
            double mid = (l + r) * .5;
            boolean ok = circleDist(go(180, mid), mainDif) > 5;
            if (ok) r = mid; else l = mid;
            go(180, mid);
        }
        go(180, (l + r) * .5);
        go(7.5, 0);
        for (int z = 0; z < 2; z++) {
            for (int y = 0; y < 2; y++) {
                go(finishLambda, 0);
                double distance = (90 - finishPhi) * 40000 / 360;
                for (int i = 0; i < 24; i++) {
                    go(0, distance);
                    go(180, distance);
                    go(360.0 / 24, 0);
                }
                go(-finishLambda, 0);
                finishLambda = -finishLambda;
            }
            go(0, 20000);
        }
    }

    static void myAssert(boolean cond) {
//        if (!cond) throw new AssertionError();
    }

    static int circleDist(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 24 - d);
    }

    static int go(double turn, double length) {
        currentTime += length / 3.0 + turn / 1440.0;
        currentTime %= 1440;
        System.out.println(String.format(Locale.US, "%.10f %.10f", turn, length));
        System.out.flush();
        String day = in.next();
        int time = in.readTimeHM(':');
        if (day.equals("---")) {
            System.exit(0);
        }
        return (int) Math.round((currentTime - time + 1440) % 1440 / 60.0) % 24;
//        return interactor.move(turn, length);
    }

    static Interactor interactor;

    static double currentTime;
    static FastScanner in;

    static class Interactor {
        Point3DDouble currentPosition;
        Point3DDouble direction;
        double finishLambda;
        double finishPhi;

        double currentTime = 0.0;

        public Interactor(double finishLambda, double finishPhi) {
            this.finishLambda = finishLambda;
            this.finishPhi = finishPhi;
            currentPosition = new Point3DDouble(rng.nextDouble(), rng.nextDouble(), rng.nextDouble());
            currentPosition = currentPosition.multiply(1. / currentPosition.length());
            direction = new Point3DDouble(rng.nextDouble(), rng.nextDouble(), rng.nextDouble());
            direction = currentPosition.vmul(direction);
            direction = direction.multiply(1. / direction.length());
        }

        double getLambda() {
            return Math.atan2(currentPosition.y, currentPosition.x);
        }

        double getPhi() {
            return Math.PI * .5 - Math.atan2(Math.sqrt(currentPosition.x * currentPosition.x + currentPosition.y * currentPosition.y),
                                currentPosition.z);
        }

        void changePosition(double dist) {
            double realAngle = (dist / 40000) * 2 * Math.PI;
            Point3DDouble oldPos = currentPosition;
            currentPosition = currentPosition.multiply(Math.cos(realAngle)).add(direction.multiply(Math.sin(realAngle)));
            direction = oldPos.multiply(-Math.sin(realAngle)).add(direction.multiply(Math.cos(realAngle)));
            myAssert(Math.abs(currentPosition.length() - 1) < 1e-6);
        }

        void makeTurn(double angle) {
            angle = angle / 180 * Math.PI;
            Point3DDouble vmul = currentPosition.vmul(direction);
            direction = direction.multiply(Math.cos(angle)).add(vmul.multiply(Math.sin(angle)));
        }

        int cc = 0;

        int move(double turn, double dist) {
            System.err.println(++cc);
            currentTime += turn / 1440. + dist / 3.0;
            makeTurn(turn);
            changePosition(dist);
            double lambda = getLambda() * 180 / Math.PI;
            double phi = getPhi() * 180 / Math.PI;
            if (Math.abs(lambda - finishLambda) + Math.abs(phi - finishPhi) < 1e-5) {
                throw new AssertionError("Solution found");
            }
            System.out.println(currentPosition + " " + direction);
            lambda += 360;
            lambda %= 360;
            lambda += 7.5;
            lambda /= 15;
            int q = (int) (Math.round(Math.floor(lambda)) % 24);
//            currentTime
            return q;
        }
    }
}
