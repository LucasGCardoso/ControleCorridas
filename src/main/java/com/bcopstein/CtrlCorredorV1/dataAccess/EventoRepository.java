package com.bcopstein.CtrlCorredorV1.dataAccess;

import java.util.List;

import com.bcopstein.CtrlCorredorV1.domain.Evento;
import com.bcopstein.CtrlCorredorV1.domain.IEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoRepository implements IEventoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

        this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");
        this.jdbcTemplate.batchUpdate(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('1','Poa Day Run',22,5,2019,5,0,36,32)");

        this.jdbcTemplate.batchUpdate(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('2','Poa Day Run 2',23,5,2019,5,0,34,32)");

        this.jdbcTemplate.batchUpdate(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('3','Poa Day Run 3',24,5,2019,5,0,31,32)");

    }

    public List<Evento> consultaEventos(){
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                rs.getInt("segundos")));
        return resp;
    }

    public List<Evento> getPorDistancia(int distancia){
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos WHERE distancia = " + distancia,
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                rs.getInt("segundos")));
        return resp;
    }

    public List<Evento> getPorDistanciaEAno(int distancia, int ano){
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos WHERE distancia = " + distancia + " AND ano = " + ano,
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                rs.getInt("segundos")));
        return resp;
    }

    

    public boolean informaEvento(Evento evento){
        this.jdbcTemplate.update(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
            evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
            evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
        return true;
        
    }
    
}







