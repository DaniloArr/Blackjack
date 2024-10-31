package com.sd.blackjack;

public class Card {

	private Naipes naipes;
	private Value valor;
	
	public Card(Naipes naipes, Value valor) {
		this.naipes = naipes;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Carta eh " + this.valor.toString() + " - " +this.naipes.toString();
	}

	public Naipes getNaipes() {
		return naipes;
	}

	public void setNaipes(Naipes naipes) {
		this.naipes = naipes;
	}

	public Value getValor() {
		return valor;
	}

	public void setValor(Value valor) {
		this.valor = valor;
	}
	
	
	
}

	
