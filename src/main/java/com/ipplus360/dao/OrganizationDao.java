package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.Organization;

public interface OrganizationDao {
	
	Organization getById(Long id);

	Organization getByToken(String token);
	
	List<Organization> getByUserId(Long userId);
	
	Organization getByOrgName(String orgName);
	
	int insert(Organization organization);
	
	int update(Organization organization);

    Organization getOneByeUserId(Long userId);

	int deleteOrgByUserId(Long id);
}
