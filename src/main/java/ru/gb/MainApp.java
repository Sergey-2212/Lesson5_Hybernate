package ru.gb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;


/**
 * Hello world!
 *
 */
public class MainApp   {


    public static void main( String[] args )   {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            UserDao userDao = new UserDaoImpl(sessionFactoryUtils);
            User user = userDao.findById(1L);
            System.out.println(user);

            List<User> users = userDao.findAll();
            System.out.println(users);
            System.out.println(userDao.findByName("Bob"));
            userDao.save(new User("Max"));
            System.out.println(userDao.findAll());
            userDao.updateNameById(1L, "Garfield");
            System.out.println(userDao.findAll());

            ProductRepository repository = new ProductRepository(sessionFactoryUtils);

            System.out.println(repository.findAll());
            System.out.println(repository.findById(1L));
            repository.saveOrUpdate(new Product("Carrot", 48));
            System.out.println(repository.findAll());
            repository.deleteById(1L);
            repository.deleteById(2L);
            System.out.println(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Упс");
        } finally {
            sessionFactoryUtils.shutdown();
        }


    }
}
