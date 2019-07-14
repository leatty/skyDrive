package xin.trooper.storage.service.impl;

import org.springframework.transaction.annotation.Transactional;

import xin.trooper.storage.dao.TestDao;
import xin.trooper.storage.domain.Test;
import xin.trooper.storage.service.TestService;

@Transactional
public class TestServiceImpl implements TestService {

	private TestDao testDao;
	
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	
	@Override
	public void save(Test test) {
		System.out.println("Service中的save方法执行了");
		testDao.save(test);
	}

	

}
