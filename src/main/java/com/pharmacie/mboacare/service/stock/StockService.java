package com.pharmacie.mboacare.service.stock;

import com.pharmacie.mboacare.dto.PharmaciResdto;
import com.pharmacie.mboacare.dto.StockReqdto;
import com.pharmacie.mboacare.dto.StockResdto;

import java.util.List;

public interface StockService {

    void addStock (StockReqdto stockReqdto);
    StockResdto getStockById(String idStock);
    List<StockResdto> getAllStock();
    void updateStock(String idStock, StockReqdto stockReqdto);
    void deleteStock(String idStock);
    }

