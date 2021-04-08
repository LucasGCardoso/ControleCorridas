package com.bcopstein.CtrlCorredorV1.domain;
import java.util.List;

public interface ICorredorRepository{

    public List<Corredor> consultaCorredor();
    public boolean cadastraCorredor(Corredor corredor);
}