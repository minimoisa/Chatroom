package main.DAO;

import main.objects.Message;

import java.util.List;

public interface MessageRepository {
    Message getMessageById(int messageId);
    void addMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Message message);
    List<Message> getAllMessages();
}

