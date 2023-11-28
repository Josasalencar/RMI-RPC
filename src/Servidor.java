import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements ServidorInterface {

    private ClienteInterface clientePlayer1;
    private ClienteInterface clientePlayer2;
    private int numP;

    public Servidor() throws RemoteException {
        super();
        System.out.println("Iniciando Servidor");
        numP = 0;
    }

    @Override
    public int initPlayers() throws RemoteException {
        if (numP < 2) {
            numP++;
            System.out.println("Entrou Jogador " + numP );
            if (numP == 2) {
                System.out.println("Não tem mais jogadores para entrar");
            }
        }
        return numP;
    }

    @Override
    public void addressPlayer(int playerName , String playerAddress ) throws RemoteException {
        try {
            if (playerName == 1) {
                clientePlayer1 = (ClienteInterface) Naming.lookup(playerAddress);
            }

            if (playerName == 2) {
                clientePlayer2 = (ClienteInterface) Naming.lookup(playerAddress);
            }
        }
        catch (Exception e) {}
    }


    @Override
    public void sendMessage(String msg, int playerName) throws RemoteException {
        if (playerName == 1) {
            clientePlayer2.attChat(msg);
        }

        if (playerName == 2) {
            clientePlayer1.attChat(msg);
        }

    }
    @Override
    public void attPieces(int p0, int p1, int p2, int playerName) throws RemoteException {
        if (playerName == 1) {
            clientePlayer2.updatePoints(p0, p1, p2);
        }

        if (playerName == 2) {
            clientePlayer1.updatePoints(p0, p1, p2);
        }

    }

    @Override
    public void sendPiece(int piece, int playerName) throws RemoteException {
        if (playerName == 1) {

            clientePlayer2.attTurn(piece);
        }

        if (playerName == 2) {
            clientePlayer1.attTurn(piece);
        }

    }

    public static void main(String[] args) {
        try {
            Servidor s = new Servidor();
            LocateRegistry.createRegistry(2055);
            Naming.rebind("rmi://localhost:2055" + "/Server", s);

        }
        catch (Exception e){}
    }
}

