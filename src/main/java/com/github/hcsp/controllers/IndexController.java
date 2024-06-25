package com.github.hcsp.controllers;

import com.github.hcsp.entities.RankItemGoodsSales;
import com.github.hcsp.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private RankService rankService;

    @RequestMapping("/rank.htm")
    public ModelAndView index() {
        List<RankItemGoodsSales> ranks = rankService.getGoodsSalesRank();
        Map<String, Object> model = new HashMap<>();
        model.put("items", ranks);
        return new ModelAndView("rank", model);
    }
}
