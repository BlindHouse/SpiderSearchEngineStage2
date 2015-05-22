/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

//import Estructuras2.Nodo;

import Estructuras2.Nodo;


/**
 *
 * @author wilson
 */
public class NodoRN 
        {
        public NodoRN Left;            // se asigana un hijo izquierdo
        public NodoRN Right;           // se asigna un hijo derecho
        public NodoRN Father;          //se asigana un padre
        public int prioridad;          //Int este sera la prioridad
        public String Url;             //la direccion del dirrectorio donde esta el archivo
        public Object AVLTree;         //Arbol que va a contener cada palabra y la cantidad de veces q aparece
        public String Color ="RED";    //Color del Nodo que vamos a insertar
        
        
        public NodoRN( String url,int peso)//Constructor *****Pendiente*****
        {   
        this.Left = null;
        this.Right = null;
        this.Father = null;
        this.Url=url;
        this.prioridad=peso;
        }
        
        /***********************
        **** METODOS GETERS ****
        ************************/
        
        public NodoRN getLeftSon() 
        {
         return Left;
        }
        
        public NodoRN getRightSon()
        {
         return Right;
        }
        
        public NodoRN getFather()
        {
        return Father;
        }
        
        public String getUrl()
        {
            return Url;
        }
        public int getPeso()
        {
          return prioridad;
        }
        
        public String getColor()
        {
            return Color;
        }
   
        /***********************
        **** METODOS SETERS ****
        ************************/
        
        public void setLeftSon(NodoRN  LeftS) 
        {
            this.Left = LeftS;
        }

        public void setRightSon(NodoRN  RightS) 
        {
            this.Right = RightS ;
        }
        
        public void setFather(NodoRN  Father) 
        {
            this.Father = Father;
        }
        
        public void setUrl(String Url) 
        {
             this.Url =Url;
        }
        
        public void setPeso(int Peso) 
        {
            this.prioridad =Peso;
        }
        
        
}
