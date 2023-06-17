package main.DAO;

import main.objects.Message;
import main.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository{
    private SessionFactory sessionFactory;

    public MessageRepositoryImpl(com.mysql.cj.xdevapi.SessionFactory sessionFactory) {

    }


    @Override
    public Message getMessageById(int MessageId) {
        try (Session session = sessionFactory.openSession()) {
            return (Message) session.get(Message.class, MessageId);
        }
    }

    @Override
    public void addMessage(Message message) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateMessage(Message message) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(Message message) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getAllMessages() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Message ",Message.class).list();
        }
    }
}
