package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.Unit;
import com.epam.darafeyeu.persistence.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Alex on 08.12.14.
 */
public class UnitDAOImpl  implements UnitDAO{

    @Override
    public Integer createUnit(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Unit unit = new Unit(name);
        Integer result = (Integer) session.save(unit);
        session.getTransaction().commit();
        return result;
    }
}
