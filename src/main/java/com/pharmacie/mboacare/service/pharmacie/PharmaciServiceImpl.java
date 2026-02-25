package com.pharmacie.mboacare.service.pharmacie;

import com.pharmacie.mboacare.dto.PharmaciReqdto;
import com.pharmacie.mboacare.dto.PharmaciResdto;
import com.pharmacie.mboacare.entity.Pharmaci;
import com.pharmacie.mboacare.repository.PharmaciRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PharmaciServiceImpl implements PharmaciService {

    private final PharmaciRepo pharmaciRepo;


    public PharmaciServiceImpl(PharmaciRepo pharmaciRepo, PharmaciRepo pharmaciRepo1) {
        this.pharmaciRepo = pharmaciRepo;
    }


    @Override
    public void addPharmaci(PharmaciReqdto pharmaciReqdto) {

    }

    @Override
    public PharmaciResdto getById(String id) {
        return null;
    }

    @Override
    public List<PharmaciResdto> getAll() {
        return List.of();
    }

    @Override
    public void update(String id, PharmaciResdto pharmaciResdto) {

    }

    @Override
    public void delete(String id) {

    }
}

        


