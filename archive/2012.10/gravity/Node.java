package gravity;

import java.util.*;

public class Node {
    Node northWest, northEast, southWest, southEast;

    Particle innerParticle;
    int n;

    Node(ArrayList<Particle> particles, double x0, double y0, double x1, double y1) {
        n = particles.size();
        if (particles.size() == 0) {
            return;
        }
        if (particles.size() == 1) {
            innerParticle = particles.get(0);
            return;
        }

        double xMid = (x0 + x1) / 2;
        double yMid = (y0 + y1) / 2;

        ArrayList<Particle> northWestParticles = new ArrayList<Particle>();
        ArrayList<Particle> northEastParticles = new ArrayList<Particle>();
        ArrayList<Particle> southWestParticles = new ArrayList<Particle>();
        ArrayList<Particle> southEastParticles = new ArrayList<Particle>();

        innerParticle = Particle.merge(particles);

        for (Particle particle : particles) {
            if (particle.getX() <= xMid) {
                if (particle.getY() <= yMid) {
                    northWestParticles.add(particle);
                } else {
                    southWestParticles.add(particle);
                }
            } else {
                if (particle.getY() <= yMid) {
                    northEastParticles.add(particle);
                } else {
                    southEastParticles.add(particle);
                }
            }
        }

        northWest = new Node(northWestParticles, x0, y0, xMid, yMid);
        northEast = new Node(northEastParticles, xMid, y0, x1, yMid);
        southWest = new Node(southWestParticles, x0, yMid, xMid, y1);
        southEast = new Node(southEastParticles, xMid, yMid, x1, y1);
    }
}
