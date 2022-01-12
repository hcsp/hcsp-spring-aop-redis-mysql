package com.github.hcsp.service;

import com.github.hcsp.anno.Cache;
import com.github.hcsp.dao.RankDao;
import com.github.hcsp.entity.GoodsRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RankService {
    @Autowired
    RankDao rankDao;

    @Cache
    public List<GoodsRank> getRank() {
        return rankDao.getRank();
    }
}
