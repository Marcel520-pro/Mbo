package com.pharmacie.mboacare.service.medicament;

import com.pharmacie.mboacare.dto.MedicamentReqdto;
import com.pharmacie.mboacare.dto.MedicamentResdto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface MedicamentService {

    void addMedicament (MedicamentReqdto medicamentReqdto);
    MedicamentResdto getById(String id);
    List<MedicamentResdto> getAll();
    void update(String id, MedicamentResdto medicamentResdto);
    void delete(String id);
    }

