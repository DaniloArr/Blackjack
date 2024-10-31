package com.sd.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Baralho {

	private ArrayList<Card> cartas;
	
	public Baralho() {
		this.cartas = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
		for(Naipes naipeCartas : Naipes.values()) {
			for (Value valorCartas : Value.values()) {
				this.cartas.add(new Card(naipeCartas, valorCartas));
			}
		}
	}
	
	public void embaralhamento() {
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		Random random = new Random();
		int randomCartaId = 0;
		int tamanhoOriginal = this.cartas.size();
		
		for(int i = 0; i < tamanhoOriginal; i++) {
			randomCartaId = random.nextInt((this.cartas.size() - 1  - 0) + 1) + 0; //random.nextInt((max - min) + 1) + min
			tempDeck.add(this.cartas.get(randomCartaId));
			this.cartas.remove(randomCartaId);
		}
		
		this.cartas = tempDeck;
	}

	@Override
	public String toString() {
		String listaCartasCriadas = "";
		for(Card carta : this.cartas) {
			listaCartasCriadas += "\n " + carta.toString();
		}
		return " \n " + listaCartasCriadas + "\n ";
	}
	

	public Card getCartas(int id) {
		return this.cartas.get(id);
	}
	
	public void addCarta(Card addCard) {
		this.cartas.add(addCard);
	}
		
	public void removeCarta(int id) {
		this.cartas.remove(id);
	}
	
	public int tamanhoBaralho() {
		return this.cartas.size();
	}
	
	public void pegarUmaCartaDoBaralho(Baralho comingFrom) {
		this.cartas.add(comingFrom.getCartas(0));
		comingFrom.removeCarta(0);
	}
	
	
	public int cartasValue() {
		int valorTotal = 0;
		//int as = 0; //representacao da quantidade das cartas "A"
		
		for(Card cartas : this.cartas) {
			switch (cartas.getValor()){
			case DOIS: valorTotal += 2;
				break;
			case TRES: valorTotal += 3;
				break;
			case QUATRO: valorTotal += 4;
				break;
			case CINCO: valorTotal += 5;
				break;
			case SEIS: valorTotal += 6;
				break;
			case SETE: valorTotal += 7;
				break;
			case OITO: valorTotal += 8;
				break;
			case NOVE: valorTotal += 9;
				break;
			case DEZ: valorTotal += 10;
				break;
			case VALETE: valorTotal += 10;
				break;
			case DAMA: valorTotal += 10;
				break;
			case REI: valorTotal += 10;
				break;
			case AS: 
				if(valorTotal > 10) {
					valorTotal += 1;
				} else {
					valorTotal += 11;
				};
				break;
			}
		}
		
		return valorTotal;
	}
	
}
