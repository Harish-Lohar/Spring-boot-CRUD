package com.crud.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.dto.CrudDto;
import com.crud.model.Users;

public interface CrudRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUserId(Long userId);

	Users findByFirstName(String firstName);

	Users getByUserId(Long userId);

	Users findByLastName(String lastName);

	Optional<Users> findByEmail(String email);

	Optional<Users> findByContact(Long contact);

	Optional<Users> findByUserName(String userName);



}
