package com.rapifuzz.assignment.service;

import com.rapifuzz.assignment.entity.Incident;
import com.rapifuzz.assignment.exception.NotFoundException;
import com.rapifuzz.assignment.repo.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public Incident createIncident(Incident incident) {
        incident.setIncidentId(generateUniqueIncidentId());
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    private String generateUniqueIncidentId() {
        String incidentId;
        do {
            incidentId = generateIncidentId();
        } while (!isIncidentIdUnique(incidentId));
        return incidentId;
    }

    private boolean isIncidentIdUnique(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId) == null;
    }

    private String generateIncidentId() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000; // Generate 5-digit random number
        return "RMG" + randomNumber + "2022";
    }

    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incident not found with id: " + id));
    }

    public void deleteIncident(Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new NotFoundException("Incident not found with id: " + id);
        }
        incidentRepository.deleteById(id);
    }

}
