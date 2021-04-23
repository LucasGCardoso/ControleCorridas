package com.bcopstein.CtrlCorredorV1.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.negocio.entidades.Corredor;
import com.bcopstein.CtrlCorredorV1.negocio.repositorios.ICorredorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorredorRepository implements ICorredorRepository {
    private ICrudCorredorRepository corredorRepository;

    @Autowired
    public CorredorRepository(ICrudCorredorRepository corredorRepository) {
        this.corredorRepository = corredorRepository;
        this.cadastra(new Corredor("10001287","Luiz",22,5,1987,"masculino"));
    }
    
    @Override
    public List<Corredor> todos () {
        return this.corredorRepository.findAll();
    }

    @Override
    public void removeTodos(){
        this.corredorRepository.deleteAll();
    }

    @Override
    public boolean cadastra(Corredor corredor){
        if(this.corredorRepository.save(corredor) instanceof Corredor){
            return true;
        }
        return false;
    }
}
