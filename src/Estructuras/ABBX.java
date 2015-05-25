/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;


import FileStuff.TikaReader;
import FileStuff.WordCounter;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static FileStuff.WordCounter.*;


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
          imprimirEntre(Inicio);
          System.out.println();//Es como un enter 
      }
       
      private Nodo getMayor_Aux(Nodo reco) {
          if (reco == null){
              System.out.println("NOTHING");
          }
          else if (reco.Right == null && reco.Father == null) {
              String uri = reco.getUrl().toString();
              int pes = reco.getPeso();
              Nodo reco1 = new Nodo(uri,pes);
              Borrar(reco);
              return reco1;
          }
          else{
              while (reco.Right != null) {
                  reco = reco.Right;
              }
          }
          Borrar(reco);
          return reco;
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
          if(raiz == Dato){
              return true;
          }
          else if(raiz==null){
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
    public void Borrar(Nodo Dato) {

        Borrar_Aux(Dato);
    }
      
    private void Borrar_Aux(Nodo Dato){
        if(iEsta(Dato)){
            if(Inicio == Dato && Dato.Left == null){
                Inicio = null;
            }
            else if(Inicio == Dato && Dato.Left != null) {
                setInicio(Dato.Left);
                Dato.getLeftSon().setFather(null);
                System.out.println("Raiz borrada");
            }
            else if(Dato.Left==null && Dato.Right== null) {
                if(Dato.getPeso()>=Dato.Father.getPeso()){
                    Nodo padre=Dato.getFather();
                    padre.setRightSon(null);
                }
                else if(Dato.getPeso()<Dato.Father.getPeso()){
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
        }
         else{
             System.out.println("Eseobjeto no se encuentra en este arbol");
         }
      
    }
      
    public Nodo getFather(Nodo Dato){
          return Dato.Father;
      }


    /**
     * @Author JackTheBones
     *
     * Metodo pop para extraer los elementos
     * (nodos del arbol de uris) y borrarlos.
     *
     * @return Nodo en el arbol de uris
     */
    public Nodo PopNodo(){
        Nodo ElementoARetornar = null;
        try{
            ElementoARetornar = getMayor();
            System.out.println("NODO  " + ElementoARetornar.getUrl());
            return ElementoARetornar;
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        System.out.println("NODO   " + ElementoARetornar.getUrl());
        return ElementoARetornar;
    }

    private Nodo RetornaNodoAux (Nodo reco) {
        Nodo a = reco;
        if (reco.Right != null && reco.Left != null){
            RetornaNodoAux(reco.Right);
            RetornaNodoAux(reco.Left);
        }
        else if (reco.Right == null && reco.Left == null){
            reco = null;
        }
        return a;
    }

    public Nodo RetornaNodo(){
        return RetornaNodoAux(Inicio);
    }


    public static RedAndBlackTree FinalTree = new RedAndBlackTree();

    public static void ReadParseLoop(ABBX arbol){
        System.out.println("----------------------READ PARSE INSERTAR NODO de arbol mas uri al BN");
        while (arbol.Inicio != null){
            Nodo TempNodo = arbol.PopNodo();
            String uri = TempNodo.getUrl().toString();
            if (uri.startsWith("http")){
                try {
                    URL archivoOnline = new URL(uri);
                    InputStream x = archivoOnline.openStream();


                    ArbolAVL AvlPalabras = WordProcess(TikaReader.ParsedOnlineText(x));
                    NodoRB NodoFinal = new NodoRB(uri, AvlPalabras);
                    System.out.println("SE INSERTO : " + uri + "ARBOL AVL :  " + NodoFinal.getDato().getDato());
                    FinalTree.insert(NodoFinal);
                    //System.out.println("PREORDEN ACTUAL BN");
                    //FinalTree.verPreOrder();


                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                } catch (SAXException e) {
                    e.printStackTrace();
                    continue;
                } catch (TikaException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            else if(uri.startsWith("/")){
                try {


                    ArbolAVL AvlPalabras = WordProcess(TikaReader.ParsedText(uri));
                    NodoRB NodoFinal = new NodoRB(uri, AvlPalabras);
                    System.out.println("SE INSERTO : " + uri + "ARBOL AVL :  " + NodoFinal.getDato().getDato());
                    FinalTree.insert(NodoFinal);
                    //System.out.println("PREORDEN ACTUAL BN");
                    //FinalTree.verPreOrder();

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (SAXException e) {
                        e.printStackTrace();
                    }
                    catch (TikaException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static void main(String [] args){
        ABBX ar = new ABBX();
        ar.insertar(new Nodo("uri 1", 23));
        ar.insertar(new Nodo("uri 7", 23));
        ar.insertar(new Nodo("uri 2", 23));
        ar.insertar(new Nodo("uri 6", 23));
        ar.insertar(new Nodo("uri 3", 33));
        ar.insertar(new Nodo("uri 5", 33));
        ar.insertar(new Nodo("uri 4", 33));

        ar.imprimirEntre();
        System.out.println();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();
        ar.PopNodo();
        ar.imprimirEntre();

    }

}
    

