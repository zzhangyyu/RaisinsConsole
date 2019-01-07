package com.yoler.raisinsconsole.controller;

import com.yoler.raisinsconsole.service.PatientService;
import com.yoler.raisinsconsole.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 管理端通用controller
 */
@Controller
@RequestMapping(value = "/console/")
public class OverviewController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    PatientService patientService;


    @RequestMapping(value = "overViewPage", method = RequestMethod.GET)
    public String overView() {
        return "overview";
    }

    @RequestMapping(value = "getMounthDatas", method = RequestMethod.POST)
    public @ResponseBody
    String getMounthDatas(@RequestBody String reqJson) {
        Map<String, Object> resultMap = patientService.getBrowserMounthDatas();
        String result = GsonUtil.objectToJson(resultMap);
        logger.debug(result);
        return result;
//        return "{\"content\":[{\"visitingDate\":\"2017-05-06\",\"visitingCnt\":\"5\"},{\"visitingDate\":\"2017-05-07\",\"visitingCnt\":\"10\"},{\"visitingDate\":\"2017-05-08\",\"visitingCnt\":\"8\"},{\"visitingDate\":\"2017-05-09\",\"visitingCnt\":\"6\"}],\"status\":\"200\"}";
    }
}
