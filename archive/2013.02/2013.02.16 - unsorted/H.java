package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

import java.util.*;

public class H {
    private static final double eps = 1e-6;

    static class Particle implements Comparable<Particle> {
        int id;
        double angle;

        Particle(int id, int x, int y) {
            this.id = id;

            double dist = Math.sqrt(x * x + y * y);

            if (y > 0) {
                angle = Math.acos(x / dist);
            } else {
                angle = -Math.acos(x / dist);
            }

            angle *= -1;
        }

        double distance(Particle particle) {
            return AMath.min(
                    Math.abs(angle - particle.angle),
                    Math.abs(angle - particle.angle + 2 * Math.PI),
                    Math.abs(angle - particle.angle - 2 * Math.PI)
            );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Particle particle = (Particle) o;

            if (Double.compare(particle.angle, angle) != 0) {
                return false;
            }
            if (id != particle.id) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = id;
            temp = angle != +0.0d ? Double.doubleToLongBits(angle) : 0L;
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public int compareTo(Particle o) {
            if (Double.compare(angle, o.angle) != 0) {
                return Double.compare(angle, o.angle);
            }
            return Integer.compare(id, o.id);
        }

        @Override
        public String toString() {
            return String.valueOf(id + 1);
        }
    }

    static class Fascio implements Comparable<Fascio> {
        Particle leftmost;
        Particle rightmost;

        double maxDistance;

        List<Particle> particles = new ArrayList<Particle>();

        int size() {
            return particles.size();
        }

        private Fascio() {
        }

        public Fascio(Particle particle) {
            leftmost = particle;
            rightmost = particle;
            particles.add(particle);

            maxDistance = leftmost.distance(rightmost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Fascio fascio = (Fascio) o;

            if (leftmost != null ? !leftmost.equals(fascio.leftmost) : fascio.leftmost != null) return false;
            if (rightmost != null ? !rightmost.equals(fascio.rightmost) : fascio.rightmost != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = leftmost != null ? leftmost.hashCode() : 0;
            result = 31 * result + (rightmost != null ? rightmost.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(Fascio o) {
            if (Integer.compare(size(), o.size()) != 0) {
                return Integer.compare(size(), o.size());
            }
            return leftmost.compareTo(o.leftmost);
        }

        boolean isGood(double alpha) {
            return maxDistance - eps <= alpha;
        }

        static Fascio merge(Fascio left, Fascio right) {
            Fascio merged = new Fascio();
            merged.leftmost = left.leftmost;
            merged.rightmost = right.rightmost;

            merged.maxDistance = Math.max(left.maxDistance, right.maxDistance);

            merged.maxDistance = Math.max(
                    merged.maxDistance,
                    Math.min(
                            left.leftmost.distance(right.rightmost),
                            left.rightmost.distance(right.leftmost)
                    )
            );

            List<Particle> particlesSmall = left.particles;
            List<Particle> particlesBig = right.particles;

            if (particlesSmall.size() > particlesBig.size()) {
                List<Particle> t = particlesSmall;
                particlesSmall = particlesBig;
                particlesBig = t;
            }

            particlesBig.addAll(particlesSmall);

            merged.particles = particlesBig;

            return merged;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(particles.size());

            stringBuilder.append('\n');

            for (Particle particle : particles) {
                stringBuilder.append(particle.id + 1);
                stringBuilder.append(' ');
            }

            return stringBuilder.toString();
        }

        static final Comparator<Fascio> distanceComparator = new Comparator<Fascio>() {
            @Override
            public int compare(Fascio o1, Fascio o2) {
                if (Double.compare(o1.maxDistance, o2.maxDistance) != 0) {
                    return Double.compare(o1.maxDistance, o2.maxDistance);
                }
                return o1.compareTo(o2);
            }
        };
    }

    static class ParticlePair implements Comparable<ParticlePair> {
        private Particle first;
        private Particle second;

        private double distance;

        protected ParticlePair(Particle first, Particle second) {
            this.first = first;
            this.second = second;

            distance = first.distance(second);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParticlePair that = (ParticlePair) o;

            if (first != null ? !first.equals(that.first) : that.first != null) return false;
            if (second != null ? !second.equals(that.second) : that.second != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(ParticlePair o) {
            if (Double.compare(distance, o.distance) != 0) {
                return Double.compare(distance, o.distance);
            }
            return first.compareTo(o.first);
        }

        @Override
        public String toString() {
            return first + " " + second;
        }
    }

    static protected boolean de(double a, double b) {
        return Math.abs(a - b) < eps;
    }

    NavigableSet<Fascio> goodFascios = new TreeSet<Fascio>();
    NavigableSet<Fascio> badFascios = new TreeSet<Fascio>(Fascio.distanceComparator);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle(i, in.nextInt(), in.nextInt());
        }
        Arrays.sort(particles);

        ParticlePair[] particlePairs = new ParticlePair[n];
        for (int i = 0; i < n; i++) {
            particlePairs[i] = new ParticlePair(particles[i], particles[(i + 1) % n]);
        }
        Arrays.sort(particlePairs);

        Map<Particle, Fascio> map = new HashMap<Particle, Fascio>();

        for (Particle particle : particles) {
            Fascio fascio = new Fascio(particle);
            goodFascios.add(fascio);
            map.put(particle, fascio);
        }

        List<List<ParticlePair>> timeLine = new ArrayList<List<ParticlePair>>();

        ParticlePair prevPair = null;
        List<ParticlePair> buffer = new ArrayList<ParticlePair>();

        for (ParticlePair particlePair : particlePairs) {
            if (prevPair == null || !de(particlePair.distance, prevPair.distance)) {
                buffer = new ArrayList<ParticlePair>();
                timeLine.add(buffer);
            }
            buffer.add(particlePair);

            prevPair = particlePair;
        }

        for (List<ParticlePair> pairs : timeLine) {
            double alpha = pairs.get(0).distance;

            trimAndCheck(alpha - eps * 3);
            trimAndCheck(alpha - eps * 2);
            trimAndCheck(alpha - eps * 1);

            for (ParticlePair pair : pairs) {
                Fascio left = map.get(pair.first);
                goodFascios.remove(left);
                badFascios.remove(left);
                Fascio right = map.get(pair.second);
                goodFascios.remove(right);
                badFascios.remove(right);

                Fascio merged = Fascio.merge(left, right);
                badFascios.add(merged);

                map.put(merged.leftmost, merged);
                map.put(merged.rightmost, merged);
            }

            trimAndCheck(alpha);
        }

        out.println(maxAlpha);
        System.out.println(Math.toDegrees(maxAlpha));

        List<Integer> list = new ArrayList<Integer>();
        boolean inside = false;
        for (int i = 0; i < 2 * n; i++) {
            if (particles[i % n].equals(maxFromFirst)) {
                inside = true;
            }
            if (inside) {
                list.add(particles[i % n].id + 1);
            }
            if (particles[i % n].equals(maxToFirst)) {
                if (inside) break;
            }
        }
        Collections.sort(list);

        out.println(list.size());
        out.printlnSpaced(list);

        list = new ArrayList<Integer>();
        inside = false;
        for (int i = 0; i < 2 * n; i++) {
            if (particles[i % n].equals(maxFromSecond)) {
                inside = true;
            }
            if (inside) {
                list.add(particles[i % n].id + 1);
            }
            if (particles[i % n].equals(maxToSecond)) {
                if (inside) break;
            }
        }
        Collections.sort(list);

        out.println(list.size());
        out.printlnSpaced(list);
    }

    void trimAndCheck(double alpha) {
        Collection<Fascio> toDeleteFromBad = new ArrayList<Fascio>();
        for (Fascio fascio : badFascios) {
            if (fascio.isGood(alpha)) {
                toDeleteFromBad.add(fascio);
                goodFascios.add(fascio);
            } else {
                break;
            }
        }
        badFascios.removeAll(toDeleteFromBad);

        if (goodFascios.size() >= 2) {
            Iterator<Fascio> iterator = goodFascios.descendingIterator();
            Fascio first = iterator.next();
            Fascio second = iterator.next();

            if (first.size() >= 3 && second.size() >= 3) {
                int res = first.size() + second.size();

                if (res > max) {
                    max = res;
                    maxAlpha = alpha;

                    maxFromFirst = first.leftmost;
                    maxToFirst = first.rightmost;
                    maxFromSecond = second.leftmost;
                    maxToSecond = second.rightmost;
                }
            }
        }
    }

    int max = -1;
    double maxAlpha = -1;

    Particle maxFromFirst = null;
    Particle maxToFirst = null;
    Particle maxFromSecond = null;
    Particle maxToSecond = null;
}
