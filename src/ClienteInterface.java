import java.rmi.Remote;

import java.rmi.RemoteException;

public interface ClienteInterface extends Remote {
    public void attTurn(int piece) throws RemoteException;
    public void updatePoints(int p0, int p1, int p2) throws RemoteException;
    public void attChat(String msg) throws RemoteException;
}
