package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Disciplina {

	LATIN(1),
	POPDANCE(2),
	HIPHOP(3);
	
	private final int val;
	
	Disciplina(int val){
		this.val=val;
	}
	
	public int getVal() {
		return val;
	}

	private static final List<Disciplina> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Disciplina randomDisciplina()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
