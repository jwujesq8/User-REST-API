package com.test.api.repository;


import com.test.api.dto.response.UserResponseDto;
import com.test.api.user.Gender;
import com.test.api.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Hidden
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findByGender(Gender gender);

    Optional<User> findByLogin(String login);

    boolean existsByLoginAndPasswordIgnoreCase(String login, String password);

    boolean existsById(Long id);

    @Transactional
    @Query(
            value = "SELECT * FROM user u WHERE u.id>=?1 AND u.id<=?2;",
            nativeQuery = true
    )
    List<User> userWhichIdInRange(Long startId, Long endId);

    @Transactional
    @Query(
            value = "SELECT * FROM user u WHERE u.id>=?1;",
            nativeQuery = true
    )
    List<User> userWhichIdFrom(Long startId);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM user u WHERE u.id>=?1 AND u.id<=?2;",
            nativeQuery = true
    )
    void deleteListOfUsersByStartAndEndId(Long startId, Long endId);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM user u WHERE u.id>=?1",
            nativeQuery = true
    )
    void deleteListOfUsersByStartIdAsc(Long startId);


}




//    @Transactional
//    @Query(
//            value = "SELECT COUNT(*) FROM user u WHERE u.id>=?1 AND u.id<=?2;",
//            nativeQuery = true
//    )
//    Long idCountInRange(Long startId, Long endId);
//
//    @Transactional
//    @Query(
//            value = "SELECT COUNT(*) FROM user u WHERE u.id>=?1;",
//            nativeQuery = true
//    )
//    Long idCountFrom(Long startId);