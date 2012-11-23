package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Army {

    class Orc{
        String name;
        int points;
        int[] array;

        Orc(String line){
            for(int i = 0; i < line.length(); ++i){
                if(line.charAt(i) >='0' && line.charAt(i)<='9'){
                    name = line.substring(0, i).trim();
                    StringTokenizer tokenizer = new StringTokenizer(line.substring(i));
                    points = Integer.parseInt(tokenizer.nextToken());
                    array = new int[Integer.parseInt(tokenizer.nextToken())];
                    for(int t = 0; t < array.length; ++t){
                        array[t] = Integer.parseInt(tokenizer.nextToken()) - 1;
                    }
                    return;
                }
            }
        }
    }
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] sizes = in.nextIntArray(m);
        boolean[] full = new boolean[m];

        ArrayList<String>[] answer = new ArrayList[m];
        for(int i = 0; i < m ;++i){
            answer[i] = new ArrayList<String>();
        }

        Orc[] orcs = new Orc[n];

        for(int i = 0; i < n; ++i){
            orcs[i] = new Orc(in.nextLine());
        }

        Arrays.sort(orcs, new Comparator<Orc>() {
            @Override
            public int compare(Orc o1, Orc o2) {
                return -(o1.points - o2.points);
            }
        });

        for(int i = 0; i < n ;++i){
            for(int j = 0; j < orcs[i].array.length; ++j){
                if(!full[orcs[i].array[j]]){
                    answer[orcs[i].array[j]].add(orcs[i].name);
                    full[orcs[i].array[j]] = answer[orcs[i].array[j]].size() == sizes[orcs[i].array[j]];
                    break;
                }
            }
        }

        for(int i = 0; i < m; ++i){
            Collections.sort(answer[i], new Comparator<String>() {
                @Override

                public int compare(String o1, String o2) {
                    for(int i = 0; i < o1.length() || i < o2.length(); ++i){
                        if(i>=o1.length())
                            return -1;
                        if(i>=o2.length()){
                            return 1;
                        }
                        if(less(o1.charAt(i), o2.charAt(i))){
                            return -1;
                        }

                        if(less(o2.charAt(i), o1.charAt(i)))
                            return 1;

                    }
                    return 0;
                }

                private boolean less(char c, char c1) {
                    int d = Character.toLowerCase(c);
                    int d1 = Character.toLowerCase(c1);
                    if(d < d1){
                        return true;
                    }
                    if(d == d1 && c < c1)
                        return true;
                    return false;
                }
            });

            for(String name: answer[i]){
                out.println(name);
            }
            out.println();
        }


    }
}
