package com.bcopstein.ctrlcorredor_v5_DIP_SRP.Interface;

import java.util.List;

import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.Corredor;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticasDTO;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.Evento;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.PerformanceDTO;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoCorredor;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoEstatistica;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ServicoEvento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasController {
    private ServicoCorredor servicoCorredor;
    private ServicoEvento servicoEvento;
    private ServicoEstatistica servicoEstatistica;

    @Autowired
    public CtrlCorridasController(ServicoEstatistica servicoEstatistica,ServicoCorredor servicoCorredor,ServicoEvento servicoEvento) {
        this.servicoEstatistica = servicoEstatistica;
        this.servicoCorredor = servicoCorredor;
        this.servicoEvento = servicoEvento;
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public List<Corredor> consultaCorredor() {
        return servicoCorredor.todos();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        servicoCorredor.cadastraCorredor(corredor);
        return true;
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public List<Evento> consultaEventos() {
        return servicoEvento.todos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
        servicoEvento.cadastra(evento);
        return true;
    }

    @GetMapping("/estatisticas")
    @CrossOrigin(origins = "*")
    public EstatisticasDTO estatisticas(@RequestParam final Integer distancia){
        return servicoEstatistica.calculaEstatisticas(distancia);
    }

    @GetMapping("/aumentoPerformance")
    @CrossOrigin(origins = "*")
    public PerformanceDTO aumentoPerformance(@RequestParam final Integer distancia,
                                            @RequestParam final Integer ano){
        return servicoEstatistica.calculaAumentoPerformance(distancia, ano);
    }
}
