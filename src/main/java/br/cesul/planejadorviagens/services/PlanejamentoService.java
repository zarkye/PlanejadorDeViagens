package br.cesul.planejadorviagens.services;

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.repository.ViagemRepository;
import java.util.List;
import java.time.LocalDate;

public class PlanejamentoService {

    private final ViagemRepository repo = new ViagemRepository();


    public void adicionar(String destino, LocalDate ini, LocalDate fim, double custo){
        if(destino == null || destino.isBlank()){
            throw new IllegalArgumentException("Destino vazio");
        }
        if(ini == null || fim == null){
            throw new IllegalArgumentException("Datas são obrigatórias");
        }
        if(ini.isAfter(fim)){
            throw new IllegalArgumentException("Inicio posterior ao fim");
        }
        if(custo < 0){
            throw new IllegalArgumentException("Custo inválido");
        }
        if(repo.conflita(ini, fim)){
            throw new IllegalArgumentException("Conflita com outra Viagem");
        }
        repo.save(new Viagem(custo, destino, ini, fim, null));
    }

    public List<Viagem> listar(){
        return repo.listarTodas();
    }

    public double totalGasto(){
        return repo.somar();
    }
}
