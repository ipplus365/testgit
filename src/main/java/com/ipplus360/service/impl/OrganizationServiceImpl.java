package com.ipplus360.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.OrganizationDao;
import com.ipplus360.entity.Organization;
import com.ipplus360.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;
	
	@Override
	public Organization getById(Long id) {
		return organizationDao.getById(id);
	}

	@Override
	public Organization getByToken(String token) {
		return organizationDao.getByToken(token);
	}


	@Override
	public List<Organization> getByUserId(Long userId) {
		return organizationDao.getByUserId(userId);
	}

	@Override
	public Organization getByOrgName(String orgName) {
		return organizationDao.getByOrgName(orgName);
	}

	@Override
	public int insert(Organization entity) {
		return organizationDao.insert(entity);
	}

	@Override
	public Organization getOneByeUserId(Long userId) {
		return organizationDao.getOneByeUserId(userId);
	}

	@Override
	public int deleteOrgByUserId(Long id) {
		return organizationDao.deleteOrgByUserId(id);
	}

}
