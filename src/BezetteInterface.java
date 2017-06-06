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
	
	//Recebe:�string�com�o�nome�do�usu�rio/jogador
	//Retorna:�id�(valor�inteiro)�do�usu�rio�(que�corresponde�a�um�n�mero�de�identifica��o��nico�para
	//este�usu�rio�durante�uma�partida),��1�se�este�usu�rio�j�est� �cadastrado�ou��2�se�o�n�mero
	//m�ximo�de�jogadores�(2�vezes�o�n�mero�m�ximo�de�partidas)�tiver�sido�atingido
	public int registraJogador(String nomeDoJogador) throws RemoteException;
	
	
	//Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//Retorna:��1�(erro),�0�(ainda�n�o�h�partida),�1�(sim,�h�partida�e�o�jogador�inicia�jogando)�ou�2
	//(sim,�h�partida�e�o�jogador��o�segundo�a�jogar)
	public int temPartida(int idUsuario) throws RemoteException;

	
	//Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//Retorna:��2�(erro:�ainda�n�o�h�partida),��1�(erro:�jogador�n�o�encontrado),�0�(n�o),�1�(sim),�2�(�
	//o�vencedor),�3�(�o�perdedor),�4�(houve�empate),�5�(vencedor�por�WO),�6�(perdedor�por�WO)
	public int ehMinhaVez(int idUsuario) throws RemoteException;
	
	//	Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//	Retorna:�string�vazio�em�caso�de�erro�ou�string�com�uma�representa��o�do�tabuleiro�de�jogo�(por
	//	exemplo,�para�representar�um�jogo�em�que�o�primeiro�jogador�est�com�6�argolas,�h�10�argolas
	//	na�haste�e�o�segundo�jogador�est�com�8�argolas,�pode�se�usar��6(10)8�)
	public String obtemTabuleiro(int idUsuario) throws RemoteException;
	
	//	Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//	Retorna:�string�vazio�para�erro�ou�string�com�o�nome�do�oponente
	public String obtemOponente(int idUsuario) throws RemoteException;
	
	//	Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//	Retorna:�3�n�meros�inteiros�correspondendo�aos�valores�obtidos�jogando�cada�um�dos�3�dados,
	//	ou�3�valores�com�c�digos�de�erro:��1�(erro),��2�(erro:�ainda�n�o�h�partida),��3�(n�o��a�vez�do
	//	jogador),��4�(�a�vez�do�jogador,�mas�n�o�para�jogar�dados)
	public String jogaDados(int idUsuario) throws RemoteException;
	
	//	Recebe:�id�do�usu�rio�(obtido�atrav�s�da�chamada�registraJogador)
	//	Retorna:�c�digo�de�sucesso�(0�indica�sucesso�e��1,�erro)
	//	Observa��o:�caso�um�dos�jogadores�chame�encerraPartida�antes�de�se�determinar�um�vencedor
	//	para�a�partida�ou�de�se�determinar�que�houve�empate,�o�outro�jogador�ser�vencedor�por�WO�(ou
	//	seja,�receber�o�c�digo�5�quando�chamar�ehMinhaVez)
	public int encerraPartida(int idUsuario) throws RemoteException;
}