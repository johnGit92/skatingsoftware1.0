package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classifica")
public class Classifica {

	private int pk;
	private int numero;
	private String id1,id2,id3,id4,id5;
	private double tot1,tot2,tot3,tot4,tot5;
	private int pos1,pos2,pos3,pos4,pos5;
	private int pt,posFin;
	
	public Classifica() {
		super();
	}

	public double getTot1() {
		return tot1;
	}

	public void setTot1(double tot1) {
		this.tot1 = tot1;
	}

	public double getTot2() {
		return tot2;
	}

	public void setTot2(double tot2) {
		this.tot2 = tot2;
	}

	public double getTot3() {
		return tot3;
	}

	public void setTot3(double tot3) {
		this.tot3 = tot3;
	}

	public double getTot4() {
		return tot4;
	}

	public void setTot4(double tot4) {
		this.tot4 = tot4;
	}

	public double getTot5() {
		return tot5;
	}

	public void setTot5(double tot5) {
		this.tot5 = tot5;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk")
	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public String getId4() {
		return id4;
	}

	public void setId4(String id4) {
		this.id4 = id4;
	}

	public String getId5() {
		return id5;
	}

	public void setId5(String id5) {
		this.id5 = id5;
	}

	public int getPos1() {
		return pos1;
	}

	public void setPos1(int pos1) {
		this.pos1 = pos1;
	}

	public int getPos2() {
		return pos2;
	}

	public void setPos2(int pos2) {
		this.pos2 = pos2;
	}

	public int getPos3() {
		return pos3;
	}

	public void setPos3(int pos3) {
		this.pos3 = pos3;
	}

	public int getPos4() {
		return pos4;
	}

	public void setPos4(int pos4) {
		this.pos4 = pos4;
	}

	public int getPos5() {
		return pos5;
	}

	public void setPos5(int pos5) {
		this.pos5 = pos5;
	}

	public int getPt() {
		return pt;
	}

	public void setPt(int pt) {
		this.pt = pt;
	}

	public int getPosFin() {
		return posFin;
	}

	public void setPosFin(int posFin) {
		this.posFin = posFin;
	}
	
	
	
}
