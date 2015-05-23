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
public class Nodo {
        public Nodo Left;            // hijo izquierdo
        public Nodo Right;              // hijo derecho
        public Nodo Father;
        public Object url;
        public  int prioridad;

    // Constructors
    public Nodo( Object url,int peso){
        this.Left = null;
        this.Right = null;
        this.Father = null;
        this.url=url;
        this.prioridad=peso;
 }
    
public Nodo getLeftSon() {
        return Left;
    }
public Nodo getRightSon() {
        return Right;
    }
public Nodo getFather() {
        return Father;
    }
public Object getUrl() {
        return url;
    }
public int getPeso() {
        return prioridad;
    }
public void setLeftSon(Nodo  LeftS) {
        this.Left = LeftS;
    }
public void setRightSon(Nodo  RightS) {
        this.Right = RightS ;
    }
public void setFather(Nodo  Father) {
        this.Father = Father;
    }
public void setUrl(String Url) {
        this.url =Url;
    }
public void setPeso(int Peso) {
        this.prioridad =Peso;
    }
}
