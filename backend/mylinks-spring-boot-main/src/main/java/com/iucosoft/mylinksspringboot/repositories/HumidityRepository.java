package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Humidity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {

    @Query("Select h FROM Humidity h WHERE h.deviceId = :deviceId")
    Page<Humidity> getHumidityByDeviceId(final @Param("deviceId") Long deviceId, Pageable pageable);

}
