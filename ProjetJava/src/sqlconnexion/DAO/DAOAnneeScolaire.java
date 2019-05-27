package sqlconnexion.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sqlconnexion.Model.AnneeScolaire;

/**
 *
 * @author samrhein
 */
public class DAOAnneeScolaire extends DAO<AnneeScolaire> {

    /**
     * 
     * @param conn
     */
    public DAOAnneeScolaire(Connection conn) {
        super(conn);
    }

    
    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean create(AnneeScolaire obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO tab_professeur_pro (pro_id,pro_name) VALUES(?,?)"
                    );
            statement.setObject(1,obj.getAnneeScolaireID(), Types.INTEGER); 
           
            statement.executeUpdate(); 
        } catch (SQLException ex) {
           // Logger.getLogger(DAOEtudiant.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean delete(AnneeScolaire obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean update(AnneeScolaire obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param id
     * @return
     */
    @Override
    public AnneeScolaire find(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
