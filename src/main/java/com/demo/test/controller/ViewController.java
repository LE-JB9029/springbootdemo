package com.demo.test.controller;

import com.demo.common.service.RedisService;
import com.demo.common.domain.AjaxMessage;
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
    private RedisService redisService;
    @Autowired
    private DataService dataService;
    @Autowired
    private PcDataService pcDataService;

    @GetMapping("index")
    public String index(ModelMap modelMap) {
        String redisKey = "DATA";
        long dataLength = redisService.lGetListSize(redisKey);
        if (dataLength == 0) {
            List list = dataService.findAll(new Data());
            if (list != null && list.size() > 0) {
                redisService.lSet(redisKey, list, 180);
            }
            modelMap.put("list", list);
        } else {
            List<Object> list = redisService.lGet(redisKey, 0, dataLength);
            modelMap.put("list", list);
        }
        redisKey = "PC_DATA";
        dataLength = redisService.lGetListSize(redisKey);
        if (dataLength == 0) {
            List list = pcDataService.findAll(new PcData());
            if (list != null && list.size() > 0) {
                redisService.lSet(redisKey, list, 180);
            }
            modelMap.put("list1", list);
        } else {
            List<Object> list = redisService.lGet(redisKey, 0, dataLength);
            modelMap.put("list1", list);
        }
        return "index";
    }

    @PostMapping("save")
    @ResponseBody
    public AjaxMessage save(Data condition) {
        String redisKey_Lock = "DATA_LOCK";
        try {
            boolean flag = condition.getId() == null;
            if (!flag) {
                redisService.getWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()));
                redisService.setWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()), true);
            }
            dataService.save(condition);
            String redisKey = "DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                if (flag) {
                    redisService.lSet(redisKey, condition, 180);
                } else {
                    List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                    for (int i = 0; i < list.size(); i++) {
                        Object obj = redisService.lGetIndex(redisKey, i);
                        Data tmp = (Data) obj;
                        if (tmp.getId() == condition.getId()) {
                            redisService.lUpdateIndex(redisKey, i, condition);
                            break;
                        }
                    }
                }
            }
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        } finally {
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()), false);
        }
    }

    @PostMapping("getById")
    @ResponseBody
    public AjaxMessage getById(Long id) {
        try {
            if (id == null) {
                return AjaxMessage.error("id不能为空");
            }
            Data condition = null;
            String redisKey = "DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                for (int i = 0; i < list.size(); i++) {
                    Data tmp = (Data) list.get(i);
                    System.out.println(tmp.getId());
                    if (id.equals(tmp.getId())) {
                        condition = tmp;
                        break;
                    }
                }
            } else {
                condition = dataService.getById(id);
            }
            return AjaxMessage.success(condition).setMessage("三玖天下第一");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    @ResponseBody
    public AjaxMessage deleteById(Long id) {
        String redisKey_Lock = "DATA_LOCK";
        try {
            if (id == null) {
                return AjaxMessage.error("id不能为空");
            }
            redisService.getWriteReadLock(redisKey_Lock, String.valueOf(id));
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(id), true);
            dataService.deleteById(id);
            String redisKey = "DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                for (int i = 0; i < list.size(); i++) {
                    Data tmp = (Data) list.get(i);
                    if (id.equals(tmp.getId())) {
                        redisService.lRemove(redisKey, 1, tmp);
                        break;
                    }
                }
            }
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        } finally {
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(id), false);
        }
    }

    @PostMapping("save_")
    @ResponseBody
    public AjaxMessage save_(PcData condition) {
        String redisKey_Lock = "PC_DATA_LOCK";
        try {
            boolean flag = condition.getId() == null;
            if (!flag) {
                redisService.getWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()));
                redisService.setWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()), true);
            }
            pcDataService.save(condition);
            String redisKey = "PC_DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                if (flag) {
                    redisService.lSet(redisKey, condition);
                } else {
                    List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                    for (int i = 0; i < list.size(); i++) {
                        Object obj = redisService.lGetIndex(redisKey, i);
                        PcData tmp = (PcData) obj;
                        if (tmp.getId() == condition.getId()) {
                            redisService.lUpdateIndex(redisKey, i, condition);
                            break;
                        }
                    }
                }
            }
            return AjaxMessage.success(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        } finally {
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(condition.getId()), false);
        }
    }

    @PostMapping("getById_")
    @ResponseBody
    public AjaxMessage getById_(Long id) {
        try {
            if (id == null) {
                return AjaxMessage.error("id不能为空");
            }
            PcData condition = null;
            String redisKey = "PC_DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                for (int i = 0; i < list.size(); i++) {
                    PcData tmp = (PcData) list.get(i);
                    if (id.equals(tmp.getId())) {
                        condition = tmp;
                        break;
                    }
                }
            } else {
                condition = pcDataService.getById(id);
            }
            return AjaxMessage.success(condition).setMessage("狂三天下第一");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("deleteById_")
    @ResponseBody
    public AjaxMessage deleteById_(Long id) {
        String redisKey_Lock = "PC_DATA_LOCK";
        try {
            if (id == null) {
                return AjaxMessage.error("id不能为空");
            }
            redisService.getWriteReadLock(redisKey_Lock, String.valueOf(id));
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(id), true);
            pcDataService.deleteById(id);
            String redisKey = "PC_DATA";
            long dataLength = redisService.lGetListSize(redisKey);
            if (dataLength != 0) {
                List<Object> list = redisService.lGet(redisKey, 0, dataLength);
                for (int i = 0; i < list.size(); i++) {
                    PcData tmp = (PcData) list.get(i);
                    if (id.equals(tmp.getId())) {
                        redisService.lRemove(redisKey, 1, tmp);
                        break;
                    }
                }
            }
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        } finally {
            redisService.setWriteReadLock(redisKey_Lock, String.valueOf(id), false);
        }
    }
}
