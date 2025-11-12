package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Heat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeatRepository extends JpaRepository<Heat, Long> {

    @Query("Select h FROM Heat h WHERE h.deviceId = :deviceId")
    Page<Heat> getHeatByDeviceId(final @Param("deviceId") Long deviceId, Pageable pageable);

}