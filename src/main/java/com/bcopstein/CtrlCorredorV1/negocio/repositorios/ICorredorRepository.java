package com.bcopstein.CtrlCorredorV1.negocio.repositorios;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.negocio.entidades.Corredor;

public interface ICorredorRepository {
    List<Corredor> todos();
    void removeTodos();
    boolean cadastra(Corredor corredor);
}
