package com.toilabs.health.repository;

import com.toilabs.health.dto.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    // ✅ Get the latest (most recent) record for each device
    @Query(value = """
        SELECT * FROM (
            SELECT *, ROW_NUMBER() OVER (PARTITION BY device_id ORDER BY timestamp DESC) AS rn
            FROM sensor_data
        ) sub
        WHERE rn = 1
    """, nativeQuery = true)
    List<SensorData> findLatestPerDevice();
}
