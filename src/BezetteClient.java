import java.io.IOException;
import java.rmi.Naming;
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

	public void startClient(){
		try {
			bezette = (BezetteInterface) Naming.lookup ("//localhost/Bezette");
			this.runConsole();
		} catch (Exception e) {
			System.out.println ("BezetteClient failed:");
			e.printStackTrace();
		}
	}
	
	private void runConsole(){
		boolean runClient = true;
		
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Starting Bezette - Client");
		
		String option;
		do{
			printMenu();
			
			option = scanner.nextLine();
			switch(option){
			case "0":
				runClient = false;
				System.out.println("Fechando Bezette.");
				break;
			default:
				System.out.println("Parametro inválido. Tente novamente.");
				break;
			}			
		}while(runClient);
	}
	
	private void printMenu(){
		//clearConsole();
		System.out.println("## Bezette Menu - Digite o número da operação desejada ##");
		System.out.println(" 0 : Fechar jogo. ");
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