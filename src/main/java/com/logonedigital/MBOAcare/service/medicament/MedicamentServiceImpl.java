package com.logonedigital.MBOAcare.service.medicament;

import com.logonedigital.MBOAcare.Exception.ResourceExistException;
import com.logonedigital.MBOAcare.Exception.ResourceNotFoundException;
import com.logonedigital.MBOAcare.dto.MedicamentReqdto;
import com.logonedigital.MBOAcare.dto.MedicamentResdto;
import com.logonedigital.MBOAcare.entity.Medicament;
import com.logonedigital.MBOAcare.repositoy.MedicamentRepo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service

public class MedicamentServiceImpl implements MedicamentService {
    private final MedicamentRepo medicamentRepo;

    public MedicamentServiceImpl(MedicamentRepo medicamentRepo) {
        this.medicamentRepo = medicamentRepo;
    }


    @Override
    public void addMedicament(MedicamentReqdto medicamentReqdto) {
        Optional<Medicament> medicamentFound = this.medicamentRepo.findByNom(medicamentReqdto.getNom());
        if (medicamentFound.isPresent())
            throw new ResourceExistException("Ce medicament existe deja");

        Medicament medicament = new Medicament(medicamentReqdto.getNom(), medicamentReqdto.getForme());

        this.medicamentRepo.save(medicament);


    }

    @Override
    public MedicamentResdto getMedicamentById(String idMedicament) {
        Medicament medicament = this.medicamentRepo.findById(idMedicament).orElseThrow(() -> new ResourceNotFoundException("ce medicament n existe pas"));
        return new MedicamentResdto(medicament.getIdMedicament(), medicament.getNom(),medicament.getForme());
    }

    @Override
    public List<MedicamentResdto> getAllMedicament() {
        return this.medicamentRepo.findAll().stream().map(medicament-> {

            return new MedicamentResdto(medicament.getIdMedicament(),medicament.getNom(),medicament.getForme());
        }).toList();
    }

    @Override
    public void updateMedicament(String idMedicament, MedicamentReqdto medicamentReqdto) {
        Medicament oldMedicament = this.medicamentRepo.findById(idMedicament)
                .orElseThrow(() -> new ResourceNotFoundException("Ce medicament n'existe pas"));

        oldMedicament.setNom(medicamentReqdto.getNom());
        oldMedicament.setForme(medicamentReqdto.getForme());
        this.medicamentRepo.saveAndFlush(oldMedicament);

    }

    @Override
    public void deleteMedicament(String idMedicament) {
        Medicament medicament = this.medicamentRepo.findById(idMedicament).orElseThrow(() -> new ResourceNotFoundException("Ce medicament n existe pas !"));

        this.medicamentRepo.delete(medicament);

    }
    @Override
    public Page<MedicamentResdto> getPaginated(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<Medicament> medicamentPage = medicamentRepo.findAll(pageable);

        return medicamentPage.map(medicament -> new MedicamentResdto(
                medicament.getIdMedicament(),
                medicament.getNom(),
                medicament.getForme()
        ));
    }

    @Override
    public List<Medicament> findMedicamentByForme(String forme) {
        return medicamentRepo.findMedicamentByForme(forme);
    }
}

