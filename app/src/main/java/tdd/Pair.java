package tdd;

import java.util.Objects;

public class Pair {
    private String from;
    private String to;

    public Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Pair Equals");
        Pair pair = (Pair) o;
        return from.equals(pair.from) && to.equals(pair.to);
    }

    @Override
    public int hashCode() {
        System.out.println("Pair HashCode");
        return 0;
    }
}
