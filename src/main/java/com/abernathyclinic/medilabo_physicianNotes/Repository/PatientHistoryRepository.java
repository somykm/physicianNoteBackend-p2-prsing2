package com.abernathyclinic.medilabo_physicianNotes.Repository;

import com.abernathyclinic.medilabo_physicianNotes.model.PatientHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientHistoryRepository extends MongoRepository<PatientHistory, String> {
    List<PatientHistory> findByPatId(Integer patId);
}
