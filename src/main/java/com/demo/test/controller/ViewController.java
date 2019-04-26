package com.demo.test.controller;

import com.demo.test.domain.AjaxMessage;
import com.demo.test.domain.Data;
import com.demo.test.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("view")
public class ViewController {

    @Autowired
    private DataService dataService;

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        List<Data> list = dataService.findAll(new Data());
        modelMap.put("list", list);
        return "view/index";
    }

    @PostMapping("save")
    @ResponseBody
    public AjaxMessage save(Data condition) {
        try {
            dataService.save(condition);
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("getById")
    @ResponseBody
    public AjaxMessage getById(Long id) {
        try {
            Data condition = dataService.getById(id);
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    @ResponseBody
    public AjaxMessage deleteById(Long id) {
        try {
            dataService.deleteById(id);
            return AjaxMessage.success();
        } catch (Exception e) {
            return AjaxMessage.error(e.getMessage());
        }
    }
}
