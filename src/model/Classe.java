package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Classe {

	ED(1),
	RS(2),
	GS(3),
	ASSOLUTA(4);
	
	private final int val;
	
	Classe(int val){
		this.val=val;
	}
	
	public int getVal() {
		return val;
	}

	private static final List<Classe> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Classe randomClasse()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
