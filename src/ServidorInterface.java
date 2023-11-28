import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {
    public int initPlayers() throws RemoteException;
    public void addressPlayer(int playerName , String playerURL ) throws RemoteException;
    public void sendPiece(int playerName, int piece) throws RemoteException;
    public void attPieces(int p0, int p1, int p2, int playerName) throws RemoteException;
    public void sendMessage(String msg, int playerName) throws RemoteException;
}
