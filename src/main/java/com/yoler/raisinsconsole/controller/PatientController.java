package com.yoler.raisinsconsole.controller;

import com.yoler.raisinsconsole.service.PatientService;
import com.yoler.raisinsconsole.util.GsonUtil;
import com.yoler.raisinsconsole.util.ImportExcelUtil;
import com.yoler.raisinsconsole.vo.PatientBriefInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 管理端病人controller
 */
@Controller
@RequestMapping(value = "/console/")
public class PatientController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PatientService patientService;
    /**
     * 病人列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "patientListPage", method = RequestMethod.GET)
    public String patientListPage(Model model) {
        List<PatientBriefInfoVo> browserPatientBriefInfoList = patientService.getPatientBriefInfo(1, 20);
        model.addAttribute("browserPatientBriefInfoList", browserPatientBriefInfoList);
        return "patientList";
    }

    @RequestMapping(value = "importPatient", method = RequestMethod.POST)
    public String importPatient(Model model, @RequestParam("patientInfoFile") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = null;
        int fileTypeIndex = fileName.lastIndexOf(".");
        if (fileTypeIndex > 0) {
            fileType = fileName.substring(fileTypeIndex + 1).toLowerCase();
            if (!fileType.equals("xls") && !fileType.equals("xlsx")) {
                return null;
            }
        }
        try {
            List<List<String>> toImportDatas = ImportExcelUtil.importExcel(file.getInputStream(), fileType);
            logger.debug(GsonUtil.objectToJson(toImportDatas));
            patientService.saveToImportConsilia(toImportDatas);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "patientList";
    }
}
