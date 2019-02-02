package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Specialita {
	
	SINCRO(1),
	COREO(2),
	SHOW(3),
	CONTEMPORANEA(4),
	MODERNA(5),
	ORIENTALI(6),
	ZUMBA(7),
	ASSOLUTA(8),
	SALSA(9),
	LATINO(10),
	CARAIBICHE(11);
	
	private final int val;
	
	Specialita(int val){
		this.val=val;
	}
	
	public int getVal() {
		return val;
	}

	private static final List<Specialita> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Specialita randomSpecialita()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
