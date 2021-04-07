package com.bcopstein.CtrlCorredorV1.domain;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.dataAccess.Evento;
import com.bcopstein.CtrlCorredorV1.dataAccess.EventoRepository;

import org.springframework.jdbc.core.JdbcTemplate;

public class ServicoEstatisticas {
    private EventoRepository eventoRepo;
    

    public ServicoEstatisticas(JdbcTemplate jdbcTemplate) {
        this.eventoRepo = new EventoRepository(jdbcTemplate);
    }

    public List<Evento> getPorDistancia(int distancia){
        return this.eventoRepo.getPorDistancia(distancia);
    }

    public List<Evento> getPorDistanciaEAno(int distancia, int ano){
        return this.eventoRepo.getPorDistanciaEAno(distancia, ano);
    }
    
}
