package com.github.hcsp;


import com.github.hcsp.entity.RankItem;
import com.github.hcsp.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RankService rankService;

    @RequestMapping("/rank.htm")
    @ResponseBody
    public ModelAndView search() {
        List<RankItem> items = rankService.selectRank();
        HashMap<String, Object> model = new HashMap<>();
        model.put("items", items);
        return new ModelAndView("Rank", model);
    }

}
