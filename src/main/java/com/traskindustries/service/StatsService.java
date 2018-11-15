package com.traskindustries.service;

import com.traskindustries.messages.GetDNAStatsResponse;
import com.traskindustries.repository.CheckedDNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private CheckedDNARepository checkedDNARepository;

    public GetDNAStatsResponse getDNAStats() {
        final long mutants = checkedDNARepository.countByResultIsTrue();
        final long humans = checkedDNARepository.countByResultIsFalse();
        return new GetDNAStatsResponse
                .Builder()
                .mutants(mutants)
                .humans(humans)
                .build();
    }
}
