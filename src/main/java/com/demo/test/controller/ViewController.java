package com.demo.test.controller;

import com.demo.test.domain.AjaxMessage;
import com.demo.test.domain.Data;
import com.demo.test.domain.PcData;
import com.demo.test.service.DataService;
import com.demo.test.service.PcDataService;
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
    @Autowired
    private PcDataService pcDataService;

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        List<Data> list = dataService.findAll(new Data());
        modelMap.put("list", list);
        List<PcData> list1 = pcDataService.findAll(new PcData());
        modelMap.put("list1", list1);
        return "index";
    }

    @PostMapping("save")
    @ResponseBody
    public AjaxMessage save(Data condition) {
        try {
            dataService.save(condition);
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @RequestMapping("getById")
    @ResponseBody
    public AjaxMessage getById(Long id) {
        try {
            Data condition = dataService.getById(id);
            return AjaxMessage.success(condition).setMessage("三玖天下第一");
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("save_")
    @ResponseBody
    public AjaxMessage save_(PcData condition) {
        try {
            pcDataService.save(condition);
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @RequestMapping("getById_")
    @ResponseBody
    public AjaxMessage getById_(Long id) {
        try {
            PcData condition = pcDataService.getById(id);
            return AjaxMessage.success(condition).setMessage("狂三天下第一");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("deleteById_")
    @ResponseBody
    public AjaxMessage deleteById_(Long id) {
        try {
            pcDataService.deleteById(id);
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }
}
