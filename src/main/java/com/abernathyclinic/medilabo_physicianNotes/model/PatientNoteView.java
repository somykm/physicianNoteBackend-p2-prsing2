package com.abernathyclinic.medilabo_physicianNotes.model;

import lombok.Data;

import java.util.List;

@Data
public class PatientNoteView {
        private Integer patId;
        private String fullName;
        private List<String> notes;
}
