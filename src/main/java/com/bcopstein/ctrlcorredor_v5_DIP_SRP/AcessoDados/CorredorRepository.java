package com.bcopstein.ctrlcorredor_v5_DIP_SRP.AcessoDados;

import java.util.List;

import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.Corredor;
import com.bcopstein.ctrlcorredor_v5_DIP_SRP.LogicaNegocios.ICorredorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CorredorRepository implements ICorredorRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CorredorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.jdbcTemplate.execute("DROP TABLE corredores IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE corredores("
                + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");

        cadastra(new Corredor("10001287","Luiz",22,5,1987,"masculino"));    }
    
    public List<Corredor> todos() {
        List<Corredor> resp = this.jdbcTemplate.query("SELECT * from corredores",
                (rs, rowNum) -> new Corredor(rs.getString("cpf"), rs.getString("nome"), rs.getInt("diaDn"),
                        rs.getInt("mesDn"), rs.getInt("anoDn"), rs.getString("genero")));
        return resp;
    }

    public void removeTodos(){
        // Limpa a base de dados
        this.jdbcTemplate.batchUpdate("DELETE from Corredores");
    }

    public boolean cadastra(Corredor corredor){
        // Então cadastra o novo "corredor único"
        this.jdbcTemplate.update("INSERT INTO corredores(cpf,nome,diaDn,mesDn,anoDn,genero) VALUES (?,?,?,?,?,?)",
        corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(), corredor.getAnoDn(),
        corredor.getGenero());
        return true;
    }
}
