package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarID(long id) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u where u.car.id = :id", User.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Override
    public User getUserByCarSeries(int series) {
        return sessionFactory.getCurrentSession().createQuery("select u from User u where u.car.series = :series", User.class).setParameter("series", series).uniqueResult();
    }

}
