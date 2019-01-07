package com.yoler.raisinsconsole.service;


import com.yoler.raisinsconsole.vo.PatientBriefInfoVo;

import java.util.List;
import java.util.Map;

public interface PatientService {

    void saveToImportConsilia(List<List<String>> toImportDatas);

    List<PatientBriefInfoVo> getPatientBriefInfo(int pageIdx, int recordPerPage);

    Map<String, Object> getBrowserMounthDatas();
}
