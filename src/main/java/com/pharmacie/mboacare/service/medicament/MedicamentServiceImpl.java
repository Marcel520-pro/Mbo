package com.pharmacie.mboacare.service.medicament;

import com.pharmacie.mboacare.dto.MedicamentReqdto;
import com.pharmacie.mboacare.dto.MedicamentResdto;
import com.pharmacie.mboacare.repository.MedicamentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicamentServiceImpl implements MedicamentService {
    private final MedicamentRepo medicamentRepo;

    public MedicamentServiceImpl(MedicamentRepo medicamentRepo) {
        this.medicamentRepo = medicamentRepo;
    }


    @Override
    public void addMedicament(MedicamentReqdto medicamentReqdto) {

    }

    @Override
    public MedicamentResdto getById(String id) {
        return null;
    }

    @Override
    public List<MedicamentResdto> getAll() {
        return List.of();
    }

    @Override
    public void update(String id, MedicamentResdto medicamentResdto) {

    }

    @Override
    public void delete(String id) {

    }
}
