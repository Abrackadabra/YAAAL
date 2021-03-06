package gravity;

import abrackadabra.math.Math;

import java.awt.*;
import java.util.Collection;

public class Particle implements Comparable<Particle> {
    private static double maxSpeed = 10;
    private double mass;
    private double x, y;
    private double velocityX, velocityY;

    public Particle(double mass, double x, double y, double velocityX, double velocityY) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public static Particle merge(Collection<Particle> particles) {
        double newMass = 0.0;
        double newX = 0.0;
        double newY = 0.0;
        double newVelocityX = 0.0;
        double newVelocityY = 0.0;
        for (Particle particle : particles) {
            newMass += particle.mass;
            newX += particle.x * particle.mass;
            newY += particle.y * particle.mass;
            newVelocityX += particle.velocityX * particle.mass;
            newVelocityY += particle.velocityY * particle.mass;
        }
        newX /= newMass;
        newY /= newMass;
        newVelocityX /= newMass;
        newVelocityY /= newMass;
        return new Particle(newMass, newX, newY, newVelocityX, newVelocityY);
    }

    @Override
    public int compareTo(Particle o) {
        if (x != o.x) return Double.compare(x, o.x);
        if (y != o.y) return Double.compare(y, o.y);
        if (mass != o.mass) return Double.compare(mass, o.mass);
        return 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw(Graphics graphics) {
        double r = getRadius();
        graphics.drawOval((int) java.lang.Math.round(x - r),
                (int) java.lang.Math.round(y - r),
                (int) java.lang.Math.round(r * 2),
                (int) java.lang.Math.round(r * 2));
    }

    public double getRadius() {
        return java.lang.Math.sqrt(mass / java.lang.Math.PI * 100);
    }

    public double distanceTo(Particle particle) {
        return Math.hypot(x, y, particle.getX(), particle.getY());
    }

    public double getMass() {
        return mass;
    }

    public double getVelocity() {
        return Math.hypot(0, 0, velocityX, velocityY);
    }

    public double getEnergy() {
        return mass * getVelocity() * getVelocity() / 2;
    }

    static double angle = -0.5;
    static double rSin = java.lang.Math.sin(angle);
    static double rCos = java.lang.Math.cos(angle);

    public void interact(Particle particle) {
        double distance = Math.hypot(x, y, particle.x, particle.y);
        if (distance < getRadius() + particle.getRadius()) return;
        double dx = (particle.x - x) / distance;
        double dy = (particle.y - y) / distance;
        double acc = particle.mass / distance / distance / mass * 1e1;
        acc -= particle.mass / distance / distance / distance / distance /  mass * 5e2;
        //acc -= 1 / hypot / hypot / hypot /  mass * 2e4;

        double nx = dx * rCos - dy * rSin;
        double ny = dy * rCos + dx * rSin;

        velocityX += nx * acc;
        velocityY += ny * acc;



        double totalSpeed = abrackadabra.math.Math.hypot(0, 0, velocityX, velocityY);
        if (totalSpeed > maxSpeed) {
            double c = totalSpeed / maxSpeed;
            velocityX /= c;
            velocityY /= c;
        }
    }

    public void act(double width, double height) {
        velocityX *= 0.99;
        velocityY *= 0.99;
        mass = java.lang.Math.max(1e-2, mass * 0.999);
        x += velocityX;
        y += velocityY;
        x = java.lang.Math.min(java.lang.Math.max(0, x), width);
        y = java.lang.Math.min(java.lang.Math.max(0, y), height);
    }
}
