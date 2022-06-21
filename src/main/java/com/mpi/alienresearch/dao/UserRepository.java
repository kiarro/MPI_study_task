package com.mpi.alienresearch.dao;

import org.springframework.stereotype.Repository;

import com.mpi.alienresearch.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    User findById(long id);

    @Query(value = "SELECT * FROM users u WHERE u.password = :password AND u.username = :username", nativeQuery=true)
    User findByCredentials(@Param("username") String username, @Param("password") String password);

}
