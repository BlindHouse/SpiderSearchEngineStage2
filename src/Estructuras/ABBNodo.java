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
public class ABBNodo {
        public ABBNodo Left;            // hijo izquierdo
        public ABBNodo Right;              // hijo derecho
        public ABBNodo Father;
        public Object url;
        public Object Palabra;
    public  int prioridad;

    // Constructors
    public ABBNodo( Object url,Object palabra,int peso){
        this.Left = null;
        this.Right = null;
        this.Father = null;
        this.url=url;
        this.Palabra=palabra;
        this.prioridad=peso;
 }
    
/**********************
*** METODOS GETERS ***
**********************/
    
public ABBNodo getLeftSon() {
        return Left;
    }
public ABBNodo getRightSon() {
        return Right;
    }
public ABBNodo getFather() {
        return Father;
    }
public Object getUrl() {
        return url;
}
public Object getPalabra(){
        return Palabra;
}
public int getPeso() {
        return prioridad;
        
/**********************
 *** METODOS SETERS ***
 **********************/
    }
public void setLeftSon(ABBNodo  LeftS) {
        this.Left = LeftS;
    }
public void setRightSon(ABBNodo  RightS) {
        this.Right = RightS ;
    }
public void setFather(ABBNodo  Father) {
        this.Father = Father;
    }
public void setUrl(String Url) {
        this.url =Url;
    }
public void setPeso(int Peso) {
        this.prioridad =Peso;
    }
}

