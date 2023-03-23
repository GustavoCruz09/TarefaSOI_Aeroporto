package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {
	private int aviao;
	private Semaphore areadedecolagem;
	private Semaphore pistanorte;
	private Semaphore pistasul;

	public ThreadAeroporto(int aviao, Semaphore areadedecolagem, Semaphore pistanorte, Semaphore pistasul) {
		this.aviao = aviao;
		this.areadedecolagem = areadedecolagem;
		this.pistanorte = pistanorte;
		this.pistasul = pistasul;
	}

	@Override
	public void run() {
		try {
			areadedecolagem.acquire();
			System.out.println("O " + aviao + "° aviao entrou na area de decolagem");
			SelecionaPista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			areadedecolagem.release();
		}
	}

	private void SelecionaPista() {
		int separa = (int) (Math.random() * 2) + 1;

		if (separa == 1) {
			try {
				pistasul.acquire();
				System.out.println("O " + aviao + "° aviao entrou na pista sul");
				ProcedimentoDecolagem();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				int tempoA = (int) (Math.random() * 501) + 300;

				try { // Afastamento
					System.out.println("O " + aviao + "° aviao comecou a se afastar da pista sul espere " + tempoA/100 + "s");
					sleep(tempoA);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pistasul.release();
			}
		} else {
			if (separa == 2) {
				try {
					pistanorte.acquire();
					System.out.println("O " + aviao + "° aviao entrou na pista norte");
					ProcedimentoDecolagem();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					int tempoA = (int) (Math.random() * 501) + 300;

					try { // Afastamento
						System.out.println("O " + aviao + "° aviao comecou a se afastar da pista norte espere " + tempoA/100 + "s");
						sleep(tempoA);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pistanorte.release();
				}
			}
		}
		
	}

	private void ProcedimentoDecolagem() {
		int tempoM = (int) (Math.random() * 401) + 300;

		try { // Manobrar
			System.out.println("O " + aviao + "° aviao comecou a manobrar espere " + tempoM/100 + "s");
			sleep(tempoM);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int tempoT = (int) (Math.random() * 501) + 500;

		try { // Taxiar
			System.out.println("O " + aviao + "° aviao comecou a taxiar espere " + tempoT/100 + "s");
			sleep(tempoT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int tempoD = (int) (Math.random() * 301) + 100;

		try { // Decolagem
			System.out.println("O " + aviao + "° aviao comecou a decolar espere " + tempoD/100 + "s");
			sleep(tempoD);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		int tempoA = (int) (Math.random() * 501) + 300;
//
//		try { // Afastamento
//			System.out.println("O " + aviao + "° aviao comecou a se afastar espere " + tempoA/100 + "s");
//			sleep(tempoA);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
