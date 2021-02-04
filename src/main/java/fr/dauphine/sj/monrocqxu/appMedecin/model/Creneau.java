package fr.dauphine.sj.monrocqxu.appMedecin.model;

import java.util.HashMap;
import java.util.Map;

public enum Creneau {
	c1(1,"8h00-8h30"),
	c2(2,"8h30-9h00"),
	c3(3,"9h00-9h30"),
	c4(4,"9h30-10h00"),
	c5(5,"10h00-10h30"),
	c6(6,"10h30-11h00"),
	c7(7,"11h00-11h30"),
	c8(8,"11h30-12h00"),
	c9(9,"12h00-12h30"),
	c10(10,"12h30-13h00"),
	c11(11,"13h00-13h30"),
	c12(12,"13h30-14h00"),
	c13(13,"14h00-14h30"),
	c14(14,"14h30-15h00"),
	c15(15,"15h00-15h30"),
	c16(16,"15h30-16h00"),
	c17(17,"16h00-16h30"),
	c18(18,"16h30-17h00"),
	c19(19,"17h00-17h30"),
	c20(20,"17h30-18h00"),
	c21(21,"18h00-18h30"),
	c22(22,"18h30-19h00"),
	c23(23,"19h00-19h30");
	
	private static final Map<String, Creneau> PAR_HEURE = new HashMap<>();
    private static final Map<Integer, Creneau> PAR_ID = new HashMap<>();
	
	static {
        for (Creneau e : values()) {
        	PAR_HEURE.put(e.name, e);
        	PAR_ID.put(e.id, e);
        }
    }
	
	public final int id;
	public final String name;
	
	private Creneau(int id, String name) {
		this.id=id;
		this.name=name;
		
	}
	
	public static Creneau valeurEnHeure(String label) {
        return PAR_HEURE.get(label);
    }

    public static Creneau valeurIdCreneau(int number) {
        return PAR_ID.get(number);
    }
}
