package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Unita {

	PG(1),
	GG(2);
	
	private final int val;
	
	Unita(int val){
		this.val=val;
	}

	public int getVal() {
		return val;
	}

	private static final List<Unita> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Unita randomUnita()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
