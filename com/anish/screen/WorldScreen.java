package com.anish.screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Random;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.MatrixSorter;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private final int shuffleStrength=20;
    private World world;
    private Calabash[][] broMatrix;
    String[] sortSteps;
    public static final int MATRIX_SIZE=8;

    public WorldScreen() {
        world = new World();

        broMatrix=new Calabash[MATRIX_SIZE][MATRIX_SIZE];

        Random random=new Random();

        for(int i=0;i<broMatrix.length;++i){
            for(int j=0;j<broMatrix[i].length;++j){
                broMatrix[i][j]=new Calabash(new Color(i*256/MATRIX_SIZE,j*256/MATRIX_SIZE,(i*MATRIX_SIZE+j)*(256/(MATRIX_SIZE*MATRIX_SIZE))),64-(i*MATRIX_SIZE+j),world);
            }
        }
        //put in world
        for(int i=0;i<broMatrix.length;++i){
            for(int j=0;j<broMatrix[i].length;++j){
                world.put(broMatrix[i][j],(i+1)*2,(j+1)*2);
            }
        }
        //shuffle


        MatrixSorter<Calabash> b =new MatrixSorter<>();
        b.load(broMatrix);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(broMatrix, Integer.parseInt(couple[0])).swap(getBroByRank(broMatrix, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }
    private Calabash getBroByRank(Calabash[][] broMatrix,int rank) {
        for (var line : broMatrix) {
            for (Calabash c : line) {
                if (c.getRank() == rank) {
                    return c;
                }
            }
        }
        return null;
    }
    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(broMatrix, sortSteps[i]);
            i++;
        }

        return this;
    }

}
