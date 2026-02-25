package com.pharmacie.mboacare.controller;

import com.pharmacie.mboacare.service.pharmacie.PharmaciService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmaci")


public class PharmaciController {
    private final PharmaciService pharmaciService;

    public PharmaciController(PharmaciService pharmaciService) {
        this.pharmaciService = pharmaciService;
    }

}


