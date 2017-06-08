import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

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
			case "2":
				this.case2();
				break;
			case "3":
				this.case3();
				break;
			case "4":
				this.case4();
				break;
			case "5":
				this.case5();
				break;
			case "6":
				this.case6();
				break;
			case "7":
				this.case7();
				break;
			default:
				System.out.println("Parametro inv�lido. Tente novamente.");
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
			System.out.println("Sess�o j� possui um usu�rio cadastrado. Id: " + idPlayer);
		}
		System.out.println("Escreva seu nome:");
		String nomeJogador = scanner.nextLine();
		idPlayer = bezette.registraJogador(nomeJogador);
		clientRegistered = true;
	}
	
	private void case2() throws RemoteException{
		int result = bezette.temPartida(idPlayer);
		switch(result){
		case 0:
			System.out.println(" Ainda n�o h� partidas.");
			break;
		case 1:
			System.out.println(" Voc� j� est� em uma partida e come�a jogando. ");
			break;
		case 2:
			System.out.println(" Voc� j� est� em uma partida mas n�o � o primeiro a jogar. ");
			break;
		default:
			System.out.println(" Ocorreu um problema na op��o \"tem partida\". ");
			break;
		}
	}
	
	private void case3() throws RemoteException{
		int result = bezette.ehMinhaVez(idPlayer);
		switch(result){
		case 0:
			System.out.println("Ainda n�o � sua vez.");
			break;
		case 1:
			System.out.println("Sim, � sua vez.");
			break;	
		case 2:
			System.out.println("Voc� � o VENCEDOR!!!.");
			break;		
		case 3:
			System.out.println("Voc� perdeu!");
			break;
		case 5:
			System.out.println("Voc� � o VENCEDOR(por WO)!!!.");
			break;	
		case 6:
			System.out.println("Voc� perdeu(por WO)!");
			break;
		default:
			System.out.println("Algum erro ocorreu. Voc� provavelmente n�o est� em uma partida.");
			break;
		}
	}
	
	private void case4() throws RemoteException{
		String result = bezette.obtemTabuleiro(idPlayer);
		System.out.println(result);
	}
	
	private void case5() throws RemoteException{
		String result = bezette.obtemOponente(idPlayer);
		System.out.println(result);
	}
	
	private void case6() throws RemoteException{
		String result = bezette.jogaDados(idPlayer);
		
		if(result == null){
			System.out.println("Ocorreu um erro. Ou voc� n�o est� em uma partida, ou n�o � sua vez.");
		} else if(result.equalsIgnoreCase("")){
			System.out.println("A partida j� terminou. Utilize a opcao 3 para saber o status.");
		}
		else{
			System.out.println(result);
		}	
	}
	
	private void case7() throws RemoteException{
		int result = bezette.encerraPartida(idPlayer);
		if(result < 0){
			System.out.println(" Ocorreu um erro. Voc� provavelmente n�o est� em uma partida.");
		} else{
			System.out.println("Voc� saiu da partida. Perdeu por WO.");
		}
	}
	
	private void printMenu(){
		clearConsole();
		System.out.println("## Bezette Menu - Digite o n�mero da opera��o desejada : ## ID: " + idPlayer);
		System.out.println(" 0 : Fechar jogo. ");
		System.out.println(" 1 : Registrar jogador. ");
		System.out.println(" 2 : Tem partida? ");
		System.out.println(" 3 : Eh minha vez?");
		System.out.println(" 4 : Obter tabuleiro");
		System.out.println(" 5 : Obter nome do oponente");
		System.out.println(" 6 : Jogar dados");
		System.out.println(" 7 : Encerrar partida");
	}
	
	private void clearConsole() {
		//TODO: doesnt work
		System.out.print("\r\n");
	}
}