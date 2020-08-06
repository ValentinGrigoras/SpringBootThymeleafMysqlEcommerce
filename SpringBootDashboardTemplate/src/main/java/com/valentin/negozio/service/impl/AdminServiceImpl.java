package com.valentin.negozio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.negozio.model.Admin;
import com.valentin.negozio.repository.AdminRepository;
import com.valentin.negozio.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminRepository adminRepository;

	@Override
	public Optional<Admin> findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public Optional<Admin> controlloLogin(String user, String pass) {
		return adminRepository.controlloLogin(user, pass);
	}

}
