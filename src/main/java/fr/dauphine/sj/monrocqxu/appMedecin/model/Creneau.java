package fr.dauphine.sj.monrocqxu.appMedecin.model;

import java.util.HashMap;
import java.util.Map;

public enum Creneau {
	c1(1,"8h-8h30"),
	c2(2,"8h30-9h"),
	c3(3,"9h30-10h");
	
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
