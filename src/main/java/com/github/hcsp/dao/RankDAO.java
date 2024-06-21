package com.github.hcsp.dao;

import com.github.hcsp.entities.RankItemGoodsSales;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RankDAO {
    private Map<String, CacheValue> cache = new ConcurrentHashMap<>();

    @Autowired
    private SqlSession sqlSession;

    private static class CacheValue {
        private final Object data;
        private final long updateAt;

        CacheValue(Object data, long updateAt) {
            this.data = data;
            this.updateAt = updateAt;
        }

        public Object getData() {
            return data;
        }

        public long getUpdateAt() {
            return updateAt;
        }
    }

    public List<RankItemGoodsSales> getGoodsSalesRank() {
        return sqlSession.selectList("com.github.hcsp.mappers.RankMapper.selectGoodsSalesRank");
    }
}
