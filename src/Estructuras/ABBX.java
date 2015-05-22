/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


import Estructuras2.Nodo;


/**
 *
 * @author wilson
 */
public class ABBX {
    
    private Nodo Inicio = null;
    
    public void setInicio(Nodo Dato){
        this.Inicio=Dato;             
    }
    
    /**Funcion para insertar en el arbol de manera ordenada menores a la izquierda
     *mayores a la derecha, inserta como un arbol binario
     * @param nuevo
     */
    public void insertar(Nodo nuevo){
        if(Inicio == null)
        {
            Inicio = nuevo;
            Inicio.Father=null;
            System.out.println("Se inserto el nodo raiz "+nuevo.getPeso());
            Inicio.Left = null;
            Inicio.Right = null;
            Inicio.Father = null;
                    
        }else{
            Nodo r = Inicio;
            boolean ok =  false;
            while(ok==false){
                while(nuevo.getPeso()>=r.getPeso()){
                    if(r.Right==null){
                        nuevo.Right=null;
                        nuevo.Left=null;
                        nuevo.Father=r;
                        r.Right = nuevo;
                        System.out.println("Se inserto el nodo "+ nuevo.getPeso()+" a la derecha y su padre es: "+nuevo.Father.getPeso());
                        ok = true;
                        break;                  
                    }
                    r=r.Right;
                }
            while(nuevo.getPeso()< r.getPeso()){
                if(r.Left==null){
                    nuevo.Left=null;
                    nuevo.Right = null;
                    nuevo.Father=r;
                    r.Left =  nuevo;
                    System.out.println("Se inserto el nodo "+ nuevo.getPeso()+"  a la izquierda y su padre es:  "+nuevo.Father.getPeso());
                    ok = true;
                    break;
                }
                r=r.Left;
            }
            }
        }
    }
    
    private void imprimirEntre (Nodo reco)
      {
          if (reco != null)
          {
              imprimirEntre (reco.Right);
              System.out.print(reco.getPeso() + " ");
              imprimirEntre (reco.Left);
          }
      }

    /**Esta es una funcion para inprimir entreOrden modificada para q imprima de mayor a manor
     *
     */
    public void imprimirEntre ()
      {
          imprimirEntre (Inicio);
          System.out.println();//Es como un enter 
      }
       
      private Nodo getMayor_Aux(Nodo reco)
      {
          while (reco.Right!= null) {
              reco=reco.Right;
            }
          Borrar(reco);
          return reco;
          
          //System.out.println("Nodo mayor  "+reco.getPeso());
        }

    /**Con esta funcion se obtiene el nodo de mayor valor 
     *(de momento solo imprime porque ocupo que borre)
     * @return 
     */
    public Nodo getMayor(){
       return getMayor_Aux(Inicio); 
    }
    
      
      
    /**Esta funcion iEsta comprueba si un nodo esta adentro del arbol 
     *si esta retorna un True y si no retorna un Falce
     * @param Dato
     * @return
     */
    public boolean iEsta(Nodo Dato){
      try{
          return iEsta_Aux(Dato,Inicio);
      }
      catch(Exception ex){
          System.out.println("No existe ese elemento en este arbol");
          return false;
        }
      }
      
      private boolean iEsta_Aux(Nodo Dato,Nodo raiz){
          if(raiz==null){
              return false;
          }
          else{
              if(raiz.getPeso()==Dato.getPeso())
                  return true;
              else{
                  if(Dato.getPeso()<raiz.getPeso())
                      return iEsta_Aux(Dato,raiz.Left);
                  else
                      return iEsta_Aux(Dato,raiz.Right);
              }
          }
              
      }
      
    /**Esta es la funcion de borrar primero comprueba si el nodo que
     * se va a borrar esta adentro de la funcion
     * @param Dato
     */
    public void Borrar(Nodo Dato){
      
          Borrar_Aux(Inicio,Dato);
      }
      
      private void Borrar_Aux(Nodo raiz,Nodo Dato){
          
          if(iEsta(Dato)){
              if(Dato.Left==null && Dato.Right== null)
              {
                  if(Dato.getPeso()>=Dato.Father.getPeso()){
                      Nodo padre=Dato.getFather();
                      padre.setRightSon(null);
                  }else if(Dato.getPeso()<Dato.Father.getPeso()){
                      Nodo padre=Dato.getFather();
                      padre.setLeftSon(null);
                  }
              } 
              else if(Dato.Left!= null){
                  Nodo padre = Dato.getFather();
                  padre.setRightSon(Dato.getLeftSon());
                  Dato.Left.setFather(padre);
              }else if(Dato.Right==null && Dato==Inicio){
                  Dato.Left.setFather(null);
                  setInicio(Dato.Left);
                  
              
              }
          }else{
           System.out.println("Eseobjeto no se encuentra en este arbol");
      }
      
      }
      
      public Nodo getFather(Nodo Dato){
          return Dato.Father;
      }
      
      public void prueba(){
          System.out.println(getMayor().getPeso());
          //System.out.println(getMayor().getLeftSon()+"  "+getMayor().Father.getPeso());
      }
      
      
      
 
     
      
    
    public static void main (String [] ar){
       Nodo nuevo1 = new Nodo("Casa", 10);
       Nodo nuevo2 = new Nodo("Casa", 26);
       Nodo nuevo3 = new Nodo("Casa", 18);
       Nodo nuevo4 = new Nodo("Pato",51);
       Nodo nuevo5 = new Nodo("Pato",28);
       Nodo nuevo6 = new Nodo("Pato",111);
       
       
   ABBX abl= new ABBX();     
       abl.insertar(nuevo1);
       abl.insertar(nuevo2);
       abl.insertar(nuevo3);
       abl.insertar(nuevo4);
       abl.insertar(nuevo5);
       abl.insertar(nuevo6);
       abl.imprimirEntre();
       abl.prueba();
       abl.prueba();
       abl.prueba();
       abl.prueba();
       //abl.prueba();
       //abl.iEsta(nuevo1);
       //abl.Borrar(nuevo5);
      // abl.Borrar(nuevo6);
      // abl.Borrar(nuevo3);
      // abl.Borrar(nuevo4);
       //abl.Borrar(nuevo2);
       abl.imprimirEntre();
       //abl.prueba(nuevo6, nuevo3);
            
             //abl.imprimirPre ();
      }
        
}
    

