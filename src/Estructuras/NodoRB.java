/*
 * NodoRB.java
 * Creado Octubre 23, 2012, 10:15am
 */
package Estructuras;

import Estructuras.ArbolAVL;
import Estructuras.DString;

/**
 * @author wilson
 * 
 * Clase para crear los nodos de arbol Rojo y negro
 */
public class NodoRB {
    
    public NodoRB right;                   
    public NodoRB left;
    public NodoRB padre;
    private DString data;
    public ArbolAVL arbol;
    public String _color = "ROJO";

    
    public NodoRB(Object pDato,ArbolAVL Tree){
        this.left = null;
        this.right = null;
        data = new DString((String)pDato);
        this.arbol=Tree;
        this.padre = null;
    }

    public NodoRB(Object pDato,ArbolAVL tree, NodoRB der, NodoRB izq){
        this.left = izq;
        data = new DString((String)pDato);
        this.right = der;
        this.arbol = tree;
        this.padre = null;
    }
    /*
     * METODOS SETERS
     */
    public void setRight(NodoRB der){
        this.right  = der;
    }

    public void setLeft(NodoRB izq){
        this.left = izq;
    }
    public void setData(String _dato){
        data = new DString((String)_dato);
    }
    public void setPadre(NodoRB father){
        padre = father; 
    }
  

    /*
     * METODOS GETERS
     */
    public NodoRB getPadre(){
        return this.padre;
    }
    public NodoRB getRight(){
        return this.right;
    }

    public NodoRB getLeft(){
        return this.left;
    }

    public DString getDato(){
        return data;
    }
    
}
