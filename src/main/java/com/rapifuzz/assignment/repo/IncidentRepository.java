package com.rapifuzz.assignment.repo;

import com.rapifuzz.assignment.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

Incident findByIncidentId(String incidentId);
}

