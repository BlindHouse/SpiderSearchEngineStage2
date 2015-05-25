/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;



import Estructuras.ArbolAVL;


/**
 *
 * @author wilson
 */
public class RedAndBlackTree {
    
    NodoRB raiz;
    String rojo = "ROJO";
    String negro = "NEGRO";
    String recorrido;

    /**
     *
     */
    public RedAndBlackTree(){
        raiz = null;
    }

    /**
     *
     * @param root
     */
    public RedAndBlackTree(NodoRB root){
        raiz = root;
        root._color = negro;
    }
    
    /**
     *
     * @return
     */
    public NodoRB getRoot(){
        return raiz;
    }
    
    /**
     *
     * @param _raiz
     */
    public void setRoot(NodoRB _raiz){
        raiz = _raiz;
        raiz._color = negro;
    }

    /**
     *
     * @param nuevo
     * @return
     */
    public boolean insert(NodoRB nuevo){
            //System.out.println("DATO A INSERTAR : "+ nuevo.getDato().getDato());
            setRoot(insert_aux(nuevo, raiz));
            insertion_case1(nuevo);
            return true;
    }
    private NodoRB insert_aux(NodoRB nuevo, NodoRB _raiz){
        
        if(_raiz == null){
            _raiz = nuevo;
            //nuevo.setPadre(null);
            
        }
        else{
             nuevo.setPadre(_raiz);            
             if (!nuevo.getDato().itsHight(_raiz.getDato().getDato()) ||
                     nuevo.getDato().equ(_raiz.getDato().getDato())){
                 //System.out.println("aja");
                _raiz.setLeft(insert_aux(nuevo,_raiz.getLeft()));
             }
             else{
                 //System.out.println(_raiz.getDato().getDato());
                _raiz.setRight(insert_aux(nuevo,_raiz.getRight()));
            }
        }
        
        return _raiz;
   }

    /**
     *
     * @param hijo
     * @return
     */
    public NodoRB getFather(NodoRB hijo){
        if(raiz != null){
            if(hijo == raiz){
                return null;
            }
            else{
             return getFather_aux(hijo, raiz);
            }

        }
        return null;

    }
    private NodoRB getFather_aux(NodoRB hijo, NodoRB _raiz){
        try{
            if(_raiz == null){
                //System.out.println("RAIZ == NULL");
                return null;
            }
            if(_raiz.getLeft() == hijo ||  _raiz.getRight() == hijo){
                return _raiz;
            }
            else{
                if(hijo.getDato().itsLower(_raiz.getDato().getDato())){
                    return getFather_aux(hijo,_raiz.getLeft());
                }
                else{
                    return getFather_aux(hijo, _raiz.getRight());
                }
            }
        }
        catch(Exception ex){
            return null;
        }
    }

    /**
     *
     * @param nodo
     * @return
     */
    public NodoRB getAbuelo(NodoRB nodo){
        if((nodo != null) && (this.getFather(nodo) != null)  ){
            NodoRB padre = this.getFather(nodo);
            return this.getFather(padre);
        }
        else{
            return null;
        }
    }

     private NodoRB leftSimpleRotation(NodoRB nodo){
        // System.out.println("LSR   " +nodo.getDato().getDato());
        NodoRB p = getFather(nodo);
        NodoRB a = p.getLeft();
        NodoRB b = nodo.getLeft();
        NodoRB c = nodo.getRight();
        nodo.setPadre(p.getPadre());
        nodo.setLeft(p);
        p.setPadre(nodo);
        p.setRight(b);
        if(b != null) {
             b.setPadre(p);
         }
        return nodo;
    }

    /**
     *
     * @param k2
     * @return
     */
    public NodoRB RightSimpleRotation(NodoRB k2){
       // System.out.println("RSR ORIGINAL PROFE");
        NodoRB k1 = k2.getLeft();
        NodoRB A = k1.getLeft();
        NodoRB B = k1.getRight();
        NodoRB C = k2.getRight();
        k2.setLeft(B);
        k1.setRight(k2);
        //MODIFICAR ALTURAS
        return k2;
    }
    /**
     * 
     * @param nodo
     * @return nodo al que se le hace la rotacion
     */
    private NodoRB rightSimpleRotation(NodoRB nodo){
        //System.out.println("RSR   " +nodo.getDato());
        NodoRB p = getFather(nodo);
        NodoRB a = nodo.getLeft();
        NodoRB b = nodo.getRight();
        NodoRB c = p.getRight();
        nodo.setPadre(p.getPadre());
        nodo.setRight(p);
        p.setPadre(nodo);
        p.setLeft(b);
        if(b != null) {
            b.setPadre(p);
        }
        return nodo;
    }

 
    private void insertion_case1(NodoRB nodo){
//        NodoRB padre = nodo.padre;
//        NodoRB abuelo = padre.padre;
        if(nodo == raiz){
//            System.out.println("CASO 1 EL NODO A INSERTAR ES LA RAIZ");
            nodo._color = negro;
        }
        else{
//            System.out.println("ELSE");
//            System.out.println("DATO A INSERTAR  "+nodo.getDato().getDato());
            insertion_case2(nodo);
        }
    }
    private void insertion_case2(NodoRB nodo){
       
        NodoRB padre = this.getFather(nodo);
        //System.out.println("padre " + padre.getDato());
         if (padre._color.equals(negro)){
               // System.out.println("NODO A INSERTAR : " + nodo.getDato().getDato());
               // System.out.println("CASO 2 EL PADRE ES DE COLOR NEGRO");
         }
        else /*if(!padre._color.equals(negro))*/{
             //System.out.println("CASO 2 ELSE");
            insertion_case3(nodo);
        }
       
        
    }
    private void insertion_case3(NodoRB nodo){
        if(this.getFather(nodo) != null){
            NodoRB padre = this.getFather(nodo);
            NodoRB abuelo = this.getFather(padre);
            NodoRB tio;
            if(padre.getDato().itsLower(abuelo.getDato().getDato())){
                tio = abuelo.getRight();
            }
            else {
                tio = abuelo.getLeft();
            }
            if(tio != null && (tio._color.equals(rojo) && padre._color.equals(rojo))){
                //System.out.println("CASO 3 SI EL PADRE Y EL TIO SON ROJOS Y EL TIO != NULL" );
                //System.out.println("COLOR PADRE :" +  padre._color);
                padre._color = negro;
                //System.out.println("COLOR PADRE :" +  padre._color);
                tio._color = negro;
                
                abuelo._color = rojo;
                insertion_case1(abuelo);
            }
            else{
                    //System.out.println("CONTINUA A CASO 4");
                    insertion_case4(nodo);
            }
        }
        
        
        
    }
    private void insertion_case4(NodoRB nodo){
        //System.out.println("CASO 4 AFUERA");
        if(this.getFather(nodo) != null && this.getFather(this.getFather(nodo)) != null){
            //System.out.println("CASO 4 ADENTRO");
            NodoRB padre = this.getFather(nodo);
            NodoRB abuelo = this.getAbuelo(nodo);
            if((nodo == padre.getLeft() && padre == abuelo.getRight()
                    ) && padre._color.equals(rojo)){
//                nodo = rightSimpleRotation(nodo);
//                abuelo.setRight(nodo);
                abuelo.setRight(rightSimpleRotation(nodo));
                nodo = nodo.getRight();
                insertionCase5(nodo);
            }
            if((nodo == padre.getRight() && padre == abuelo.getLeft())
                    && padre._color.equals(rojo)){
                
                abuelo.setLeft(leftSimpleRotation(nodo));
                nodo = nodo.getLeft();
                insertionCase5(nodo);
                
            }

       }
        //else{
        insertionCase5(nodo);
            //insertion_case5(nodo);
        //}
    }
    private void  insertionCase5(NodoRB nodo){
        NodoRB padre = getFather(nodo);
        NodoRB abuelo = getFather(padre);
        if(abuelo != null){
            //System.out.println("CASO 5");
            if(nodo == padre.getLeft() && padre == abuelo.getLeft() &&
            (abuelo.getRight() == null || abuelo.getRight()._color.equals(negro)  ) &&
                    padre._color.equals(rojo) && (abuelo.getLeft()._color.equals(negro) || 
                    abuelo.getLeft() == null)){
                NodoRB bisabuelo = getFather(abuelo);
                //System.out.println("NODO HIJO IZQ - PADRE HIJO IZQ - TIO NEGRO || NULL");
                if(bisabuelo != null){//abuelo no es la raiz principal
                   // System.out.println("BISABUELO != NULL");
                    abuelo._color = rojo;
                    padre._color = negro;
                    if(bisabuelo.getLeft() == abuelo){//si el abuelo es hijo izq
                        padre = this.rightSimpleRotation(padre);
                        bisabuelo.setLeft(padre);
                                                                                                                            
                    }
                    else{
                        padre = rightSimpleRotation(padre);
                        bisabuelo.setRight(padre);
                    }
                }
                else{//significa que el abuelo es la raiz principal
                    raiz = rightSimpleRotation(padre);
                }
            }
            if(nodo == padre.getRight() && padre == abuelo.getRight() && 
                    (abuelo.getLeft() == null || abuelo.getLeft()._color.equals(negro))
                    && padre._color.equals(rojo) && (abuelo.getLeft() == null) ||
                    abuelo.getLeft()._color.equals(negro) 
                    ){
                //System.out.println("NODO HIJO DER - PADRE HIJO DER - TIO NEGRO || NULL");
                NodoRB bisabuelo = this.getFather(abuelo);
                abuelo._color = rojo;
                padre._color = negro;
                if(bisabuelo != null){
                     //System.out.println("BISABUELO != NULL");
                     
                    if(bisabuelo.getLeft() == abuelo){//si el abuelo es hijo izq
                        padre = this.leftSimpleRotation(padre);
                        bisabuelo.setLeft(padre);
                                                                                                                            
                    }
                    else{
                        padre = leftSimpleRotation(padre);
                        bisabuelo.setRight(padre);
                    }
                }
                else{//significa que el abuelo es la raiz principal
                    raiz = leftSimpleRotation(padre);
                }
            }
        }
    }
    
    
    private void insertion_case5(NodoRB nodo){//
        //System.out.println("CASO 5");
        if(this.getFather(nodo) != null){
            NodoRB padre = this.getFather(nodo);
            NodoRB abuelo = this.getFather(padre);
            abuelo._color = rojo;
            padre._color  = negro;
            if(nodo == padre.getLeft() && padre == abuelo.getLeft() && 
                    (abuelo.getRight() == null || abuelo.getRight()._color.equals(negro) )){
                //System.out.println("NODO HIJO IZQ - PADRE HIJO IZQ - TIO NEGRO || NULL");
                NodoRB bisabuelo = getFather(abuelo);
                if(bisabuelo != null){
                    //System.out.println("bisabuelo != null");
                    //SIGNIFICA QUE EL ABUELO NO ES LA RAIZ PRINCIPAL DEL ARBOL
//                    abuelo._color = rojo;
//                    padre._color  = negro;
                    //System.out.println(padre._color);
                    if(abuelo == bisabuelo.getLeft()){
                        nodo = rightSimpleRotation(padre);
                        bisabuelo.setLeft(nodo);
                    }
                    else{
                        nodo = rightSimpleRotation(padre);
                        bisabuelo.setRight(nodo);
                    }

                }                
                raiz = rightSimpleRotation(padre);
            }
            abuelo._color = rojo;
            padre._color  = negro;
            if(nodo == padre.getRight() && padre == abuelo.getRight() && 
                    (abuelo.getLeft() == null || abuelo.getLeft()._color.equals(negro))){
                //System.out.println("NODO HIJO DER - PADRE HIJO DER - TIO NEGRO || NULL");
                NodoRB bisabuelo = this.getFather(abuelo);
                if(bisabuelo != null){
                    //System.out.println("bisabuelo != null");
                    //SIGNIFICA QUE EL ABUELO NO ES LA RAIZ PRINCIPAL DEL ARBOL
//                    abuelo._color = rojo;
//                    padre._color  = negro;
                    System.out.println(padre._color);
                    if(abuelo == bisabuelo.getLeft()){
                        padre = leftSimpleRotation(padre);
                        bisabuelo.setLeft(padre);
                    }
                    else{
                        padre = leftSimpleRotation(padre);
                        bisabuelo.setRight(padre);
                    }

                }
                
                raiz = leftSimpleRotation(padre);
            }
        
        }
  }


    

    private void preOrder(NodoRB raiz){
        if(raiz != null){
            System.out.println("Dato : " + raiz.getDato().getDato() + " Color :" + raiz._color +"--");
            recorrido += " - " + raiz.getDato().getDato()+ "---"+raiz._color;
            preOrder(raiz.getRight());
            preOrder(raiz.getLeft());
        }
    }

    /**
     *
     * @return
     */
    public String verPreOrder(){
        recorrido = "";
        preOrder(raiz);
        return recorrido;

    }


 public void Buscar(String Busqueda){

     Recorrido_Aux(raiz,Busqueda);
 }
 
 private void Recorrido_Aux(NodoRB rec,String Busqueda) {
     if (rec != null) {
         String uri = rec.getDato().getDato();
         System.out.println("Se esta recorriendo el arbol voy por el nodo " + uri);
             rec.arbol.Buscar(uri, Busqueda);
             Recorrido_Aux(rec.getLeft(), Busqueda);
             Recorrido_Aux(rec.getRight(), Busqueda);

     }
 }

    /**
     *home/wilson/NetBeansProjects/SpyderSearchEngineStage2
     * @param ar
     */
     public static void main (String [] ar){
     
        ArbolAVL arbol1 = new ArbolAVL();
        ArbolAVL arbol2 = new ArbolAVL();
        ArbolAVL arbol3 = new ArbolAVL();
         ArbolAVL arbol4 = new ArbolAVL();
         ArbolAVL arbol5 = new ArbolAVL();
         ArbolAVL arbol6 = new ArbolAVL();

        arbol1.insert(1,"Arbol 1.1");
        arbol1.insert(2,"Arbol 1.2");
        arbol1.insert(3,"Arbol 1.3");
        arbol2.insert(1,"Arbol repeat");
        arbol2.insert(2,"Arbol 2.2");
        arbol2.insert(3,"Arbol 2.3");
        arbol3.insert(1,"Arbol repeat");
        arbol3.insert(2,"Arbol 3.2");
        arbol3.insert(3,"Arbol 3.3");
         arbol4.insert(1,"Arbol repeat");
         arbol4.insert(2,"Arbol 3.2");
         arbol4.insert(3,"Arbol 3.3");
         arbol5.insert(1,"Arbol repeat");
         arbol5.insert(2,"Arbol 3.2");
         arbol5.insert(3,"Arbol 3.3");
         arbol6.insert(1,"Arbol repeat");
         arbol6.insert(2,"Arbol 3.2");
         arbol6.insert(3,"Arbol 3.3");

       NodoRB nuevo1 = new NodoRB("Nodo 1",arbol1);
       NodoRB nuevo2 = new NodoRB("Nodo 2",arbol2);
       NodoRB nuevo3 = new NodoRB("Nodo 3",arbol3);
        NodoRB nuevo4 = new NodoRB("Nodo 4",arbol4);
        NodoRB nuevo5 = new NodoRB("Nodo 5",arbol5);
        NodoRB nuevo6 = new NodoRB("Nodo 6",arbol6);
      // NodoRB nuevo4 = new NodoRB("Cabalilo");
      // NodoRB nuevo5 = new NodoRB("Gallina");
      // NodoRB nuevo6 = new NodoRB("Cerveza");
       // NodoRB nuevo7 = new NodoRB("carne");

      RedAndBlackTree abl=new RedAndBlackTree();
      abl.insert(nuevo1);
      abl.insert(nuevo2);
      abl.insert(nuevo3);
      abl.insert(nuevo4);
      abl.insert(nuevo5);
      abl.insert(nuevo6);
      //abl.insert(nuevo7);
      System.out.println();
      abl.verPreOrder();
      abl.Buscar("Arbol 1.1");
      //abl.raiz.arbol.Buscar("CAS");



    }
}
