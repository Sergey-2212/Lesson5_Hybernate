package ru.gb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {

    private SessionFactory factory;

    public void init() {
        //Открытие сессии ресурсозатратный процесс. Создается один раз при запуске приложение и держится открытым до завершения
        //Вынесено в статический метод, чтобы инициализировать при компиляции.
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        System.out.println("init");
    }

    public Session getSession() {
       return factory.getCurrentSession();
    }
    public void shutdown() {
        if(factory.isOpen()) {
            factory.close();
        }
    }
}
