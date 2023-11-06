package service;

public interface iRelationshipDAO {
    public String getRelationshipBetween(int firstUser, int secondUser);
    public void addRequestAddFriend(int senderId, int receiveId);
    public boolean isSender(int senderId,int receiverId);
    public boolean isReceiver(int senderId,int receiverId);
    public void addFriend(int senderId,int receiverId);
    public void deleteRelationshipOf(int firstUser, int secondUser);
}
