
package fr.dauphine.sj.monrocqxu.rdvmedecin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
public interface UserRepository extends CrudRepository<Utilisateur, Long> {
 
    @Query("SELECT u FROM Utilisateur u WHERE u.mail = :mail")
    public Utilisateur getUserByUsername(@Param("mail") String mail);
    
        public static Utilisateur toto() {
        	Utilisateur u = new Utilisateur();
        	u.setId(9);
        	u.setNom("fraise");
        	u.setPrenom("pomme");
        	u.setTelephone("0622334455");
        	u.setMail("pomme@gmail.com");
        	u.setNaissance("1955");
        	u.setAdresse("5 rue lucien sans paix");
        	u.setCode_postal(75010);
        	u.setVille("Paris");
        	u.setRole("MEDECIN");
        	u.setMdp("$2y$12$s1zD5K2fbNtcPigPhpoxWeDT.9Mlbk4W5GxUScHwl7necwkYxYVJ2");
        	
    	System.out.println("bawé logik");
    	return u;
    }
    
}