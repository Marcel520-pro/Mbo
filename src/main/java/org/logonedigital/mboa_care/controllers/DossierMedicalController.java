package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.DossierMedicaleDto;
import org.logonedigital.mboa_care.service.DossierMedicaleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dossier")
public class DossierMedicalController {
    private final DossierMedicaleService  dossierMedicaleService;

    public DossierMedicalController(DossierMedicaleService dossierMedicaleService) {
        this.dossierMedicaleService = dossierMedicaleService;
    }

    @GetMapping(path = "/get_dossier_medical{idUtilisateur}")
    public ResponseEntity<byte[]> telechargerDossier(@PathVariable String idUtilisateur) {
        byte[]  pdfBytes = this.dossierMedicaleService.genererDossierMedicale(idUtilisateur);
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename = dossier_medicale_" + idUtilisateur +".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping(path = "/get_qrcode{idUtilisateur}")
    public ResponseEntity<byte[]> getQRcode(@PathVariable String idUtilisateur) {
        byte[] qrCodeBytes = this.dossierMedicaleService.genererQrCode(idUtilisateur);

        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename = dossier_medicale_" + idUtilisateur +".png")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCodeBytes);
    }

    @GetMapping(path = "historique_telechargements")
    public ResponseEntity<Page<DossierMedicaleDto>> historique(
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<DossierMedicaleDto> historiquePages = this.dossierMedicaleService.historiqueTelechargements(page, size);
        return ResponseEntity.status(200).body(historiquePages);
    }
}
