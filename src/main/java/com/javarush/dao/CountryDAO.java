package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
            Query<Country> countries = sessionFactory.getCurrentSession()
                    .createQuery("select c from Country c join fetch c.languages", Country.class);
            return countries.list();
    }

    public int getTotalCount() {
            Query<Integer> totalCountQuery = sessionFactory.getCurrentSession()
                    .createQuery("select count(Country) from Country", Integer.class);
            return totalCountQuery.uniqueResult();
    }
}
