package com.demo.test.controller;

import com.demo.common.domain.AjaxMessage;
import com.demo.test.domain.Data;
import com.demo.test.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/test")
@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private DataService dataService;

    @PostMapping("getByCodeAndName")
    @ResponseBody
    @ApiOperation("根据code及name获取信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", dataType = "String", paramType = "query")
    })
    public AjaxMessage getByCodeAndName(String code, String name) {
        try {
            Data condition = new Data();
            condition.setCode(code);
            condition.setName(name);
            List<Data> result = dataService.findAll(condition);
            return AjaxMessage.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @GetMapping("autoComplete")
    public String autoComplete() {
        return "autocomplete";
    }

    @PostMapping("getByName")
    @ResponseBody
    public AjaxMessage getByName(String name) {
        try {
            Data condition = new Data();
            condition.setName(name);
            List<Data> result = dataService.findAll(condition);
            return AjaxMessage.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }
}
