package com.bcopstein.CtrlCorredorV1.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoEvento {
    private IEventoRepository eventoRep;

    @Autowired
    public ServicoEvento(IEventoRepository eventoRep){
        this.eventoRep = eventoRep;
    } 

    public List<Evento> todos(){
        return eventoRep.consultaEventos();
    }

    public void cadastra(Evento evento){
        eventoRep.informaEvento(evento);
    }
    
}
