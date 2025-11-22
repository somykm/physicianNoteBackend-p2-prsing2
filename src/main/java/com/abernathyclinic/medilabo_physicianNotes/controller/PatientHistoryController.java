package com.abernathyclinic.medilabo_physicianNotes.controller;

import com.abernathyclinic.medilabo_physicianNotes.model.PatientHistory;
import com.abernathyclinic.medilabo_physicianNotes.model.PatientNoteView;
import com.abernathyclinic.medilabo_physicianNotes.service.PatientHistoryNameViewService;
import com.abernathyclinic.medilabo_physicianNotes.service.PatientHistoryService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/history")
public class PatientHistoryController {

    private final PatientHistoryService patientHistoryService;
    private PatientHistoryNameViewService viewService;

    @Autowired
    public PatientHistoryController(PatientHistoryService patientHistoryService, PatientHistoryNameViewService viewService) {
        this.patientHistoryService = patientHistoryService;
        this.viewService = viewService;
    }

    //Get all notes as JSON
    @GetMapping("/all")
    public List<PatientHistory> getAllNotes() {
        log.info("Fetching all patient history notes...");
        return patientHistoryService.getPatientsMedicalHistory();
    }

    //API: Add a new note
    @PostMapping
    public ResponseEntity<String> addNote(@RequestBody PatientHistory patientHistory) {
        log.info("Adding note for patient ID {}: {}", patientHistory.getPatId(), patientHistory.getNotes());
        patientHistoryService.addNote(patientHistory);
        return ResponseEntity.ok("Note added successfully.");
    }

    //UI: View all notes with patient names (Thymeleaf view)
    @GetMapping("/")
    public ModelAndView viewAllNotesPage() {
        log.info("Loading full patient history view...");
        List<PatientNoteView> enrichedNotes = viewService.getPatientFullHistory();
        log.info("Retrieved {} enriched notes", enrichedNotes.size());

        ModelAndView mav = new ModelAndView("history");
        mav.addObject("notes", enrichedNotes);
        mav.addObject("note", new PatientHistory());
        return mav;
    }

    //UI: View notes for a specific patient
    @GetMapping("/add/{patId}")
    public ModelAndView viewNotesForPatient(@PathVariable Integer patId, Model model) {
        log.info("Fetching history for patient ID: {}", patId);
        List<PatientHistory> allNotes = patientHistoryService.getPatientsMedicalHistory();
        List<PatientHistory> filtered = allNotes.stream().filter(n -> n.getPatId().equals(patId)).collect(Collectors.toList());
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("notes", filtered);
        mav.addObject("note", new PatientHistory());
        return mav;
    }

}