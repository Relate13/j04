package com.anish.calabashbros;

import java.awt.Color;
import java.util.Random;

public class Calabash extends Creature implements Comparable<Calabash> {

    private int rank;
    public static Random random=new Random();
    public Calabash(Color color, int rank, World world) {
        super(color, (char) random.nextInt(128), world);
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return String.valueOf(this.rank);
    }

    @Override
    public int compareTo(Calabash o) {
        return Integer.valueOf(this.rank).compareTo(Integer.valueOf(o.rank));
    }

    public void swap(Calabash another) {
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
    }

}
