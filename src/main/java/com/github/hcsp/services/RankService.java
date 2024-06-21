package com.github.hcsp.services;

import com.github.hcsp.dao.RankDAO;
import com.github.hcsp.entities.RankItemGoodsSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
    @Autowired
    private RankDAO rankDAO;

    public List<RankItemGoodsSales> getGoodsSalesRank() {
        return rankDAO.getGoodsSalesRank();
    }
}
