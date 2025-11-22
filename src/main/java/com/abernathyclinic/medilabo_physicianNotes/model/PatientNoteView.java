package com.abernathyclinic.medilabo_physicianNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientNoteView {
        private Integer patId;
        private String fullName;
        private List<String> notes;

}
