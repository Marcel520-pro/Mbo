package com.logonedigital.MBOAcare.service.pharmaci;

import com.logonedigital.MBOAcare.Exception.ResourceExistException;
import com.logonedigital.MBOAcare.Exception.ResourceNotFoundException;
import com.logonedigital.MBOAcare.dto.MedicamentReqdto;
import com.logonedigital.MBOAcare.dto.PharmaciReqdto;
import com.logonedigital.MBOAcare.dto.PharmaciResdto;
import com.logonedigital.MBOAcare.dto.StockReqdto;
import com.logonedigital.MBOAcare.entity.Medicament;
import com.logonedigital.MBOAcare.entity.Pharmaci;
import com.logonedigital.MBOAcare.entity.Stock;
import com.logonedigital.MBOAcare.repositoy.PharmaciRepo;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class PharmaciServiceImpl implements PharmaciService {

    private final PharmaciRepo pharmaciRepo;


    public PharmaciServiceImpl(PharmaciRepo pharmaciRepo) {
        this.pharmaciRepo = pharmaciRepo;
    }



    @Override
    public void addPharmaci(PharmaciReqdto pharmaciReqdto) {
        Optional<Pharmaci> pharmaciFound = this.pharmaciRepo.findByEmail(pharmaciReqdto.getEmail());
        if (pharmaciFound.isPresent())
            throw new ResourceExistException("Cette pharmacie existe deja");

        Pharmaci pharmacie = new Pharmaci();
        pharmacie.setNom(pharmaciReqdto.getNom());
        pharmacie.setEmail(pharmaciReqdto.getEmail());
        pharmacie.setVille(pharmaciReqdto.getVille());
        pharmacie.setQuartier(pharmaciReqdto.getQuartier());
        pharmacie.setDateCreation(LocalDate.now());

        // transformation des StockReqdto en Stock
        List<Stock> stocks = new ArrayList<>();
        if (pharmaciReqdto.getStocks() != null) {
            for (StockReqdto stockReq : pharmaciReqdto.getStocks()) {
                Stock stock = new Stock();
                stock.setNom(stockReq.getNom());
                stock.setQuantite(stockReq.getQuantite());
                stock.setPharmaci(pharmacie); // lie le stock à la pharmacie

                // transformation des MedicamentReqdto en Medicament
                if (stockReq.getMedicaments() != null) {
                    List<Medicament> medicaments = new ArrayList<>();
                    for (MedicamentReqdto medicamentReq : stockReq.getMedicaments()) {
                        Medicament med = new Medicament();
                        med.setNom(medicamentReq.getNom());
                        med.setForme(medicamentReq.getForme());
                        med.setStock(stock);        // lie au stock
                        med.setPharmaci(pharmacie); // lie à la pharmacie
                        medicaments.add(med);
                    }
                    stock.setMedicaments(medicaments);
                }
                stocks.add(stock);
            }
        }
        pharmacie.setStocks(stocks);

        // sauvegarde tout d'un coup
        this.pharmaciRepo.save(pharmacie);
    }

    @Override
    public PharmaciResdto getPharmaciById(String idPharmaci) {

        Pharmaci pharmaci = this.pharmaciRepo.findById(idPharmaci).orElseThrow(() -> new ResourceNotFoundException("cette pharmacie n existe pas"));
        return new PharmaciResdto(pharmaci.getIdPharmaci(), pharmaci.getNom(), pharmaci.getEmail(),pharmaci.getVille(), pharmaci.getQuartier());
    }

    @Override
    public List<PharmaciResdto> getAllPharmaci() {
        return this.pharmaciRepo.findAll().stream().map(pharmaci -> {

            return new PharmaciResdto(pharmaci.getIdPharmaci(), pharmaci.getNom(), pharmaci.getEmail(),pharmaci.getVille(), pharmaci.getQuartier());
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
        oldPharmaci.setEmail(pharmaciReqdto.getEmail());
        oldPharmaci.setQuartier(pharmaciReqdto.getQuartier());

        this.pharmaciRepo.saveAndFlush(oldPharmaci);


    }

    @Override
    public void deletePharmaci(String idPharmaci) {
        Pharmaci pharmaci = this.pharmaciRepo.findById(idPharmaci).orElseThrow(() -> new ResourceNotFoundException("Cette pharmacie  n existe pas !"));

        this.pharmaciRepo.delete(pharmaci);

    }

    @Override
    public Page<PharmaciResdto> getPaginated(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Pharmaci> pharmaciePage = pharmaciRepo.findAll(pageable);

        return pharmaciePage.map(pharmaci -> new PharmaciResdto(
                pharmaci.getIdPharmaci(),
                pharmaci.getNom(),
                pharmaci.getVille(),
                pharmaci.getQuartier(),
                pharmaci.getEmail()
        ));
    }

    @Override
    public List<Pharmaci> findPharmaciByMedicamentNom(String nomMedicament) {
        return pharmaciRepo.findPharmaciByMedicamentNom(nomMedicament);
    }

    @Override
    public List<Object[]> countMedicamentParPharmaci() {
        return pharmaciRepo.countMedicamentParPharmaci();
    }


}



