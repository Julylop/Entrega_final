package com.juliana.entrega_final.controladores;

import com.juliana.entrega_final.modelos.Libro;

import java.util.List;

public interface I_LibroBD {

    Libro elemento(int id);//Devuelve el elemento dado su Id
    Libro elemento (String title);//Devuelve el elemento dado su titulo exacto

    List<Libro>lista();//Devuelve una lista con todos los elementos registrados
    void agregar(Libro book);// Añade el elemento indicado
    void actualizar(int id ,Libro book);//Actualiza datos del elemento dado su Id
    void borrar(int id);//Elimina el elemento indicado con el Id
}
