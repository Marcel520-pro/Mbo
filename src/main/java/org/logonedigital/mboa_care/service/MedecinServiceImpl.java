package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.LocationDto;
import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Medecin;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.MedecinRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedecinServiceImpl implements MedecinService {
    private final MedecinRepo medecinRepo;

    public MedecinServiceImpl(MedecinRepo medecinRepo) {
        this.medecinRepo = medecinRepo;
    }

    @Override
    public void ajouterMedecin(MedecinReqDto medecinReqDto) {
        Location location = new Location();
        location.setVille(medecinReqDto.getLocation().getVille());
        location.setQuartier(medecinReqDto.getLocation().getQuartier());

        Medecin medecin = new Medecin();
        medecin.setNom(medecinReqDto.getNom());
        medecin.setEmail(medecinReqDto.getEmail());
        medecin.setTelephone(medecinReqDto.getTelephone());
        medecin.setSpecialite(medecinReqDto.getSpecialite());
        medecin.setDisponible(true);
        medecin.setLocation(location);
        
        medecinRepo.save(medecin);
    }

    @Override
    public void supprimerMedecin(String idUtilisateur) {
        if (!medecinRepo.existsById(idUtilisateur)) {
            throw new ResourceNotFoundException("Medecin not found with ID: " + idUtilisateur);
        }
        medecinRepo.deleteById(idUtilisateur);
    }

    @Override
    public Page<MedecinResDto> listerMedecin(int page, int size) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        Page<Medecin> medecins = medecinRepo.findAll(pageable);
        return mapPageToResDto(medecins, pageable);
    }

    @Override
    public void modifierMedecin(String idUtilisateur, MedecinReqDto medecinReqDto) {
        Medecin medecin = medecinRepo.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found with ID: " + idUtilisateur));

        medecin.setNom(medecinReqDto.getNom());
        medecin.setEmail(medecinReqDto.getEmail());
        medecin.setTelephone(medecinReqDto.getTelephone());
        medecin.setSpecialite(medecinReqDto.getSpecialite());

        if (medecinReqDto.getLocation() != null) {
            Location location = medecin.getLocation();
            if (location == null) {
                location = new Location();
            }
            location.setVille(medecinReqDto.getLocation().getVille());
            location.setQuartier(medecinReqDto.getLocation().getQuartier());
            medecin.setLocation(location);
        }

        medecinRepo.save(medecin);
    }

    @Override
    public Page<MedecinResDto> rechercherParSpecialite(String specialite, int page, int size) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        Page<Medecin> medecins = medecinRepo.findBySpecialite(specialite, pageable);
        return mapPageToResDto(medecins, pageable);
    }

    @Override
    public Page<MedecinResDto> rechercheSpecialisteVille(String specialite, String ville, int page, int size) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        Page<Medecin> medecins = medecinRepo.findBySpecialiteAndLocation_Ville(specialite, ville, pageable);
        return mapPageToResDto(medecins, pageable);
    }

    private Page<MedecinResDto> mapPageToResDto(Page<Medecin> medecins, Pageable pageable) {
        List<MedecinResDto> medecinResDtos = medecins.getContent()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(medecinResDtos, pageable, medecins.getTotalElements());
    }

    private MedecinResDto mapToResponseDto(Medecin medecin) {
        LocationDto locationDto = new LocationDto();
        if (medecin.getLocation() != null) {
            locationDto.setVille(medecin.getLocation().getVille());
            locationDto.setQuartier(medecin.getLocation().getQuartier());
        }

        return MedecinResDto.builder()
                .idUtilisateur(medecin.getIdUtilisateur())
                .nom(medecin.getNom())
                .email(medecin.getEmail())
                .telephone(medecin.getTelephone())
                .specialite(medecin.getSpecialite())
                .locationDto(locationDto)
                .build();
    }
}
