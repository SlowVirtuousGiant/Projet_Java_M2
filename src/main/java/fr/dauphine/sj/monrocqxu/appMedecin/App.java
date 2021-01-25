package fr.dauphine.sj.monrocqxu.appMedecin;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String  originalPassword = "password";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);
        //System.out.println( "Hello World!" );
    }
}
