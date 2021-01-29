package com.hse.organizer.repository;

import com.hse.organizer.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    Drug getById(Long id);

    Drug findByName(String name);
}
