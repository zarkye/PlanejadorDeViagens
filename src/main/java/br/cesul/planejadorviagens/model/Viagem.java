package br.cesul.planejadorviagens.model;

import org.bson.types.ObjectId;

import java.time.LocalDate;

// mostrar o que está no banco através de uma entidade
// esta classe representará uma viagem
// Constructor
// Atributos com getters e setters
public class Viagem {
    private double custo;
    private String destino;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private ObjectId id;

    public Viagem(){}

    public Viagem(double custo, String destino, LocalDate dataInicio, LocalDate dataFim, ObjectId id){
        if(custo < 0){
            throw new RuntimeException("Valor Nulo");
        }
        this.destino = destino;
        this.custo = custo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.id = id;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
