package com.bcopstein.CtrlCorredorV1.adaptadores.repositorios;

import java.util.List;
import com.bcopstein.CtrlCorredorV1.negocio.entidades.Corredor;

import org.springframework.data.repository.CrudRepository;

public interface ICrudCorredorRepository extends CrudRepository<Corredor, String>{

    List<Corredor> findAll ();
}
