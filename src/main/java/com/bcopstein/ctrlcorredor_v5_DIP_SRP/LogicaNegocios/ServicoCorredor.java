package com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoCorredor {
    public ICorredorRepository corredorRep;

    @Autowired
    public ServicoCorredor(ICorredorRepository corredorRep){
        this.corredorRep = corredorRep;
    }

    public List<Corredor> todos(){
        return corredorRep.todos();
    }

    public void cadastraCorredor(Corredor corredor){
        corredorRep.removeTodos();
        corredorRep.cadastra(corredor);
    }
    
}
