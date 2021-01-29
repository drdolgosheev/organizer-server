package com.hse.organizer.repository;

import com.hse.organizer.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Drug}.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Drug getById(Long id);

    Drug findByName(String name);
}
