package com.bcopstein.CtrlCorredorV1.domain;

import java.util.List;

public interface IEventoRepository{

    public List<Evento> consultaEventos();
    public List<Evento> getPorDistancia(int distancia);
    public List<Evento> getPorDistanciaEAno(int distancia, int ano);
    public boolean informaEvento(Evento evento);
}