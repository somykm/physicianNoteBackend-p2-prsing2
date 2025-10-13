package com.abernathyclinic.medilabo_physicianNotes.service;

import com.abernathyclinic.medilabo_physicianNotes.model.Patient;
import com.abernathyclinic.medilabo_physicianNotes.model.PatientHistory;
import com.abernathyclinic.medilabo_physicianNotes.model.PatientNoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientHistoryNameViewService {
    private final PatientHistoryService patientHistoryService;
    private  final RestTemplate restTemplate;
    private final String patientServiceUrl ="http://localhost:8081/api/patient";

    @Autowired
    public PatientHistoryNameViewService(PatientHistoryService patientHistoryService, RestTemplate restTemplate) {
        this.patientHistoryService = patientHistoryService;
        this.restTemplate = restTemplate;
    }

    public List<PatientNoteView> getPatientFullHistory(){
        List<PatientHistory> doctorNotes= patientHistoryService.getPatientsMedicalHistory();

        return doctorNotes.stream().map(history ->{
            PatientNoteView view=new PatientNoteView();
            view.setPatId(history.getPatId());
            view.setNotes(history.getNotes());

            try {
                Patient patient = restTemplate.getForObject(
                        patientServiceUrl + "/" + history.getPatId(), Patient.class);
                if (patient != null) {
                    view.setFullName(patient.getFirstName() + " " + patient.getLastName());
                } else {
                    view.setFullName("Unknown");
                }

            }catch (Exception e) {
                view.setFullName("Unavailable");
            }
            return view;
        }).collect(Collectors.toList());

    }
}
