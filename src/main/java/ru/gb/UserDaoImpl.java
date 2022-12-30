package ru.gb;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao{

    private SessionFactoryUtils sessionFactoryUtils;


    public UserDaoImpl(SessionFactoryUtils sessionFactoryUtils) {

        this.sessionFactoryUtils = sessionFactoryUtils;
    }

//    @Override
//    public Optional<User> findById(Long id) {
//        try (Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
//            Optional<User> user = Optional.ofNullable(session.get(User.class, id));
//            session.getTransaction().commit();
//            return user;
//        }
//    }
     @Override
    public User findById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User findByName(String name) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.createQuery("select user from User user where user.name = :uname", User.class)
                            .setParameter("uname", name).getSingleResult();
            //в данном запросе:
            //createQuery "...user.name = :name" = :name указывает на название параметра который будет передаваться в запрос
            //setParameter здесь мы передаем параметр name в переменную uname
            session.getTransaction().commit();
            return user;
        }
    }
//    @Override
//    public Optional<User> findByName(String name) {
//        try(Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
//            Optional<User> user = Optional.ofNullable(session.createQuery("select user from User user where user.name = :uname", User.class)
//                            .setParameter("uname", name).getSingleResult());
//            //в данном запросе:
//            //createQuery "...user.name = :name" = :name указывает на название параметра который будет передаваться в запрос
//            //setParameter здесь мы передаем параметр name в переменную uname
//            session.getTransaction().commit();
//            return user;
//        }
//    }

    @Override
    public List<User> findAll() {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u").getResultList();
            //в скобках запрос на языке HPL - прослойка между. u - название объекта полученного из списка объектов класса
            //User. Следует отметить, что указывается не имя таблицы в запросы, а имя класса. В классе есть ссылка
            //на таблицу, поэтому удобнее использовать сразу класс.
            // суть запроса - верни объекты u из таблицы с объектами класса User которым присавивается имя u.
            //пример расширенного запроса "select u from User u where u.id < 10"
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void save(User user) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateNameById(Long id, String name) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
//              HQL Example:
//            session.createQuery("update User u set u.name =:name where u.id = :id")
//                    .setParameter("name", name)
//                    .setParameter("id", id)
//                    .executeUpdate();
            User user = session.get(User.class, id);
            user.setName(name);
            session.getTransaction().commit();
            //Комментированный код является более правильным т.к. расходует меньше памяти, не требует создавать объект
        }
    }
}
