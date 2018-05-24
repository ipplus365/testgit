package com.ipplus360.service;

import java.util.List;

import com.ipplus360.entity.Organization;

public interface OrganizationService {
	
	Organization getById(Long id);

	Organization getByToken(String token);
	
	List<Organization> getByUserId(Long userId);
	
	Organization getByOrgName(String orgName);
	
	int insert(Organization entity);

    Organization getOneByeUserId(Long userId);

	int deleteOrgByUserId(Long id);
}
