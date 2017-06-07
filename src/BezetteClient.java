import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Vinicius
 *
 */
public class BezetteClient {
	
	private BezetteInterface bezette;
	
	private boolean runClient = true;
	
	private boolean clientRegistered = false;
	
	private int idPlayer = -1;
	
	Scanner scanner = new Scanner(System.in); 

	public void startClient(){
		try {
			bezette = (BezetteInterface) Naming.lookup ("//localhost/Bezette");
			this.runConsole();
		} catch (Exception e) {
			System.out.println ("BezetteClient failed");
			e.printStackTrace();
		}
	}
	
	private void runConsole() throws RemoteException{
		System.out.println("Starting Bezette - Client");
		
		String option;
		do{
			printMenu();
			
			option = scanner.nextLine();
			switch(option){
			case "0":
				this.case0();
				break;
			case "1":
				this.case1();
				break;
			default:
				System.out.println("Parametro inválido. Tente novamente.");
				break;
			}			
		}while(runClient);
	}
	
	private void case0() throws RemoteException{
		runClient = false;
		System.out.println("Fechando Bezette.");
	}
	
	private void case1() throws RemoteException{
		if(clientRegistered){
			System.out.println("Sessão já possui um usuário cadastrado. Id: " + idPlayer);
		}
		System.out.println("Escreva seu nome:");
		String nomeJogador = scanner.nextLine();
		idPlayer = bezette.registraJogador(nomeJogador);
		clientRegistered = true;
	}
	
	private void printMenu(){
		//clearConsole();
		System.out.println("## Bezette Menu - Digite o número da operação desejada ##");
		System.out.println(" 0 : Fechar jogo. ");
		System.out.println(" 1 : Registrar jogador. ");
	}
	
	private void clearConsole(){
		try {
			//TODO: not working
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}