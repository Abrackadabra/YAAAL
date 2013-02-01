package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class Euler54 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

    }

    static class Card implements Comparable<Card> {
        int value, suit;

        Card(String s) {
            switch (s.charAt(0)) {
                case 'A':
                    value = 14;
                    break;
                case 'K':
                    value = 13;
                    break;
                case 'Q':
                    value = 12;
                    break;
                case 'J':
                    value = 11;
                    break;
                default:
                    value = s.charAt(0) - '0';
            }
            switch (s.charAt(1)) {
                case 'S':
                    suit = 0;
                    break;
                case 'H':
                    suit = 1;
                    break;
                case 'C':
                    suit = 2;
                    break;
                case 'D':
                    suit = 3;
                    break;
                default:
                    throw new RuntimeException("Wrong suit");
            }
        }

        @Override
        public int compareTo(Card o) {
            if (value != o.value) return value - o.value;
            return suit - o.suit;
        }
    }

    static class Hand {
        Card[] cards = new Card[5];


    }
}
