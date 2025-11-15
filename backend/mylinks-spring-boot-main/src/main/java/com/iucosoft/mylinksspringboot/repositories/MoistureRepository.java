package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Moisture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoistureRepository extends JpaRepository<Moisture, Long> {

    @Query("Select mo FROM Humidity mo WHERE mo.deviceId = :deviceId")
    Page<Moisture> getMoistureByDeviceId(final @Param("deviceId") Long deviceId, Pageable pageable);

}
