package com.abernathyclinic.medilabo_physicianNotes.service;

import com.abernathyclinic.medilabo_physicianNotes.Repository.PatientHistoryRepository;
import com.abernathyclinic.medilabo_physicianNotes.model.PatientHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class PatientHistoryService {
    private final PatientHistoryRepository patientHistoryRepository;

    @Autowired
    public PatientHistoryService(PatientHistoryRepository patientHistoryRepository) {
        this.patientHistoryRepository = patientHistoryRepository;
    }

    public List<PatientHistory> getPatientsMedicalHistory() {
        log.info("Fetched from repository: {}", patientHistoryRepository.findAll());
        return patientHistoryRepository.findAll();
    }

    public void addNote(PatientHistory patientHistory) {
        patientHistoryRepository.save(patientHistory);
    }
}
