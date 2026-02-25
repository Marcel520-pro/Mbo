package com.pharmacie.mboacare.controller;

import com.pharmacie.mboacare.dto.StockReqdto;
import com.pharmacie.mboacare.dto.StockResdto;
import com.pharmacie.mboacare.entity.Stock;
import com.pharmacie.mboacare.service.stock.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")

public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<String> createStock(@Valid @RequestBody StockReqdto stockReqdto){
        this.stockService.addStock(stockReqdto);
        return ResponseEntity.status(201).body("Stock cree avec succes !");
    }
    @GetMapping(path = "/get_by_id/{idStock}")
    public ResponseEntity<StockResdto> getStockById(@PathVariable String idStock){
        return ResponseEntity.status(200)
                .body(this.stockService.getStockById(idStock));
    }
    @GetMapping(path = "/get_all")
    public ResponseEntity<List<StockResdto>> getStock(){
        return ResponseEntity.status(200).body(this.stockService.getAllStock());
    }
    @PutMapping("/update_by_id/{idStock}")
    public ResponseEntity<String> updateStock(@Valid @PathVariable String idStock,@RequestBody StockReqdto stockReqdto){
        this.stockService.updateStock(idStock,new StockReqdto());
        return ResponseEntity.status(202).body("Stock modifie avec succes !");
    }
    @DeleteMapping(path = "delete_by_id/{idStock}")
    public ResponseEntity<String> deleteStock(@PathVariable String idStock){
        this.stockService.deleteStock(idStock);
        return ResponseEntity.status(202).body("Stock supprime avec succces!");
    }
}
