package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.modele.Pokemon;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokedexAPI{
    private static final String URL = "https://pokeapi.co/api/v2/pokemon/";

    private final HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper mapper = new ObjectMapper();

    public Pokemon recuperer(int id) throws Exception{
        HttpRequest req = HttpRequest.newBuilder(URI.create(URL + id)).GET().build();

        HttpResponse<String> res = client.send(req,HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() != 200) {
            throw new RuntimeException(

                    "!! Erreur : " + res.statusCode());
        }


        JsonNode pokemon = mapper.readTree(res.body());

        Pokemon p = new Pokemon();


        p.experience_base =Integer.parseInt(pokemon.get("base_experience").asText());
        p.crie =pokemon.get("cries").get("latest").asText() ;
        p.grandeur =Integer.parseInt(pokemon.get("height").asText());
        p.id =Integer.parseInt(pokemon.get("id").asText());
        p.is_default =pokemon.get("is_default").asBoolean();
        p.nom =pokemon.get("name").asText() ;
        p.ordre =Integer.parseInt(pokemon.get("order").asText());
        p.weight =Integer.parseInt(pokemon.get("weight").asText());
        p.image =pokemon.get("sprites").get("other").get("official-artwork").get("front_default").asText() ;
        p.espece =pokemon.get("species").get("name").asText() ;

        StringBuilder stats = new StringBuilder();
        for (JsonNode stat : pokemon.get("stats")){
            stats.append(stat.get("stat").get("name").asText()).append(" : ").append(stat.get("base_stat").asText()).append("\n");

        }

        p.stats =stats.toString() ;

        StringBuilder types = new StringBuilder();
        for (JsonNode type : pokemon.get("types")){
            types.append(type.get("type").get("name").asText()).append(", ");
        }


        p.type_poke = types.toString();

        return p;
    }
}
