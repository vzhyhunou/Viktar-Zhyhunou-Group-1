package com.epam.darafeyeu.dao;

import com.epam.darafeyeu.domain.Project;
import com.epam.darafeyeu.persistence.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Alex on 08.12.14.
 */
public class ProjectDAOImpl implements ProjectDAO {

    @Override
    public Integer createProject(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Project project = new Project(name);
        Integer result = (Integer) session.save(project);
        session.getTransaction().commit();
        return result;
    }
}
