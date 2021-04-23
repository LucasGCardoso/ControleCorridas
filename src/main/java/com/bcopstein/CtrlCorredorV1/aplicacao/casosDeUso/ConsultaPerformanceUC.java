package com.bcopstein.CtrlCorredorV1.aplicacao.casosDeUso;

import com.bcopstein.CtrlCorredorV1.aplicacao.dtos.PerformanceDTO;
import com.bcopstein.CtrlCorredorV1.aplicacao.servicos.ServicoEstatistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPerformanceUC {
        private ServicoEstatistica servicoEstatistica;
    
        @Autowired
        public ConsultaPerformanceUC(ServicoEstatistica servicoEstatistica) {
            this.servicoEstatistica = servicoEstatistica;
        }
    
        public PerformanceDTO run(int distancia,int ano){
            return servicoEstatistica.calculaAumentoPerformance(distancia, ano);
        }
    }
    