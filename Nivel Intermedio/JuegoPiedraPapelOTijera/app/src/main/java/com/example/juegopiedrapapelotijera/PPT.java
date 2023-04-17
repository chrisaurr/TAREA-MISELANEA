package com.example.juegopiedrapapelotijera;

public class PPT {

    private int[][] tablero;

    public PPT() {
        this.iniciarTablero();
    }

    private void iniciarTablero(){
        int[][] tabla = {{0,-1,1}, {1,0,-1}, {-1,1,0}};

        this.tablero = tabla;
    }

    public int ganador(Mano j1, Mano cpu){
        return this.tablero[j1.getIndex()][cpu.getIndex()];
    }
}
