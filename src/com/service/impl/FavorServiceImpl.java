package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Favor;
import com.beans.User;
import com.dao.IFavorDao;
import com.service.IFavorService;

/**
 * 收藏夹信息业务的实现类
 * @author 杨鑫荣
 *
 */
@Service("favorService")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class FavorServiceImpl implements IFavorService{

	@Resource(name="favorDao")
	private IFavorDao favorDao;
	
	@Override
	public List<Favor> findAllFavorList(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return favorDao.findAll(dCriteria);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void saveFavor(Favor favor) {
		// TODO Auto-generated method stub
		favorDao.save(favor);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cancelFavor(Favor favor) {
		// TODO Auto-generated method stub
		favorDao.delete(favor);
	}

	@Override
	public boolean isFavor(User user, Integer bookId) {
		// TODO Auto-generated method stub
		return favorDao.isFavor(user, bookId);
	}

}
