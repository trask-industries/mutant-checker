package com.traskindustries.repository;

import com.traskindustries.model.CheckedDNA;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface CheckedDNARepository extends Repository<CheckedDNA, Long> {

    CheckedDNA save(CheckedDNA checkedDNA);

    Long countByResultIsFalse();

    Long countByResultIsTrue();

    void deleteAll();
}
