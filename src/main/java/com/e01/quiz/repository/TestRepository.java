package com.e01.quiz.repository;

import com.e01.quiz.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {
}

