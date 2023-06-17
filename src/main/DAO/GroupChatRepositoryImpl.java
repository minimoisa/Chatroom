package main.DAO;

import main.objects.Chat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class GroupChatRepositoryImpl implements GroupChatRepository {
    private SessionFactory sessionFactory;

    public GroupChatRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Chat getGroupChatById(Long chatroomId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Chat.class, chatroomId);
        }
    }

    @Override
    public Chat addGroupChat(Chat chat) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(chat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return chat;
    }

    @Override
    public void updateGroupChat(Chat chat) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(chat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroupChat(Chat chat) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(chat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Chat> getAllGroupChats() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Chat", Chat.class).list();
        }
    }
}
