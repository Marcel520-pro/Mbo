package com.logonedigital.MBOAcare.controller;

import com.logonedigital.MBOAcare.dto.MedicamentReqdto;
import com.logonedigital.MBOAcare.dto.MedicamentResdto;
import com.logonedigital.MBOAcare.entity.Medicament;
import com.logonedigital.MBOAcare.service.medicament.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/medicament")

    public class MedicamentController {
        private final MedicamentService medicamentService;

        public MedicamentController(MedicamentService medicamentService) {
            this.medicamentService = medicamentService;
        }
        @PostMapping(path = "/create")
        public ResponseEntity<String> createMedicament(@Valid @RequestBody MedicamentReqdto medicamentReqdto) {
            this.medicamentService.addMedicament(medicamentReqdto);
            return ResponseEntity.status(201).body("Medicament cree avec succes !");

        }

      @GetMapping(path = "/get_by_id/{idMedicament}")
    public ResponseEntity<MedicamentResdto> getMedicamentById(@PathVariable String idMedicament) {
        return ResponseEntity.status(200)
                .body(this.medicamentService.getMedicamentById(idMedicament));
      }

      @GetMapping(path = "/get_all")
    public ResponseEntity<List<MedicamentResdto>> getMedicament() {
        return ResponseEntity.status(200).body(this.medicamentService.getAllMedicament());
    }
    @PutMapping("/update_by_id/{idMedicament}")
    public ResponseEntity<String> updateMedicament(@Valid @PathVariable String idMedicament, @RequestBody MedicamentReqdto medicamentReqdto) {

        this.medicamentService.updateMedicament(idMedicament, medicamentReqdto);
        return ResponseEntity.status(202).body("Medicament modifie avec succes !");
    }



    @DeleteMapping(path = "delete_by_id/{idMedicament}")
    public ResponseEntity<String> deleteMedicament(@PathVariable String idMedicament) {
        this.medicamentService.deleteMedicament(idMedicament);
        return ResponseEntity.status(202).body("Medicament supprime avec succces!");
    }
    @GetMapping("/pagination")
    public Page<MedicamentResdto> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nom") String sortBy
    ) {
        return medicamentService.getPaginated(page, size, sortBy);
    }
    // Trouver les médicaments par forme
    @GetMapping("/forme/{forme}")
    public List<Medicament> getMedicamentByForme(@PathVariable String forme) {
        return medicamentService.findMedicamentByForme(forme);
    }

    }

