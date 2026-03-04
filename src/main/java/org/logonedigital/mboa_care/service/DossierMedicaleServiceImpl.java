package org.logonedigital.mboa_care.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.logonedigital.mboa_care.dto.DossierMedicaleDto;
import org.logonedigital.mboa_care.entity.HistoriqueDossier;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.HistoriqueRepo;
import org.logonedigital.mboa_care.repository.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;


@Service
public class DossierMedicaleServiceImpl implements DossierMedicaleService {
    private final PatientRepo  patientRepo;
    private final HistoriqueRepo  historiqueRepo;


    public DossierMedicaleServiceImpl(PatientRepo patientRepo, HistoriqueRepo historiqueRepo) {
        this.patientRepo = patientRepo;
        this.historiqueRepo = historiqueRepo;
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
            
            // Save to history
            HistoriqueDossier history = HistoriqueDossier.builder()
                    .idPatient(idUtilisateur)
                    .nom(patient.getNom())
                    .email(patient.getEmail())
                    .telephone(patient.getTelephone())
                    .groupSanguin(patient.getGroupSanguin())
                    .antecedents(patient.getAntecedents())
                    .dateTelechargement(LocalDate.now())
                    .build();
            historiqueRepo.save(history);

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
                        .dateCreation(h.getDateTelechargement())
                        .build());
    }

    @Override
    public byte[] genererQrCode(String idUtilisateur) {
        try {
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/v1/dossier")
                    .path(idUtilisateur)
                    .toUriString();

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            var bitMatrix = qrCodeWriter.encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    300,
                    300
            );
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
            return baos.toByteArray();
        }
        catch (WriterException e) {
            throw new RuntimeException("QR code generation failed", e);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("QR code generation failed");
        }
    }
}

