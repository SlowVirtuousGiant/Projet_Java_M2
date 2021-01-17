
package fr.dauphine.sj.monrocqxu.rdvmedecin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
public interface UserRepository extends CrudRepository<Utilisateur, Long> {
 
    @Query("SELECT u FROM Utilisateur u WHERE u.mail = :mail")
    public Utilisateur getUserByUsername(@Param("mail") String mail);
}