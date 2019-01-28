package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Categoria {

	U5(1),
	U7(2),
	U9(3),
	U11(4),
	U13(5),
	U16(6),
	U21(7),
	O16(8),
	O35(9),
	O50(10),
	OPEN(11),
	ASSOLUTA(12);

	private final int val;

	Categoria(int val){
		this.val=val;
	}

	public int getVal() {
		return val;
	}

	private static final List<Categoria> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Categoria randomCategory()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
