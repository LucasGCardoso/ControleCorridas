package com.bcopstein.CtrlCorredorV1;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {

    private CorredorRepository corredorRepo;
    private EventoRepository eventoRepo;

    @Autowired
    public CtrlCorridasControler(JdbcTemplate jdbcTemplate) {
        this.corredorRepo = new CorredorRepository(jdbcTemplate);
        this.eventoRepo = new EventoRepository(jdbcTemplate);
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
            return corredorRepo.consultaCorredor();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        return corredorRepo.cadastraCorredor(corredor);
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return eventoRepo.consultaEventos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        return eventoRepo.informaEvento(evento);
    }

    @GetMapping(value = "/estatisticas", produces = MediaType.APPLICATION_JSON_VALUE) 
    @CrossOrigin(origins = "*") 
    public EstatisticasDTO estatisticas(@RequestParam final int distancia){
        List<Evento> lista = eventoRepo.getPorDistancia(distancia);
        double soma = 0;
        double mediana;

        for (Evento evento : lista) {
            soma += (evento.getHoras()*60) + evento.getMinutos();
        }
        double media = soma / lista.size();
        if(lista.size() % 2 == 0){
            Evento evento1 = lista.get(lista.size()/2);
            Evento evento2 = lista.get(lista.size()/2 - 1);
            mediana = (evento1.getHoras()*60 + evento1.getMinutos() + evento2.getHoras()*60 + evento2.getMinutos()) / 2; 
        }else{
            Evento evento = lista.get(lista.size()/2);
            mediana = evento.getHoras()*60 + evento.getMinutos();
        }

        double somaUnica = 0;
        double somaDP = 0;
        for (Evento evento : lista) {
            somaUnica = 0;
            somaUnica += (evento.getHoras()*60) + evento.getMinutos();
            somaDP += Math.pow((somaUnica - media), 2);
        }
        double desvio_padrao = Math.sqrt(somaDP/lista.size());

        EstatisticasDTO stat  = new EstatisticasDTO(media, mediana, desvio_padrao, lista.size());
        return stat;


    }

    @GetMapping("/aumentoPerformance") 
    @CrossOrigin(origins = "*") 
    public PerformanceDTO aumentoPerformance(@RequestParam final int distancia, @RequestParam final int ano){
        List<Evento> lista = eventoRepo.getPorDistanciaEAno(distancia, ano);
        // Pegar a lista, comparar duas a duas, guardar a diff e nomes. Se forem as bests, criar objeto e retornar
        if(lista.size() < 2){
            //Caso não haja provas suficiente o método retorna nulo.
            return null;
        }

        double bestDiff = Integer.MIN_VALUE;
        String prova1 = "";
        String prova2 = "";

        for (int i = 0; i < lista.size()-1; i++) {
            Evento evento1 = lista.get(i);
            Evento evento2 = lista.get(i+1);
            int tempo1 = evento1.getHoras()*3600 + evento1.getMinutos()*60 + evento1.getSegundos();
            int tempo2 = evento2.getHoras()*3600 + evento2.getMinutos()*60 + evento2.getSegundos();
            System.out.println(prova1 + prova2 + "diff de tempo: " + (tempo1 - tempo2));
            if(tempo1 - tempo2 > bestDiff && tempo1 - tempo2 > 0){ 
                bestDiff = tempo1 - tempo2;
                prova1 = evento1.getNome();
                prova2 = evento2.getNome();   
            }
        }

        if(bestDiff == Integer.MIN_VALUE){
            PerformanceDTO performance = new PerformanceDTO("Não houve melhora", "Não houve melhora", 0);
            return performance;
        }
        PerformanceDTO performance = new PerformanceDTO(prova1, prova2, bestDiff);
        return performance;
    }
}
