package com.e01.quiz.repository;

import com.e01.quiz.entity.Test;
import com.e01.quiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {

    Optional<Test> findByCode(String code);
    @Query("SELECT t FROM Test t JOIN FETCH t.questions WHERE t.user = :user")
    List<Test> getTestsForUser(@Param("user") User user);
}

