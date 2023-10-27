package com.e01.quiz.repository;

import com.e01.quiz.entity.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestHistoryRepository  extends JpaRepository<TestHistory, Long> {

    @Query("select th from TestHistory th where th.testId = ?1")
    List<TestHistory> findAllByTestId(Long id);
}
