package com.logonedigital.MBOAcare.service.pharmaci;


import com.logonedigital.MBOAcare.dto.PharmaciReqdto;
import com.logonedigital.MBOAcare.dto.PharmaciResdto;

import java.util.List;

public interface PharmaciService {

    void addPharmaci(PharmaciReqdto pharmaciReqdto);
    PharmaciResdto getPharmaciById(String idPharmaci);
    List<PharmaciResdto> getAllPharmaci();
    void updatePharmaci(String idPharmaci, PharmaciReqdto pharmaciReqdto);
    void deletePharmaci(String idSPharmaci);
}
