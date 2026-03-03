package org.logonedigital.mboa_care.service;

import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import org.logonedigital.mboa_care.dto.DossierMedicaleDto;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.HistoriqueRepo;
import org.logonedigital.mboa_care.repository.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;


@Service
public class DossierMedicaleServiceImpl implements DossierMedicaleService {
    private final PatientService patientService;
    private final PatientRepo  patientRepo;
    private final HistoriqueRepo  historiqueRepo;
    private final DossierMedicaleService dossierMedicaleService;


    public DossierMedicaleServiceImpl(PatientService patientService, PatientRepo patientRepo, HistoriqueRepo historiqueRepo, DossierMedicaleService dossierMedicaleService) {
        this.patientService = patientService;
        this.patientRepo = patientRepo;
        this.historiqueRepo = historiqueRepo;
        this.dossierMedicaleService = dossierMedicaleService;
    }

    @Override
    public byte[] genererDossierMedicale(String idUtilisateur) {
        Patient patient = patientRepo.findById(idUtilisateur)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient Introuvable"));

        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 18);

            document.add(new Paragraph("DOSSIER MEDICAL DU PATIENT", titleFont));
            document.add(new Paragraph("Nom: " + patient.getNom(), normalFont));
            document.add(new Paragraph("Email: " + patient.getEmail(), normalFont));
            document.add(new Paragraph("Telephone: " + patient.getTelephone(), normalFont));
            document.add(new Paragraph("Ville: " + patient.getLocation().getVille(), normalFont));
            document.add(new Paragraph("Quartier: " + patient.getLocation().getQuartier(), normalFont));
            document.add(new Paragraph("Groupe Sanguin: " + patient.getGroupSanguin(), normalFont));
            document.add(new Paragraph("Allergies: " + patient.getAllergies(), normalFont));
            document.add(new Paragraph("Antecedents: " + patient.getAntecedents(), normalFont));

            document.close();

            return baos.toByteArray();
        }
        catch (Exception e) {
            throw new RuntimeException("Erreur generer dossierMedicale", e);
        }
    }

    @Override
    public Page<DossierMedicaleDto> historiqueTelechargements(int page, int size) {
        return historiqueRepo.findAll(PageRequest.of(page, size))
                .map(h -> DossierMedicaleDto.builder()
                        .idPatient(h.getIdPatient())
                        .nom(h.getNom())
                        .email(h.getEmail())
                        .telephone(h.getTelephone())
                        .groupSanguin(h.getGroupSanguin())
                        .antecedents(h.getAntecedents())
                        .dateCreation(LocalDate.now())
                        .build());
    }

    @Override
    public byte[] genererQrCode(String idUtilisateur) {
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            var bitMatrix = qrCodeWriter.encode(
                    "https://localhost:8080/api/v1/dossier" + idUtilisateur,
                    BarcodeFormat
            )
        }
        catch (Exception e){
            throw new ResourceNotFoundException("QR code generation failed");
        }
    }
}
