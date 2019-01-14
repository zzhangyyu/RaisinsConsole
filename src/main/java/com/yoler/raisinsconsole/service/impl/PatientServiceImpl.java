package com.yoler.raisinsconsole.service.impl;

import com.yoler.raisinsconsole.dao.mapper.PatientConditionMapper;
import com.yoler.raisinsconsole.dao.mapper.PatientInfoMapper;
import com.yoler.raisinsconsole.dao.mapper.PrescriptionMapper;
import com.yoler.raisinsconsole.entity.PatientCondition;
import com.yoler.raisinsconsole.entity.PatientInfo;
import com.yoler.raisinsconsole.entity.Prescription;
import com.yoler.raisinsconsole.service.PatientService;
import com.yoler.raisinsconsole.util.DateFormatUtil;
import com.yoler.raisinsconsole.util.StringUtil;
import com.yoler.raisinsconsole.vo.MounthDatasVo;
import com.yoler.raisinsconsole.vo.PatientBriefInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("browserPatientServiceImpl")
public class PatientServiceImpl implements PatientService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    PatientConditionMapper patientConditionMapper;

    @Autowired
    PatientInfoMapper patientInfoMapper;

    @Autowired
    PrescriptionMapper prescriptionMapper;

    @Override
    public void saveToImportConsilia(List<List<String>> toImportDatas) {
        try {
            for (int i = 1; i < toImportDatas.size(); i++) {
                List<String> rowDatas = toImportDatas.get(i);
                logger.debug(rowDatas.size() + "");
                if (rowDatas.size() == 16) {
                    String patientName = rowDatas.get(0);
                    String patientSex = rowDatas.get(1);
                    String patientAge = rowDatas.get(2);
                    String patientBirthday = rowDatas.get(3);
                    String patientIntroducer = rowDatas.get(4);
                    String visitingDate = rowDatas.get(5);
                    String illness = rowDatas.get(6);
                    String addCondition = rowDatas.get(7);
                    String pulse = rowDatas.get(8);
                    String tongue = rowDatas.get(9);
                    String prescriptionName = rowDatas.get(10);
                    String prescriptionDetail = rowDatas.get(11);
                    String prescriptionMethod = rowDatas.get(12);
                    String prescriptionDuration = rowDatas.get(13);
                    String advice = rowDatas.get(14);
                    String analysis = rowDatas.get(15);
                    int patientInfoId = insertPatientInfo(patientName, patientSex, patientAge, patientBirthday, patientIntroducer);
                    int patientConditionId = insertPatientCondition(patientInfoId, visitingDate, pulse, tongue, illness, addCondition, analysis);
                    insertPrescription(patientConditionId, prescriptionName, prescriptionDetail, prescriptionMethod, prescriptionDuration, advice);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }

    }

    @Override
    public List<PatientBriefInfoVo> getPatientBriefInfo(int pageIdx, int recordPerPage) {
        List<PatientBriefInfoVo> result = new ArrayList<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", (pageIdx - 1) * recordPerPage);
        queryMap.put("recordPerPage", recordPerPage);
        try {
            result = patientConditionMapper.getPatientBriefInfo(queryMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Map<String, Object> getBrowserMounthDatas() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        List<MounthDatasVo> browserMounthDatas = patientConditionMapper.getMounthDatas(queryMap);
        result.put("content", browserMounthDatas);
        result.put("status", "200");
        return result;
    }


    /**
     * 插入病人信息
     *
     * @param patientName
     * @param patientSex
     * @param patientAge
     * @param patientBirthday
     * @param patientIntroducer
     * @return
     */
    private int insertPatientInfo(String patientName, String patientSex, String patientAge, String patientBirthday, String patientIntroducer) {
        int patientInfoId;
        PatientInfo patientInfo = patientInfoMapper.getPatientInfoByName(patientName);
        if (patientInfo == null) {
            patientInfo = new PatientInfo();
            patientInfo.setName(patientName);
            patientInfo.setSex(patientSex);

            patientInfo.setAge(StringUtil.isEmpty(patientAge.trim()) ? 0 : (int) Double.parseDouble(patientAge));
            patientInfo.setBirthday(patientBirthday);
            patientInfo.setIntroducer(patientIntroducer);
            patientInfoMapper.insert(patientInfo);
            patientInfoId = patientInfo.getId();
        } else {
            patientInfoId = patientInfo.getId();
        }
        return patientInfoId;
    }

    /**
     * 插入病历
     *
     * @param patientInfoId
     * @param visitingDate
     * @param pulse
     * @param tongue
     * @param illness
     * @param addCondition
     * @param analysis
     * @return
     */
    private int insertPatientCondition(int patientInfoId, String visitingDate, String pulse, String tongue, String illness, String addCondition, String analysis) throws Exception {
        PatientCondition patientCondition = new PatientCondition();
        patientCondition.setPatientInfoId(patientInfoId);
        if (StringUtil.isEmpty(visitingDate)) {
            throw new Exception("就诊日期为空");
        } else {
            patientCondition.setVisitingDate(DateFormatUtil.parseDateTime(visitingDate));
        }
        patientCondition.setPulse(pulse);
        patientCondition.setTongue(tongue);
        patientCondition.setIllness(illness);
        patientCondition.setAddCondition(addCondition);
        patientCondition.setAnalysis(analysis);
        patientConditionMapper.insert(patientCondition);
        return patientCondition.getId();
    }

    /**
     * 插入方剂
     *
     * @param patientConditionId
     * @param prescriptionName
     * @param prescriptionDetail
     * @param prescriptionMethod
     * @param prescriptionDuration
     * @param advice
     */
    private void insertPrescription(int patientConditionId, String prescriptionName, String prescriptionDetail, String prescriptionMethod, String prescriptionDuration, String advice) {
        Prescription prescription = new Prescription();
        prescription.setDoctorId(0);
        prescription.setPatientConditionId(patientConditionId);
        prescription.setPrescriptionName(prescriptionName);
        prescription.setPrescriptionDetail(prescriptionDetail);
        prescription.setPrescriptionMethod(prescriptionMethod);
        prescription.setPrescriptionDuration(prescriptionDuration);
        prescription.setAdvice(advice);
        prescriptionMapper.insert(prescription);
    }
}
