package com.pharmacie.mboacare.service.pharmacie;

import com.pharmacie.mboacare.dto.PharmaciReqdto;
import com.pharmacie.mboacare.dto.PharmaciResdto;


import java.util.List;

public interface PharmaciService {
    void addPharmaci (PharmaciReqdto pharmaciReqdto);
    PharmaciResdto getById(String id);
    List<PharmaciResdto> getAll();
    void update(String id, PharmaciResdto pharmaciResdto);
    void delete(String id);
}
