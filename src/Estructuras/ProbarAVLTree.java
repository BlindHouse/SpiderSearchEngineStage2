package Estructuras;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Estructuras.ArbolAVL;

/**
 *
 * @author wilson
 */
public class ProbarAVLTree {
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolAVL arbolAVL = new ArbolAVL();

      
        
        int elemento1 = 45;
        int elemento2 = 45;
        int elemento3 = 32;
        int elemento4 = 99;
        int elemento5 = 45;
        int elemento6 = 28;
        int elemento7 = 15;
        int elemento8 = 12;
        int elemento9 = 10;
        
        arbolAVL.insert(elemento1);
        arbolAVL.insert(elemento2);
        arbolAVL.insert(elemento3);
        arbolAVL.insert(elemento4);
        arbolAVL.insert(elemento5);
        arbolAVL.insert(elemento6);
        arbolAVL.insert(elemento7);
        arbolAVL.insert(elemento8);
        arbolAVL.insert(elemento9);
        //arbolAVL.insertar(elemento10);
        arbolAVL.imprimirPorNiveles();
        //System.out.println("\n");

        

    }
    
}
