package com.example.api.Repository;

import com.example.api.Models.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INumberRepository extends JpaRepository<NumberEntity, Long> {
}
