package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Medical {
    class Patient implements Comparable<Patient> {
        ArrayList<Integer> rooms = new ArrayList<Integer>();

        int startTime;
        int currentRoom = 0;
        int id;

        Patient(int id, int startTime, ArrayList<Integer> rooms) {
            this.id = id;
            this.startTime = startTime;
            this.rooms = rooms;
        }

        int getNextRoom() {
            if (currentRoom >= rooms.size()) {
                return -1;
            }
            return rooms.get(currentRoom++);
        }

        int getStartTime() {
            return startTime;
        }

        @Override
        public int compareTo(Patient o) {
            return Integer.compare(id, o.id);
        }
    }

    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        TreeMap<Integer, ArrayList<Patient>> patients = new TreeMap<Integer, ArrayList<Patient>>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            int k = in.nextInt();
            ArrayList<Integer> a = new ArrayList<Integer>();
            for (int j = 0; j < k; j++) {
                a.add(in.nextInt());
            }
            Patient patient = new Patient(i, t, a);
            if (!patients.containsKey(t)) {
                patients.put(t, new ArrayList<Patient>());
            }
            patients.get(t).add(patient);
        }

        HashMap<Integer, Queue<Patient>> queues = new HashMap<Integer, Queue<Patient>>();

        int last = -1;

        HashMap<Integer, TreeSet<Patient>> newComers = new HashMap<Integer, TreeSet<Patient>>();

        for (int t = 0; t < 2000000; t++) {
            if (patients.containsKey(t)) {
                for (Patient patient : patients.get(t)) {
                    int room = patient.getNextRoom();
                    if (!newComers.containsKey(room)) {
                        newComers.put(room, new TreeSet<Patient>());
                    }
                    newComers.get(room).add(patient);
                }
            }

            for (Map.Entry<Integer, TreeSet<Patient>> e : newComers.entrySet()) {
                if (!queues.containsKey(e.getKey())) {
                    queues.put(e.getKey(), new LinkedList<Patient>());
                }
                queues.get(e.getKey()).addAll(e.getValue());
            }

            newComers.clear();

            ArrayList<Integer> keys = new ArrayList<Integer>();
            keys.addAll(queues.keySet());
            for (int key : keys) {
                Patient patient = queues.get(key).poll();
                int room = patient.getNextRoom();

                if (room == -1) {
                    last = t + 1;
                } else {
                    if (!newComers.containsKey(room)) {
                        newComers.put(room, new TreeSet<Patient>());
                    }
                    newComers.get(room).add(patient);
                }
                if (queues.get(key).isEmpty()) {
                    queues.remove(key);
                }
            }
        }

        out.println(last);
    }
}
