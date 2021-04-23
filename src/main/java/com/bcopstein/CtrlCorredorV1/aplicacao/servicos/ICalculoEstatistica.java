package com.bcopstein.CtrlCorredorV1.aplicacao.servicos;

import com.bcopstein.CtrlCorredorV1.aplicacao.dtos.EstatisticasDTO;

public interface ICalculoEstatistica {
    EstatisticasDTO calculaEstatisticas(int distancia);
}
