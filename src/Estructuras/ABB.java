/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


import GUI.Results;
import static GUI.Results.linkedlinks;

/**
 *
 * @author wilson
 */
public class ABB {

    private ABBNodo Inicio = null;
    
    public void setInicio(ABBNodo Dato){
        this.Inicio=Dato;             
    }
    
    /**Funcion para insertar en el arbol de manera ordenada menores a la izquierda
     *mayores a la derecha, inserta como un arbol binario
     * @param nuevo
     */
    public void insertar(ABBNodo nuevo){
        if(Inicio == null)
        {
            Inicio = nuevo;
            Inicio.Father=null;
            System.out.println("Se inserto el nodo raiz "+nuevo.getPeso());
            Inicio.Left = null;
            Inicio.Right = null;
            Inicio.Father = null;
                    
        }else{
            ABBNodo r = Inicio;
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
    
    private void imprimirEntre (ABBNodo reco) {
          if (reco != null) {
              imprimirEntre (reco.Right);
              imprimirEntre(reco.Left);
              String uri = reco.getUrl().toString();
              if(uri.startsWith("http")){
                  linkedlinks = linkedlinks + "La Palabra "+reco.getPalabra()+" Sale "+reco.getPeso() +
                          " veces,Y se ubica en " + "<a href="+ uri + ">" + uri + "</a>" + "<br><br>";
              }
              else{
                  linkedlinks = linkedlinks + "La Palabra "+reco.getPalabra()+" Sale "+reco.getPeso() +
                          " veces,Y se ubica en " + "<a href=\"" + uri + "\">" + uri + "</a>" + "<br><br>";
              }
          }
      }

    /**Esta es una funcion para inprimir entreOrden modificada para q imprima de mayor a manor
     *
     */
    public void DeMayor_Menor () {
        linkedlinks = "";
        imprimirEntre (Inicio);
         System.out.println();//Es como un enter
      }
       
      private ABBNodo getMayor_Aux(ABBNodo reco)
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
    public ABBNodo getMayor(){
       return getMayor_Aux(Inicio); 
    }
    
      
      
    /**Esta funcion iEsta comprueba si un nodo esta adentro del arbol 
     *si esta retorna un True y si no retorna un Falce
     * @param Dato
     * @return
     */
    public boolean iEsta(ABBNodo Dato){
      try{
          return iEsta_Aux(Dato,Inicio);
      }
      catch(Exception ex){
          System.out.println("No existe ese elemento en este arbol");
          return false;
        }
      }
      
      private boolean iEsta_Aux(ABBNodo Dato,ABBNodo raiz){
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
    public void Borrar(ABBNodo Dato){
      
          Borrar_Aux(Dato);
      }

    public void WipeTree(){
            Inicio = null;
    }
      
      private void Borrar_Aux(ABBNodo Dato){
         if(iEsta(Dato)){
             if(Inicio == Dato) {
                 setInicio(Dato.Left);
                 Dato.getLeftSon().setFather(null);
                 System.out.println("Raiz borrada");
             }
             else if(Dato.Left==null && Dato.Right== null) {
                 if(Dato.getPeso()>=Dato.Father.getPeso()){
                     ABBNodo padre=Dato.getFather();
                     padre.setRightSon(null);
                 }
                 else if(Dato.getPeso()<Dato.Father.getPeso()){
                     ABBNodo padre=Dato.getFather();
                     padre.setLeftSon(null);
                 }
             }
             else if(Dato.Left!= null){
                 ABBNodo padre = Dato.getFather();
                 padre.setRightSon(Dato.getLeftSon());
                 Dato.Left.setFather(padre);
             }else if(Dato.Right==null && Dato==Inicio){
                 Dato.Left.setFather(null);
                 setInicio(Dato.Left);
             }
         }
         else{
             System.out.println("Eseobjeto no se encuentra en este arbol");
         }
      
    }
      
      public ABBNodo getFather(ABBNodo Dato){
          return Dato.Father;
      }
      
      public void prueba(){
          System.out.println(getMayor().getPeso());
          //System.out.println(getMayor().getLeftSon()+"  "+getMayor().Father.getPeso());
      }
      
      
      
 
     
      
    
    public static void main (String [] ar){
       ABBNodo nuevo1 = new ABBNodo("Casa","Casa", 10);
       ABBNodo nuevo2 = new ABBNodo("Casa","Casa", 26);
       ABBNodo nuevo3 = new ABBNodo("Casa","Casa", 18);
       ABBNodo nuevo4 = new ABBNodo("Pato","Casa",51);
       ABBNodo nuevo5 = new ABBNodo("Pato","Casa",28);
       ABBNodo nuevo6 = new ABBNodo("Pato","Casa",111);
       
       
   ABB abl= new ABB();     
       abl.insertar(nuevo1);
       abl.insertar(nuevo2);
       abl.insertar(nuevo3);
       abl.insertar(nuevo4);
       abl.insertar(nuevo5);
       abl.insertar(nuevo6);

       abl.DeMayor_Menor();


      }
        
}
    

