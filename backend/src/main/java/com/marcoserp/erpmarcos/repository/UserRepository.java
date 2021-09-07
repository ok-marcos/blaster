package com.marcoserp.erpmarcos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcoserp.erpmarcos.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
