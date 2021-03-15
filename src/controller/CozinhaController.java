package controller;

import java.util.concurrent.Semaphore;

public class CozinhaController extends Thread {

	private int countLasanha = 0;
	private int countSopa = 0;
	private int tempo;
	private int idThread;
	private Semaphore semaforo;

	public CozinhaController(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		Cozinha();
	}

	private void Cozinha() {
		if (idThread % 2 == 0) {
			tempo = (int) ((Math.random() * 300) + 500);
			String prato = "Sopa de Cebola";
			try {
				int temp = tempo/100;
				System.out.println("Prato: Sopa de Cebola iniciado pela thread #" + idThread + " e demorará " + temp + " segundos para ficar pronto.");
				while (countSopa < 100) {
					
					sleep(100);
					countSopa += tempo / 100;
					System.out.println("O prato da thread " + idThread +  " está: " + countSopa + "% concluído");
					if(countSopa >= 90) {
					System.out.println("O prato da thread " + idThread +  " está 100% concluído");
					break;
					}
				}
				EntregarPrato(idThread, prato);

			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			tempo = (int) ((Math.random() * 600) + 600);
			String prato = "Lasanha a Bolonhesa";
			try {
				int temp = tempo/100;
				System.out.println("Prato: Lasanha a Bolonhesa iniciado pela thread #" + idThread + " e demorará " + temp + " segundos para ficar pronto.");
				while (countLasanha < 100) {
					sleep(100);
					countLasanha += tempo / 100;
					System.out.println("O prato da thread " +idThread +  " está: " + countLasanha + "% concluído");
					if(countLasanha >= 90) {
					System.out.println("O prato da thread " +idThread +  " está 100% concluído");
					break;
					}
				}
				EntregarPrato(idThread, prato);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	private void EntregarPrato(int id, String prato) {
		try {
			semaforo.acquire();
			System.out.println("O prato " + prato + " da thread #" + idThread + " foi entregue com sucesso!");
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}
}
