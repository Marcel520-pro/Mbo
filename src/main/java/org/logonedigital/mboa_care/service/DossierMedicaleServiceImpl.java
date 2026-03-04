package org.logonedigital.mboa_care.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
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

            // Define color scheme: light blue and medical green
            BaseColor lightBlue = new BaseColor(135, 206, 235);
            BaseColor medicalGreen = new BaseColor(34, 139, 34);
            BaseColor darkGreen = new BaseColor(0, 100, 0);
            BaseColor lightGray = new BaseColor(240, 240, 240);
            BaseColor borderColor = new BaseColor(200, 200, 200);

            // Define styled fonts
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
            Font hospitalNameFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, darkGreen);
            Font sectionHeaderFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.WHITE);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            Font infoFont = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.DARK_GRAY);

            // Add administrative header
            addProfessionalHeader(document, lightBlue, medicalGreen, headerFont, hospitalNameFont);

            // Add spacing
            document.add(new Paragraph(" "));

            // Add title
            Paragraph title = new Paragraph("📋 DOSSIER MÉDICAL DU PATIENT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(5);
            document.add(title);
            
            addHorizontalLine(document, darkGreen);
            
            document.add(new Paragraph(" "));

            // Add dossier info
            createPatientInfoTable(document, patient, labelFont, valueFont, lightBlue);

            document.add(new Paragraph(" "));

            // Section: Information du Patient
            addModernSectionHeader(document, "👤 INFORMATIONS PERSONNELLES", lightBlue, sectionHeaderFont);
            createFieldsTable(document, new String[]{"Nom Complet", "Adresse Email", "Téléphone"}, 
                            new String[]{patient.getNom(), patient.getEmail(), patient.getTelephone()}, 
                            lightGray, borderColor, valueFont);
            
            document.add(new Paragraph(" "));

            // Section: Localisation
            addModernSectionHeader(document, "📍 LOCALISATION", medicalGreen, sectionHeaderFont);
            createFieldsTable(document, new String[]{"Ville", "Quartier"},
                            new String[]{patient.getLocation().getVille(), patient.getLocation().getQuartier()},
                            lightGray, borderColor, valueFont);
            
            document.add(new Paragraph(" "));

            // Section: Informations Médicales
            addModernSectionHeader(document, "⚕️ INFORMATIONS MÉDICALES", lightBlue, sectionHeaderFont);
            createFieldsTable(document, new String[]{"Groupe Sanguin", "Allergies", "Antécédents Médicaux"},
                            new String[]{patient.getGroupSanguin(), patient.getAllergies(), patient.getAntecedents()},
                            lightGray, borderColor, valueFont);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            addHorizontalLine(document, darkGreen);
            
            // Add professional footer
            addProfessionalFooter(document, infoFont);

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



    /**
     * Add a professional header with LogoneDigital and MboaCare
     */
    private void addProfessionalHeader(Document document, BaseColor lightBlue, BaseColor medicalGreen, 
                                      Font headerFont, Font hospitalNameFont) throws com.itextpdf.text.DocumentException {
        // Top header bar
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        PdfPCell headerCell = new PdfPCell(new Paragraph("LOGONEDIGITAL", headerFont));
        headerCell.setBackgroundColor(lightBlue);
        headerCell.setPadding(12);
        headerCell.setBorder(0);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.addCell(headerCell);
        headerTable.setSpacingAfter(0);
        document.add(headerTable);

        // Hospital name section
        PdfPTable hospitalTable = new PdfPTable(1);
        hospitalTable.setWidthPercentage(100);
        hospitalTable.setSpacingBefore(0);
        PdfPCell hospitalCell = new PdfPCell(new Paragraph("🏥 CENTRE MÉDICAL - MBOA CARE", hospitalNameFont));
        hospitalCell.setBackgroundColor(medicalGreen);
        hospitalCell.setPadding(12);
        hospitalCell.setBorder(0);
        hospitalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hospitalTable.addCell(hospitalCell);
        document.add(hospitalTable);
    }

    /**
     * Add a modern section header with emoji and background
     */
    private void addModernSectionHeader(Document document, String sectionTitle, BaseColor backgroundColor, Font sectionFont) throws com.itextpdf.text.DocumentException {
        PdfPTable sectionTable = new PdfPTable(1);
        sectionTable.setWidthPercentage(100);
        sectionTable.setSpacingBefore(5);
        sectionTable.setSpacingAfter(5);
        
        PdfPCell sectionCell = new PdfPCell(new Paragraph(sectionTitle, sectionFont));
        sectionCell.setBackgroundColor(backgroundColor);
        sectionCell.setPadding(10);
        sectionCell.setBorder(1);
        sectionCell.setBorderColor(backgroundColor);
        sectionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        sectionTable.addCell(sectionCell);
        document.add(sectionTable);
    }

    /**
     * Add a horizontal line separator
     */
    private void addHorizontalLine(Document document, BaseColor color) throws com.itextpdf.text.DocumentException {
        Paragraph line = new Paragraph("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━", 
                                      new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, color));
        line.setAlignment(Element.ALIGN_CENTER);
        line.setSpacingBefore(8);
        line.setSpacingAfter(8);
        document.add(line);
    }

    /**
     * Create a table with patient info (dossier number and date)
     */
    private void createPatientInfoTable(Document document, Patient patient, Font labelFont, Font valueFont, BaseColor bgColor) throws com.itextpdf.text.DocumentException {
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(5);
        infoTable.setSpacingAfter(5);

        // Dossier number
        PdfPCell dossierLabel = new PdfPCell(new Paragraph("N° Dossier:", labelFont));
        dossierLabel.setBackgroundColor(bgColor);
        dossierLabel.setPadding(8);
        dossierLabel.setBorder(1);
        infoTable.addCell(dossierLabel);

        PdfPCell dossierValue = new PdfPCell(new Paragraph(patient.getNom() != null ? patient.getNom().substring(0, Math.min(8, patient.getNom().length())).toUpperCase() : "N/A", valueFont));
        dossierValue.setPadding(8);
        dossierValue.setBorder(1);
        infoTable.addCell(dossierValue);

        // Date
        PdfPCell dateLabel = new PdfPCell(new Paragraph("Date:", labelFont));
        dateLabel.setBackgroundColor(bgColor);
        dateLabel.setPadding(8);
        dateLabel.setBorder(1);
        infoTable.addCell(dateLabel);

        PdfPCell dateValue = new PdfPCell(new Paragraph(java.time.LocalDate.now().toString(), valueFont));
        dateValue.setPadding(8);
        dateValue.setBorder(1);
        infoTable.addCell(dateValue);

        document.add(infoTable);
    }

    /**
     * Create a table for displaying fields in a professional manner
     */
    private void createFieldsTable(Document document, String[] labels, String[] values, 
                                   BaseColor bgColor, BaseColor borderColor, Font valueFont) throws com.itextpdf.text.DocumentException {
        PdfPTable fieldsTable = new PdfPTable(2);
        fieldsTable.setWidthPercentage(100);
        fieldsTable.setSpacingBefore(5);
        fieldsTable.setSpacingAfter(5);
        fieldsTable.setWidths(new float[]{30, 70});

        for (int i = 0; i < labels.length; i++) {
            // Label cell
            PdfPCell labelCell = new PdfPCell(new Paragraph(labels[i], new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, new BaseColor(60, 120, 180))));
            labelCell.setBackgroundColor(bgColor);
            labelCell.setPadding(8);
            labelCell.setBorder(1);
            labelCell.setBorderColor(borderColor);
            fieldsTable.addCell(labelCell);

            // Value cell
            PdfPCell valueCell = new PdfPCell(new Paragraph(values[i] != null ? values[i] : "N/A", valueFont));
            valueCell.setPadding(8);
            valueCell.setBorder(1);
            valueCell.setBorderColor(borderColor);
            fieldsTable.addCell(valueCell);
        }

        document.add(fieldsTable);
    }

    /**
     * Add a professional footer
     */
    private void addProfessionalFooter(Document document, Font footerFont) throws com.itextpdf.text.DocumentException {
        Paragraph footer1 = new Paragraph("Document confidentiel - Propriété de MboaCare", footerFont);
        footer1.setAlignment(Element.ALIGN_CENTER);
        document.add(footer1);

        Paragraph footer2 = new Paragraph("Généré le: " + java.time.LocalDate.now() + " | Système de Gestion Médicale", footerFont);
        footer2.setAlignment(Element.ALIGN_CENTER);
        footer2.setSpacingBefore(3);
        document.add(footer2);
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

