package main.DAO;

import main.objects.Chat;

import java.util.List;

public interface GroupChatRepository {
    Chat getGroupChatById(Long chatroomId);
    Chat addGroupChat(Chat chat);
    void updateGroupChat(Chat chat);
    void deleteGroupChat(Chat chat);
    List<Chat> getAllGroupChats();
}