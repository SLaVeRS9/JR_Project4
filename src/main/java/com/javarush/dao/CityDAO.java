package com.javarush.dao;

import com.javarush.domain.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getRangeCities(int offset, int limit) {
            Query<City> citiesQuery = sessionFactory.getCurrentSession().createQuery("from City", City.class);
            citiesQuery.setFirstResult(offset);
            citiesQuery.setMaxResults(limit);
            return citiesQuery.list();
    }

    public int getTotalCount() {
            Query<Long> totalCountQuery = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
            return Math.toIntExact(totalCountQuery.uniqueResult());
    }

    public City getById(Integer id) {
        Query<City> cityByIdQuery = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        cityByIdQuery.setParameter("ID", id);
        City cityById = cityByIdQuery.uniqueResult();
        return cityById;
    }
}
