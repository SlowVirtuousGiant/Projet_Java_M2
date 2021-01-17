
package fr.dauphine.sj.monrocqxu.rdvmedecin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
public interface UserRepository extends CrudRepository<User, Long> {
 
    @Query("SELECT u FROM User u WHERE u.mail = :mail")
    public User getUserByUsername(@Param("mail") String mail);
}