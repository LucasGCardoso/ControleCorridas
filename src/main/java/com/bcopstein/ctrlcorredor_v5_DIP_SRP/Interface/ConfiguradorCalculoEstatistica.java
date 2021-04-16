package com.bcopstein.ctrlcorredor_v5_DIP_SRP.Interface;

import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticaDesconsidera;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.EstatisticaNormal;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ICalculoEstatistica;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.IEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConfiguradorCalculoEstatistica {
    private IEventoRepository eventoRep;

    @Autowired
    public ConfiguradorCalculoEstatistica(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "original", matchIfMissing = true)
    public ICalculoEstatistica opcaoRegraClassica() {
        return new EstatisticaNormal(eventoRep);
    }

    @Bean
    @ConditionalOnProperty(name = "calculo.estatistica", havingValue = "desconsidera")
    public ICalculoEstatistica opcaoDesconsidera() {
        return new EstatisticaDesconsidera(eventoRep);
    }
}
