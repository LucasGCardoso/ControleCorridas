package com.bcopstein.CtrlCorredorV1.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.negocio.entidades.Evento;
import com.bcopstein.CtrlCorredorV1.negocio.repositorios.IEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoRepository implements IEventoRepository{
    private ICrudEventoRepository eventoRepository;

    @Autowired
    public EventoRepository(ICrudEventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
        // Insere eventos
        this.cadastra(new Evento((long)1,"Poa Day Run",22,5,2019,5,0,35,32));
        this.cadastra(new Evento((long)2,"Poa Night Run",12,6,2019,5,0,31,10));
        this.cadastra(new Evento((long) 3,"Winter Day Run",9,7,2019,5,0,29,17));
        this.cadastra(new Evento((long)4,"Summer Night Run",18,12,2019,5,0,32,25));
    }  

    @Override
    public List<Evento> todos() {
        return eventoRepository.findAll();
    }

    @Override
    public boolean cadastra(Evento evento){
        if(eventoRepository.save(evento) instanceof Evento){
            return true;
        }
        return false;
    }
}
