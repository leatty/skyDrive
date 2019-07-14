package xin.trooper.storage.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.trooper.storage.dao.TestDao;
import xin.trooper.storage.domain.Test;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public void save(Test test) {
		System.out.println("Dao中的save方法执行了");
		System.out.println(test.getId());
		System.out.println(test.getName());

		/*
		 * Configuration configuration = new Configuration().configure(); SessionFactory
		 * sessionFactory = configuration.buildSessionFactory(); Session session =
		 * sessionFactory.openSession(); Transaction transaction =
		 * session.beginTransaction();
		 * 
		 * session.save(test);
		 * 
		 * transaction.commit(); session.close();
		 */
		
		this.getHibernateTemplate().save(test);
	}

}
