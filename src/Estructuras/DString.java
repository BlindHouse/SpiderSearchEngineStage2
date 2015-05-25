/*
 * DString.java
 * Creado Octubre 22, 2011, 05:40pm
 */
package Estructuras;

/**
 * * @author wilson
 * 
 *Clase que maneja los datos de tipo String
 * implementa metodos de la clase IData.java
 */

public class DString implements IData {
    
    private String _dato;               //declaracion de dato como String
    /**Constructor por defecto
     * 
     * @param pDato dato
     */
    public DString(String pDato){
        _dato = pDato;
    }

    public String getDato(){
        return _dato;
    }
    
    /** Metodo de IData para verificar si dos datos son iguales
     * @param pData dato a comparar
     * @return true si los datos son iguales y false en caso contrario
     */
    @Override
    public boolean equ(Object pData) {
        return _dato.equals((String)pData);
    }
    
    /**Metodo de IData que indica si un valor es menor que otro
     * @param pData dato a comparar
     * @return  true si el parametro es mayor y false en caso contrario
     */
    @Override
    public boolean itsLower(Object pData) {
        if(_dato.compareTo((String)pData) < 0){
            return true;
        }
        else{
            return false;
        }

    }
    /**Metodo de IData que indica si un valor es menor que otro
     * @param pData dato a comparar
     * @return  true si el parametro es menor y false en caso contrario
     */
    @Override
    public boolean itsHight(Object pData) {
        if(_dato.compareTo((String)pData) > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
}
