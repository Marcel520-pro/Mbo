package com.pharmacie.mboacare.service.pharmacie;

import com.pharmacie.mboacare.dto.PharmaciReqdto;
import com.pharmacie.mboacare.dto.PharmaciResdto;
import com.pharmacie.mboacare.entity.Pharmaci;
import com.pharmacie.mboacare.exception.ResourceExistException;
import com.pharmacie.mboacare.exception.ResourceNotFoundException;
import com.pharmacie.mboacare.repository.PharmaciRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class PharmaciServiceImpl implements PharmaciService {

    private final PharmaciRepo pharmaciRepo;


    public PharmaciServiceImpl(PharmaciRepo pharmaciRepo, PharmaciRepo pharmaciRepo1) {
        this.pharmaciRepo = pharmaciRepo;
    }

    @Override
    public void addPharmaci(PharmaciReqdto pharmaciReqdto) {
        Optional<Pharmaci> pharmaciFound = this.pharmaciRepo.findByEmail(pharmaciReqdto.getEmail());
        if(pharmaciFound.isPresent())
            throw new ResourceExistException("Cette pharmacie existe deja");

        Pharmaci pharmaci= new Pharmaci(pharmaciReqdto.getNom(),pharmaciReqdto.getEmail(),pharmaciReqdto.getVille(),pharmaciReqdto.getQuartier(),pharmaciReqdto.getContact());
        pharmaci.setDateCreation(LocalDate.now());
        this.pharmaciRepo.save(pharmaci);


    }

    @Override
    public PharmaciResdto getPharmaciById(String idPharmaci) {

        Pharmaci pharmaci = this.pharmaciRepo.findById(idPharmaci).orElseThrow(()-> new ResourceNotFoundException("cette pharmacie n existe pas"));
        return new PharmaciResdto(pharmaci.getIdPharmaci(),pharmaci.getNom(),pharmaci.getVille(),pharmaci.getQuartier(),pharmaci.getEmail(),pharmaci.getContact());
    }

    @Override
    public List<PharmaciResdto> getAllPharmaci() {
        return this.pharmaciRepo.findAll().stream().map(pharmaci -> {

            return new PharmaciResdto(pharmaci.getIdPharmaci(),pharmaci.getNom(),pharmaci.getVille(),pharmaci.getQuartier(),pharmaci.getEmail(),pharmaci.getContact());
        }).toList();
    }

    @Override
    public void updatePharmaci(String idPharmaci, PharmaciReqdto pharmaciReqdto) {
        Pharmaci oldPharmaci = this.pharmaciRepo.findById(idPharmaci)
                .orElseThrow(() -> new ResourceNotFoundException("cette pharmacie n existe pas"));

        if (pharmaciReqdto.getEmail() == null || pharmaciReqdto.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le champ 'email' est obligatoire !");
        }
        oldPharmaci.setNom(pharmaciReqdto.getNom());
        oldPharmaci.setVille(pharmaciReqdto.getVille());
        oldPharmaci.setQuartier(pharmaciReqdto.getQuartier());
        oldPharmaci.setEmail(pharmaciReqdto.getEmail());

        this.pharmaciRepo.saveAndFlush(oldPharmaci);

    }

    @Override
    public void deletePharmaci(String idPharmaci) {
        Pharmaci pharmaci = this.pharmaciRepo.findById(idPharmaci).orElseThrow(()-> new ResourceNotFoundException("Cette pharmacie  n existe pas !"));

        this.pharmaciRepo.delete(pharmaci);

    }
}

        


