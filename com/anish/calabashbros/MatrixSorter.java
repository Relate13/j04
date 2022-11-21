package com.anish.calabashbros;

public class MatrixSorter<T extends Comparable<T>> implements Sorter<T> {
    private T[][] a;
    private String plan = "";
    @Override
    public void load(T[] elements) {
        throw new UnsupportedOperationException();
    }
    public void load(T[][] elementMatrix){
        a=elementMatrix;
    }

    private void swap(int Ai, int Aj,int Bi,int Bj) {
        T temp;
        temp = a[Ai][Aj];
        a[Ai][Aj] = a[Bi][Bj];
        a[Bi][Bj] = temp;
        //System.out.println("New Plan:" + a[Ai][Aj] + "<->" + a[Bi][Bj]);
        plan += "" + a[Ai][Aj] + "<->" + a[Bi][Bj] + "\n";
    }
    @Override
    public void sort() {
        for(int lineNum=0;lineNum<a.length;++lineNum) {
            boolean sorted = false;
            while (!sorted) {
                sorted = true;
                for (int i = 0; i < a.length - 1; i++) {
                    if (a[lineNum][i].compareTo(a[lineNum][i + 1]) > 0) {
                        swap(lineNum,i, lineNum,i + 1);
                        sorted = false;
                    }
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}
