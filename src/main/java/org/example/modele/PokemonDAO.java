package org.example.modele;

import org.example.utils.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PokemonDAO {


    public void sauvegarder(Pokemon p) throws SQLException{
        String sql =
            "INSERT INTO pokemons"
            +"(experience_base,crie,grandeur,id,is_default,nom,ordre,espece,image,stats,type_poke,weight)"
            +"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
            +"ON CONFLICT (id) DO UPDATE SET "
            +" nom = EXCLUDED.nom";

        try (Connection co = Connexion.getConnexion();
             PreparedStatement ps = co.prepareStatement(sql)){
                ps.setInt(1,p.experience_base);
                ps.setString(2,p.crie ) ;
                ps.setInt(3,p.grandeur ) ;
                ps.setInt(4,p.id ) ;
                ps.setBoolean(5,p.is_default );
                ps.setString(6,p.nom ) ;
                ps.setInt(7,p.ordre ) ;
                ps.setString(8,p.espece ) ;
                ps.setString(9,p.image);
                ps.setString(10,p.stats);
                ps.setString(11,p.type_poke);
                ps.setInt(12,p.weight);
                ps.executeUpdate();
        }
    }

    public List<Pokemon> lister() throws SQLException {
        List<Pokemon> tous = new ArrayList<>();
        String sql = " SELECT * FROM pokemons ORDER BY nom ASC ";

        try (Connection co = Connexion.getConnexion();
             Statement st = co.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.experience_base = rs.getInt("experience_base");
                p.crie = rs.getString("crie");
                p.grandeur = rs.getInt("grandeur");
                p.id = rs.getInt("id");
                p.is_default = rs.getBoolean("is_default");
                p.nom = rs.getString("nom");
                p.ordre = rs.getInt("ordre");
                p.espece = rs.getString("espece");
                p.image = rs.getString("image");
                p.stats = rs.getString("stats");
                p.type_poke = rs.getString("type_poke");
                p.weight = rs.getInt("weight");
                tous.add(p);
            }
        }
        return tous;
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM pokemons WHERE id=?";
        try (Connection co = Connexion.getConnexion();
             PreparedStatement ps = co.prepareStatement(sql)) {
            ps.setInt(1, id);
            int lignesAffectees = ps.executeUpdate();
            if (lignesAffectees == 0) {
                throw new SQLException(" Aucun Pokemon avec l'id " + id);
            }
        }
    }
}

