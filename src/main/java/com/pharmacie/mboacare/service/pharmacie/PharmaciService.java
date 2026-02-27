package com.pharmacie.mboacare.service.pharmacie;

import com.pharmacie.mboacare.dto.PharmaciReqdto;
import com.pharmacie.mboacare.dto.PharmaciResdto;


import java.util.List;

public interface PharmaciService {
    void addPharmaci (PharmaciReqdto pharmaciReqdto);
    PharmaciResdto getPharmaciById(String id);
    List<PharmaciResdto> getAllPharmaci();
    void updatePharmaci(String id, PharmaciReqdto pharmaciReqdto);
    void deletePharmaci(String id);
}
