package mislugares.example.org.firebase;

/**
 * Created by Quintero on 20/03/2017.
 */

public interface Turismo {

    LugarTuristico elemento(int id); //Devuelve el elemento dado su id
    void anyade(LugarTuristico lugar); //Añade el elemento indicado
    int nuevo(); //Añade un elemento en blanco y devuelve su id
    void borrar(int id); //Elimina el elemento con el id indicado
    int tamanyo(); //Devuelve el número de elementos
    void actualiza(int id, LugarTuristico lugar); //Reemplaza un elemento



}
