package com.pharmacie.mboacare.controller;

import com.pharmacie.mboacare.dto.PharmaciReqdto;
import com.pharmacie.mboacare.dto.PharmaciResdto;
import com.pharmacie.mboacare.service.pharmacie.PharmaciService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmaci")


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
    public ResponseEntity<List<PharmaciResdto>> getPharmaci(){
        return ResponseEntity.status(200).body(this.pharmaciService.getAllPharmaci());
    }
    @PutMapping("/update_by_id/{idPharmaci}")
    public ResponseEntity<String> updatePharmaci(@Valid @PathVariable String idPharmaci,@RequestBody PharmaciReqdto pharmaciReqdto){
        this.pharmaciService.updatePharmaci(idPharmaci,new PharmaciReqdto());
        return ResponseEntity.status(202).body("Pharmacie modifiee avec succes !");
    }
    @DeleteMapping(path = "delete_by_id/{idPharmaci}")
    public ResponseEntity<String> deletePharmaci(@PathVariable String idPharmaci){
        this.pharmaciService.deletePharmaci(idPharmaci);
        return ResponseEntity.status(202).body("Pharmacie supprime avec succces!");
    }

}


