package com.github.hcsp;

import com.github.hcsp.entity.GoodsItem;
import com.github.hcsp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
public class Router {
    @Autowired
    private RecordService recordService;

    @GetMapping("/rank.htm")
    public ModelAndView responsList() {
        List<GoodsItem> list = recordService.getRecords();

        HashMap<String, Object> model = new HashMap<>();

        model.put("goods", list);
        return new ModelAndView("rank", model);
    };

    @GetMapping("/test")
    @ResponseBody
    public Object test() {
        return recordService.getRecords();
    };
}
