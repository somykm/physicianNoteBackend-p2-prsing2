package com.abernathyclinic.medilabo_physicianNotes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "doctorNotes")
public class PatientHistory {
    @Id
    private String _id;
    private Integer patId;
    private List<String> notes;
}
