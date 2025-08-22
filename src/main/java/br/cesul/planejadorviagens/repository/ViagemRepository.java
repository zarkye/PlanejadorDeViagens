package br.cesul.planejadorviagens.repository;
// camada de persistencia. interage com o mongo
// e devolve/recebe objetos do modelo

import br.cesul.planejadorviagens.config.MongoConfig;
import br.cesul.planejadorviagens.model.Viagem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

// será responsavel por consultar e alterar os dados do mongo
// e só trabalha com objetos do tipo Viageme
public class ViagemRepository {

    private final MongoCollection<Viagem> col = MongoConfig.db.getCollection("trip-planner", Viagem.class);
    public void save(Viagem v){
        col.insertOne(v);
    }

    public void delete(Viagem v){
        col.deleteOne(eq("_id", v.getId()));
    }

    public void edit(Viagem oldV, Viagem newV){

        Bson update = Updates.combine(
                Updates.set("custo", newV.getCusto()),
                Updates.set("dataInicio", newV.getDataInicio()),
                Updates.set("dataFim", newV.getDataFim()),
                Updates.set("destino", newV.getDestino())
        );

        col.updateOne(Filters.eq("_id", oldV.getId()), update);
//        col.replaceOne(Filters.eq("_id", oldV.getId()), newV);
    }

    public List<Viagem> listarTodas(){
        return col.find().sort(ascending("dataInicio")).into(new ArrayList<>());
    }

    public double somar(){
        return col.find()
                .into(new ArrayList<>())
                .stream()
                .mapToDouble(Viagem::getCusto)
                .sum();
    }

    public boolean conflita(LocalDate ini, LocalDate fim){
        long qtd = col.countDocuments(
                Filters.and(
                        Filters.lte("dataInicio", fim),

                        Filters.gte("dataFim", ini)
                )
        );
        return qtd > 0;
    }

}
