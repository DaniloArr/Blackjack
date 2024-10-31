package com.sd.blackjack;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		System.out.println(" *** INICIANDO BLACKJACK ***");
		
		Baralho playingDeck = new Baralho();
		playingDeck.createFullDeck();
		playingDeck.embaralhamento();
		
		Baralho jogadorDeck = new Baralho();
		Baralho dealerDeck = new Baralho();
		
		double dinheiroApostaInicial = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		while(dinheiroApostaInicial > 0) {
			boolean fimJogo = false;
			
			System.out.println("Jogador tem R$ " + dinheiroApostaInicial + ", vai apostar quanto ? ");
			double valorApostado = userInput.nextDouble();
			if(valorApostado > dinheiroApostaInicial) {
				//da para colocar uma logica que ele pode ou nao inserir um valor menor
				//CASO "S", da mais uma chance para inserir outro valor
				//CASO "N", acaba o jogo
				System.out.println("Valor apostado eh inferior a quanto possui. Ate a proxima");
				break;
			}
			
			//duas cartas para ambos os jogadores
			jogadorDeck.pegarUmaCartaDoBaralho(playingDeck);
			jogadorDeck.pegarUmaCartaDoBaralho(playingDeck);
			
			dealerDeck.pegarUmaCartaDoBaralho(playingDeck);
			dealerDeck.pegarUmaCartaDoBaralho(playingDeck);
			
			while(true) {
				System.out.println("Mao do Jogador: ");
				System.out.println(jogadorDeck.toString());
				System.out.println("A sua mao vale: " + jogadorDeck.cartasValue());
				
				System.out.println("Mao do Dealer: " + dealerDeck.getCartas(0).toString() + " [OUTRA CARTA TA ESCONDIDA]");
				//System.out.println(jogadorDeck.toString());
				//System.out.println("A sua mao vale: " + jogadorDeck.cartasValue());
				
				System.out.println("Quer mais uma carta(1) ou parou(2) ?");
				int respostaContinuaPara = userInput.nextInt();
				
				//adicionar uma validacao para caso o usuario insira um valor diferente desses dois
				
				if(respostaContinuaPara == 1) {
					jogadorDeck.pegarUmaCartaDoBaralho(playingDeck);
					System.out.println("A carta que pegou eh: " + jogadorDeck.getCartas(jogadorDeck.tamanhoBaralho()-1).toString());
					
					if(jogadorDeck.cartasValue() > 21) {
						System.out.println("Estourou o limite. Voce ficou com : "+ jogadorDeck.cartasValue());
						dinheiroApostaInicial -= valorApostado;
						fimJogo = true;
						break;
					}
				} else if (respostaContinuaPara == 2) {
					break;
				}
			}
			
			System.out.println("Cartas do Dealer: " + dealerDeck.toString());
			
			if(dealerDeck.cartasValue() > jogadorDeck.cartasValue() && !fimJogo) {
				System.out.println("Dealer ganhou!");
				dinheiroApostaInicial -=  valorApostado;
				fimJogo = true;
			}
			
			while((dealerDeck.cartasValue() < 17 ) && !fimJogo) {
				dealerDeck.pegarUmaCartaDoBaralho(playingDeck);
				System.out.println("Ultima carta do Dealer: " + dealerDeck.getCartas(dealerDeck.tamanhoBaralho() - 1).toString());
			}
			
			System.out.println("Cartas do Dealer valem: " + dealerDeck.cartasValue());
			if((dealerDeck.cartasValue() > 21) && !fimJogo) {
				System.out.println("Dealer perdeu! Voce ganhou");
				dinheiroApostaInicial += valorApostado;
				fimJogo = true;
			}
			
			if((jogadorDeck.cartasValue() == dealerDeck.cartasValue()) && !fimJogo) {
				System.out.println("EMPATE");
				fimJogo = true;
			}
			
			if((jogadorDeck.cartasValue() > dealerDeck.cartasValue()) && !fimJogo) {
				System.out.println("Voce venceu!");
				dinheiroApostaInicial += valorApostado;
				fimJogo = true;
			} else if(!fimJogo) {
				System.out.println("Voce perdeu essa rodada");
				dinheiroApostaInicial -= valorApostado;
				fimJogo = true;
			}
			
			jogadorDeck.moverTodoBaralho(playingDeck);
			dealerDeck.moverTodoBaralho(playingDeck);
			
			System.out.println("Rodada encerrada.\n");
		}
		
		userInput.close();
		System.out.println("\n*** FIM DE JOGO ***");

	}

}
