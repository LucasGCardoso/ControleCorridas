package com.bcopstein.CtrlCorredorV1.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoEstatisticas {
    private IEventoRepository eventoRepo;
    
    @Autowired
    public ServicoEstatisticas(IEventoRepository eventoRepository) {
        this.eventoRepo = eventoRepository;
    }

    public List<Evento> getPorDistancia(int distancia){
        return this.eventoRepo.getPorDistancia(distancia);
    }

    public List<Evento> getPorDistanciaEAno(int distancia, int ano){
        return this.eventoRepo.getPorDistanciaEAno(distancia, ano);
    }
    
}
