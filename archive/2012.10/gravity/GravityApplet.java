package gravity;

import abrackadabra.math.MathUtils;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GravityApplet extends Applet {
    HashSet<Particle> particles = new HashSet<Particle>();
    int n = 0;
    double fps = 20.0;
    double width = 1500.0, height = 700.0;
    BufferedImage bufferedImage = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);

    long calculationTime;
    double totalMass = 0.0;
    double totalEnergy = 0.0;

    public void init() {
        setSize((int) width, (int) height);
        for (int i = 0; i < n; i++) {
            particles.add(new Particle(MathUtils.randomBetween(0.1, 0.1),
                    MathUtils.randomBetween(0, width),
                    MathUtils.randomBetween(0, height),
                    0,
                    0));
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long time = System.nanoTime();
                    repaint();
                    time = ((long) (1 / fps - System.nanoTime() + time));
                    if (time > 0) {
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void paint(Graphics graphics) {
        calculationTime = -System.nanoTime();
        calculate();
        calculationTime += System.nanoTime();
        draw(bufferedImage.getGraphics());
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

    public void update(Graphics graphics) {
        paint(graphics);
    }

    void calculate() {
        totalMass = 0.0;
        if (Math.random() < 0.3) {
            particles.add(new Particle(MathUtils.randomBetween(0.1, 0.1),
                    MathUtils.randomBetween(0, width),
                    MathUtils.randomBetween(0, height),
                    0,
                    0));
        }
        ArrayList<ArrayList<Particle>> merges = new ArrayList<ArrayList<Particle>>();
        for (Particle a : particles) {
            ArrayList<Particle> toMerge = new ArrayList<Particle>();
            for (Particle b : particles) {
                if (a.distanceTo(b) <= (a.getRadius() + b.getRadius()) * 0.7) {
                    toMerge.add(b);
                }
            }
            if (toMerge.size() > 0) {
                merges.add(toMerge);
            }
        }
        for (ArrayList<Particle> toMerge : merges) {
            boolean ok = true;
            for (Particle particle : toMerge)
                if (!particles.contains(particle))
                    ok = false;
            if (!ok) continue;
            Particle newParticle = Particle.merge(toMerge);
            particles.removeAll(toMerge);
            particles.add(newParticle);
        }
        for (Particle a : particles) {
            totalMass += a.getMass();
            totalEnergy += a.getEnergy();
            for (Particle b : particles) {
                if (a.equals(b)) continue;
                a.interact(b);
            }
        }
        for (Particle particle : particles) {
            particle.act(width, height);
        }
    }

    void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, (int) width, (int) height);
        graphics.setColor(Color.BLACK);
        for (Particle particle : particles) {
            particle.draw(graphics);
        }

        ArrayList<String> debugOutput = new ArrayList<String>();

        debugOutput.add("particles: " + particles.size());
        debugOutput.add("calc time: " + calculationTime);
        Formatter formatter = new Formatter(Locale.UK);
        debugOutput.add("load: " + formatter.format("%.2f", calculationTime / (1.0 / fps * 1e9) * 100) + "%");
        debugOutput.add("mass: " + totalMass);
        debugOutput.add("energy: " + totalEnergy);

        for (int i = 0; i < debugOutput.size(); i++) {
            graphics.drawString(debugOutput.get(i), 0, 20 + i * 20);
        }
    }
}
