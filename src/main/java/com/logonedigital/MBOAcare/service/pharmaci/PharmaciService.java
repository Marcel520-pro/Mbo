package com.logonedigital.MBOAcare.service.pharmaci;

import com.logonedigital.MBOAcare.entity.Pharmaci;
import java.util.List;
import com.logonedigital.MBOAcare.dto.PharmaciReqdto;
import com.logonedigital.MBOAcare.dto.PharmaciResdto;
import org.springframework.data.domain.Page;



public interface PharmaciService {

    void addPharmaci(PharmaciReqdto pharmaciReqdto);
    PharmaciResdto getPharmaciById(String idPharmaci);
    List<PharmaciResdto> getAllPharmaci();
    void updatePharmaci(String idPharmaci, PharmaciReqdto pharmaciReqdto);
    void deletePharmaci(String idSPharmaci);
    Page<PharmaciResdto> getPaginated(int page, int size, String sortBy, String direction);
    List<Pharmaci> findPharmaciByMedicamentNom(String nomMedicament);

    List<Object[]> countMedicamentParPharmaci();

}
