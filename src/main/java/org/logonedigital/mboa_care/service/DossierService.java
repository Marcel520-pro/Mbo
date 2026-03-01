package org.logonedigital.mboa_care.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.logonedigital.mboa_care.entity.ActivityLog;
import org.logonedigital.mboa_care.entity.DossierHistory;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.ActivityLogRepo;
import org.logonedigital.mboa_care.repository.DossierHistoryRepo;
import org.logonedigital.mboa_care.repository.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class DossierService {

    private final PatientRepo patientRepository;
    private final DossierHistoryRepo historyRepository;
    private final ActivityLogRepo activityLogRepository;

    public DossierService(PatientRepo patientRepository,
                         DossierHistoryRepo historyRepository,
                         ActivityLogRepo activityLogRepository) {
        this.patientRepository = patientRepository;
        this.historyRepository = historyRepository;
        this.activityLogRepository = activityLogRepository;
    }

    /**
     * Génère un PDF contenant le dossier médical d'un patient
     */
    public byte[] generateDossierPdf(String idPatient, String username) throws Exception {
        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec l'ID: " + idPatient));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();

        // Titre
        document.add(new Paragraph("DOSSIER MEDICAL"));
        document.add(new Paragraph(" "));

        // Informations personnelles
        document.add(new Paragraph("Nom: " + patient.getNomUtilisateur()));
        document.add(new Paragraph("Email: " + patient.getEmail()));
        document.add(new Paragraph("Téléphone: " + patient.getTelephone()));
        document.add(new Paragraph(" "));

        // Informations médicales
        document.add(new Paragraph("Groupe Sanguin: " + (patient.getGroupSanguin() != null ? patient.getGroupSanguin() : "Non renseigné")));
        document.add(new Paragraph("Antécédents: " + (patient.getAntecedents() != null ? patient.getAntecedents() : "Aucun")));
        document.add(new Paragraph("Allergies: " + (patient.getAllergies() != null ? patient.getAllergies() : "Aucune")));
        document.add(new Paragraph("Traitements: " + (patient.getTraitement() != null ? patient.getTraitement() : "Aucun")));

        document.close();

        // Enregistrer dans l'historique
        DossierHistory history = DossierHistory.builder()
                .action("DOWNLOAD_DOSSIER")
                .dateAction(LocalDateTime.now())
                .username(username)
                .patient(patient)
                .build();
        historyRepository.save(history);

        // Enregistrer dans les logs d'activité
        logAction("DOWNLOAD_DOSSIER", username);

        return out.toByteArray();
    }

    /**
     * Récupère l'historique des dossiers d'un patient avec pagination
     */
    public Page<DossierHistory> getPatientDossierHistory(String idPatient, int page, int size) {
        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec l'ID: " + idPatient));

        // Log de la consultation
        logAction("CONSULT_HISTORIQUE", "USER");

        return historyRepository.findByPatient(patient, PageRequest.of(page, size));
    }

    /**
     * Enregistre une action dans les logs d'activité globaux
     */
    public void logAction(String action, String username) {
        ActivityLog log = ActivityLog.builder()
                .action(action)
                .username(username)
                .dateAction(LocalDateTime.now())
                .build();
        activityLogRepository.save(log);
    }
}