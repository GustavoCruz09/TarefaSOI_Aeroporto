package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
		Semaphore areadedecolagem = new Semaphore(2);
		Semaphore pistanorte = new Semaphore(1);
		Semaphore pistasul = new Semaphore(1);
		
		for(int aviao = 1; aviao <= 5; aviao++) {
			ThreadAeroporto aeroporto = new ThreadAeroporto(aviao, areadedecolagem, pistanorte, pistasul);
			aeroporto.start();
		}
		
	}

}
