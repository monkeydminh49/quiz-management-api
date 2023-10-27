package com.e01.quiz.repository;

import com.e01.quiz.entity.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryRepository  extends JpaRepository<TestHistory, Long> {

}
