package ru.gb;

import org.hibernate.Session;

import java.util.List;

public class ProductRepository implements ProductDao{

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductRepository(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public Product findById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select prod from Product prod")
                    .getResultList();
            return products;
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete Product prod where prod.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public Product saveOrUpdate(Product product) {
        try(Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }
}
