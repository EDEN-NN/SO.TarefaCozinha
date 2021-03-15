package view;

import java.util.concurrent.Semaphore;

import controller.CozinhaController;

public class Main {

	public static void main(String[] args) {

		final int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		
		for (int i = 1; i < 6; i++) {
			Thread t = new CozinhaController(i, semaforo);
			t.start();
		}
	}

}
