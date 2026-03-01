package com.logonedigital.MBOAcare.controller;

import com.logonedigital.MBOAcare.dto.PharmaciReqdto;
import com.logonedigital.MBOAcare.dto.PharmaciResdto;
import com.logonedigital.MBOAcare.entity.Pharmaci;
import com.logonedigital.MBOAcare.service.pharmaci.PharmaciService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmaci")
//test commit

public class PharmaciController {
    private final PharmaciService pharmaciService;

    public PharmaciController(PharmaciService pharmaciService) {
        this.pharmaciService = pharmaciService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createPharmaci(@Valid @RequestBody PharmaciReqdto pharmaciReqdto) {
        this.pharmaciService.addPharmaci(pharmaciReqdto);
        return ResponseEntity.status(201).body("Pharmacie cree avec succes !");
    }

    @GetMapping(path = "/get_by_id/{idPharmaci}")
    public ResponseEntity<PharmaciResdto> getPharmaciById(@PathVariable String idPharmaci) {
        return ResponseEntity.status(200)
                .body(this.pharmaciService.getPharmaciById(idPharmaci));
    }

    @GetMapping(path = "/get_all")
    public ResponseEntity<List<PharmaciResdto>> getPharmaci() {
        return ResponseEntity.status(200).body(this.pharmaciService.getAllPharmaci());
    }

    @PutMapping("/update_by_id/{idPharmaci}")
    public ResponseEntity<String> updatePharmaci(
            @PathVariable String idPharmaci,
            @RequestBody @Valid PharmaciReqdto pharmaciReqdto) {

        this.pharmaciService.updatePharmaci(idPharmaci, pharmaciReqdto);
        return ResponseEntity.status(202).body("Pharmacie modifiee avec succes !");
    }

    @DeleteMapping(path = "delete_by_id/{idPharmaci}")
    public ResponseEntity<String> deletePharmaci(@PathVariable String idPharmaci) {
        this.pharmaciService.deletePharmaci(idPharmaci);
        return ResponseEntity.status(202).body("Pharmacie supprime avec succces!");
    }

    @GetMapping("/pagination")
    public Page<PharmaciResdto> getPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction
    ) {
        return pharmaciService.getPaginated(page, size, sortBy, direction);
    }
    //  Recherche pharmacie par nom de médicament
    @GetMapping("/medicament/{nom}")
    public List<Pharmaci> getPharmaciByMedicament(@PathVariable String nom) {
        return pharmaciService.findPharmaciByMedicamentNom(nom);
    }
    //   nombre de médicaments par pharmacie
    @GetMapping("/stats/medicaments-par-pharmacie")
    public List<Object[]> medicamentParPharmaci() {
        return pharmaciService.countMedicamentParPharmaci();
    }
}
