package com.pharmacie.mboacare.service.stock;

import com.pharmacie.mboacare.dto.StockReqdto;
import com.pharmacie.mboacare.dto.StockResdto;
import com.pharmacie.mboacare.entity.Stock;
import com.pharmacie.mboacare.exception.ResourceExistException;
import com.pharmacie.mboacare.exception.ResourceNotFoundException;
import com.pharmacie.mboacare.repository.StockRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class StockServiceImpl implements StockService {
    private final StockRepo stockRepo;

    public StockServiceImpl(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }


    @Override
    public void addStock(StockReqdto stockReqdto) {
        Optional<Stock> stockFound = this.stockRepo.findByNom(stockReqdto.getNom());
        if(stockFound.isPresent())
            throw new ResourceExistException("Ce stock existe deja");

        Stock stock = new Stock(stockReqdto.getQuantite(),stockReqdto.getNom());

        this.stockRepo.save(stock);


    }

    @Override
    public StockResdto getStockById(String idStock) {

        Stock stock = this.stockRepo.findById(idStock).orElseThrow(()-> new ResourceNotFoundException("ce stock n existe pas"));
        return new StockResdto(stock.getIdStock(),stock.getQuantite(),stock.getNom());

    }



    @Override
    public List<StockResdto> getAllStock() {
        return this.stockRepo.findAll().stream().map(stock -> {

            return new StockResdto(stock.getIdStock(),stock.getQuantite(), stock.getNom());
        }).toList();


    }

    @Override

    public void updateStock(String idStock, StockReqdto stockReqdto) {

        Stock oldStock = this.stockRepo.findById(idStock)
                .orElseThrow(() -> new ResourceNotFoundException("ce stock n existe pas"));

        oldStock.setQuantite(stockReqdto.getQuantite());
        oldStock.setNom(stockReqdto.getNom());

        this.stockRepo.saveAndFlush(oldStock);


    }


    @Override
    public void deleteStock(String idStock) {
        Stock stock = this.stockRepo.findById(idStock).orElseThrow(()-> new ResourceNotFoundException("Ce stock n existe pas !"));

        this.stockRepo.delete(stock);

    }
}
