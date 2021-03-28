package com.bcopstein.CtrlCorredorV1;

public class PerformanceDTO {
    private String nomeProva1;
    private String nomeProva2;
    //Diferença de tempo em segundos
    private double diffTempo;

    public PerformanceDTO(String nomeProva1, String nomeProva2, double diffTempo){
        this.nomeProva1 = nomeProva1;
        this.nomeProva2 = nomeProva2;
        this.diffTempo = diffTempo;
    }

    public double getDiffTempo() {
        return diffTempo;
    }

    public String getNomeProva1() {
        return nomeProva1;
    }

    public String getNomeProva2() {
        return nomeProva2;
    }

}
