package main.Controller;


import main.DAO.GroupChatRepositoryImpl;
import main.DAO.MessageRepository;
import main.objects.Chat;
import main.objects.Message;
import main.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ChatroomController {
    private Chat main;
    @Autowired
    private GroupChatRepositoryImpl chatroomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/chatrooms")
    @ResponseBody
    public List<Chat> getChatrooms() {
        return chatroomRepository.getAllGroupChats();
    }

    @PostMapping("/chatrooms")
    @ResponseBody
    public Chat createChatroom(@RequestBody Chat chatroom) {
        return chatroomRepository.addGroupChat(chatroom);
    }

    @GetMapping("/chatrooms/{id}")
    @ResponseBody
    public Chat getChatroom(@PathVariable Long id) {
        if(chatroomRepository.getGroupChatById(id)!= null){
            return chatroomRepository.getGroupChatById(id);
        }else {
            return null;
        }
    }

    @PostMapping("/messages")
    @ResponseBody
    public void sendMessage(@RequestBody MessageRequest messageRequest) {

        User user = UserController.main;
        Chat chatroom = main;
        Message message = new Message();
        message.setUser(user);
        message.setChat(chatroom);
        message.setContent(messageRequest.getContent());

        messageRepository.addMessage(message);
    }
}