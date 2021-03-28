package com.bcopstein.CtrlCorredorV1;

public class EstatisticasDTO {
    private double media;
    private double mediana;
    private double desvio_padrao;
    private int qtdProvas;

    public EstatisticasDTO(double media, double mediana, double desvio_padrao, int qtdProvas){
        this.media = media;
        this.mediana = mediana;
        this.desvio_padrao = desvio_padrao;
        this.qtdProvas = qtdProvas;
    }

    public double getDesvio_padrao() {
        return desvio_padrao;
    }

    public double getMedia() {
        return media;
    }

    public double getMediana() {
        return mediana;
    }

    public int getQtdProvas() {
        return qtdProvas;
    }
}
