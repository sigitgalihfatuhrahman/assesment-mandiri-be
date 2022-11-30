package com.bankmandiri.assesment.repositories;

import com.bankmandiri.assesment.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT NOW(3)", nativeQuery = true)
	Timestamp getSysDate();

    @Query(
            value = "SELECT * FROM users u WHERE (:userName IS NULL OR u.user_name LIKE :userName OR u.first_name LIKE :firstName)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM users WHERE (:userName IS NULL OR u.user_name LIKE :userName)",
            nativeQuery = true
    )
    Page<Users> findAllUsers(String userName, String firstName, Pageable pageable);

}
