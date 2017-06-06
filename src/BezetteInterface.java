import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 */

/**
 * @author Vinicius
 *
 */
public interface BezetteInterface extends Remote {
	
	//Recebe: string com o nome do usuário/jogador
	//Retorna: id (valor inteiro) do usuário (que corresponde a um número de identificação único para
	//este usuário durante uma partida), ­1 se este usuário já está  cadastrado ou ­2 se o número
	//máximo de jogadores (2 vezes o número máximo de partidas) tiver sido atingido
	public int registraJogador(String nomeDoJogador) throws RemoteException;
	
	
	//Recebe: id do usuário (obtido através da chamada registraJogador)
	//Retorna: ­1 (erro), 0 (ainda não há partida), 1 (sim, há partida e o jogador inicia jogando) ou 2
	//(sim, há partida e o jogador é o segundo a jogar)
	public int temPartida(int idUsuario) throws RemoteException;

	
	//Recebe: id do usuário (obtido através da chamada registraJogador)
	//Retorna: ­2 (erro: ainda não há partida), ­1 (erro: jogador não encontrado), 0 (não), 1 (sim), 2 (é
	//o vencedor), 3 (é o perdedor), 4 (houve empate), 5 (vencedor por WO), 6 (perdedor por WO)
	public int ehMinhaVez(int idUsuario) throws RemoteException;
	
	//	Recebe: id do usuário (obtido através da chamada registraJogador)
	//	Retorna: string vazio em caso de erro ou string com uma representação do tabuleiro de jogo (por
	//	exemplo, para representar um jogo em que o primeiro jogador está com 6 argolas, há 10 argolas
	//	na haste e o segundo jogador está com 8 argolas, pode­se usar “6(10)8”)
	public String obtemTabuleiro(int idUsuario) throws RemoteException;
	
	//	Recebe: id do usuário (obtido através da chamada registraJogador)
	//	Retorna: string vazio para erro ou string com o nome do oponente
	public String obtemOponente(int idUsuario) throws RemoteException;
	
	//	Recebe: id do usuário (obtido através da chamada registraJogador)
	//	Retorna: 3 números inteiros correspondendo aos valores obtidos jogando cada um dos 3 dados,
	//	ou 3 valores com códigos de erro: ­1 (erro), ­2 (erro: ainda não há partida), ­3 (não é a vez do
	//	jogador), ­4 (é a vez do jogador, mas não para jogar dados)
	public String jogaDados(int idUsuario) throws RemoteException;
	
	//	Recebe: id do usuário (obtido através da chamada registraJogador)
	//	Retorna: código de sucesso (0 indica sucesso e ­1, erro)
	//	Observação: caso um dos jogadores chame encerraPartida antes de se determinar um vencedor
	//	para a partida ou de se determinar que houve empate, o outro jogador será vencedor por WO (ou
	//	seja, receberá o código 5 quando chamar ehMinhaVez)
	public int encerraPartida(int idUsuario) throws RemoteException;
}