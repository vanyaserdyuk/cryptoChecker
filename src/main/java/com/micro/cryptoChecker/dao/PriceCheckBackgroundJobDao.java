package com.micro.cryptoChecker.dao;

import com.micro.cryptoChecker.model.PriceCheckBackgroundJob;
import com.micro.cryptoChecker.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriceCheckBackgroundJobDao {
    public void save(PriceCheckBackgroundJob project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(project);
        tx1.commit();
        session.close();
    }

    public void delete(PriceCheckBackgroundJob project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(project);
        tx1.commit();
        session.close();
    }

    public List<PriceCheckBackgroundJob> findAll() {
        List<PriceCheckBackgroundJob> projects = (List<PriceCheckBackgroundJob>)  HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From PriceCheckBackgroundJob ").list();
        return projects;
    }
}
