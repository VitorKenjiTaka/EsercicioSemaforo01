package Controller;
import java.util.concurrent.Semaphore;

public class Processo extends Thread {
	private int threadId;
	private Semaphore semaforo;
	
	public Processo(int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
		
	}
	
	public void run() {
		
		if (threadId % 3 == 0) {
			calculo();
			transacao();
			calculo();
			transacao();
			calculo();
			transacao();		
		}
		
		if (threadId % 3 == 1) {
			calculo();
			transacao();
			calculo();
			transacao();
		}
		
		if (threadId % 3 == 2) {
			calculo();
			transacao();
			calculo();
			transacao();
			calculo();
			transacao();
		}
		
	}
	
	public void transacao() {
		int tempo = 0;
		
		if (threadId % 3 == 1) {
			tempo = 1000;
		}
		if (threadId % 3 == 2) {
			tempo = 1500;
		}
		if (threadId % 3 == 0) {
			tempo = 1500;
		}
		
		try {
			semaforo.acquire();
			System.out.println("A thread #" + threadId + " está fazendo a transação.");
			Thread.sleep(tempo);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}finally {
			semaforo.release();
		}
		System.out.println("A thread #" + threadId + " acabou a transação.");
	}
	
	
	public void calculo() {
		int tempo = 0;
		if (threadId % 3 == 1) {
			tempo = (int) ((int) (Math.random() + 0.2) * Math.pow(10, 9));
		}
		if (threadId % 3 == 2) {
			tempo = (int) ((int) ((Math.random()* 1.6) + 0.5) * Math.pow(10, 9));
		}
		if (threadId % 3 == 0) {
			tempo = (int) ((int) ((Math.random() * 1.1) + 1) * Math.pow(10, 9));
		}
		System.out.println("A thread #" + threadId + " está fazendo o calculo.");
		
		try {
			Thread.sleep(tempo);
		} catch (Exception e) {
			
		}
	}
}
