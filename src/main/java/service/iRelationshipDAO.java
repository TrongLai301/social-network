package service;

public interface iRelationshipDAO {
    public String getRelationshipBetween(int firstUser, int secondUser);
    public void addRequestAddFriend(int senderId, int receiveId);
    public boolean isSender(int senderId);
    public boolean isReceiver(int receiveId);
}
