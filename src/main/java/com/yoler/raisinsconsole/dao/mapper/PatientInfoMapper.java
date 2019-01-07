package com.yoler.raisinsconsole.dao.mapper;

import com.yoler.raisinsconsole.entity.PatientInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis-病人基本信息Mapper
 */

@Mapper
public interface PatientInfoMapper extends BaseMapper<PatientInfo, Integer> {

    PatientInfo getPatientInfoByName(String patientName);

}
