/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author wilson
 */
public class AVLNodo {
    public Comparable dato;      	 // el dato del nodo
    public String Palabra;
    public AVLNodo izquierdo;            // hijo izquierdo
    public AVLNodo derecho;              // hijo derecho
    public int height;                   // altura

    // Constructors
   /* public AVLNodo( Comparable dato ){
        this( dato, null, null );
    }*/

    public AVLNodo( Comparable dato,String Palabra, AVLNodo izq, AVLNodo der ){
        this.dato = dato;
        this.Palabra= Palabra;
        this.izquierdo = null;
        this.derecho = null;
        height   = 0;               // altura predeterminada
    }
    
    
   public AVLNodo getHijoIzquierdo(){
       return izquierdo;
   }
   
   public AVLNodo getHijoDerecho(){
       return  derecho;
   }
   
   public String getPalabra(){
       return Palabra;
   }
   
   public Comparable getDato(){
        return dato;
   }
    
    
    
}
