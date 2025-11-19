package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Co2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Co2Repository extends JpaRepository<Co2, Long>{

    @Query("Select co FROM Co2 co WHERE co.deviceId = :deviceId")
    Page<Co2> getCo2ByDeviceId(final @Param("deviceId") Long deviceId, Pageable pageable);

}
