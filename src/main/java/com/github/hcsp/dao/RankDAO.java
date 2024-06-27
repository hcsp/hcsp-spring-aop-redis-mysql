package com.github.hcsp.dao;

import com.github.hcsp.entities.RankItemGoodsSales;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<RankItemGoodsSales> getGoodsSalesRank() {
        return sqlSession.selectList("com.github.hcsp.mappers.RankMapper.selectGoodsSalesRank");
    }
}
