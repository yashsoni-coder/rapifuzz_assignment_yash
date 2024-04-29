package com.rapifuzz.assignment.controller;

import com.rapifuzz.assignment.entity.Incident;
import com.rapifuzz.assignment.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping("/create")
    public ResponseEntity createIncident(@RequestBody Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);
        return ResponseEntity.ok(createdIncident);
    }

    @GetMapping("/{id}")
    public ResponseEntity getIncidentById(@PathVariable Long id) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incident);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateIncident(@PathVariable Long id, @RequestBody Incident incidentDetails) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident == null) {
            return ResponseEntity.notFound().build();
        }
        incident.setUserName(incidentDetails.getUserName());
        incident.setUserEmail(incidentDetails.getUserEmail());
        incident.setUserPhoneNumber(incidentDetails.getUserPhoneNumber());
        incident.setUserAddress(incidentDetails.getUserAddress());
        incident.setPinCode(incidentDetails.getPinCode());
        incident.setCity(incidentDetails.getCity());
        incident.setCountry(incidentDetails.getCountry());
        Incident updatedIncident = incidentService.updateIncident(incident);
        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIncident(@PathVariable Long id) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident == null) {
            return ResponseEntity.notFound().build();
        }
        incidentService.deleteIncident(id);
        return ResponseEntity.ok().build();
    }
}
