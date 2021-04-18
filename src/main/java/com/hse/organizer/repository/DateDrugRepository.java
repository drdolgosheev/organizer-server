package com.hse.organizer.repository;

import com.hse.organizer.model.DateDrugs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateDrugRepository extends JpaRepository<DateDrugs, Long> {
    DateDrugs getById(Long id);

    DateDrugs getByDrugId(Long id);

    List<DateDrugs> getAllByDrugId(Long id);
}
