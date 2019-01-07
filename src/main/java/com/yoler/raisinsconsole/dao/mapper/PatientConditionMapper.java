package com.yoler.raisinsconsole.dao.mapper;

import com.yoler.raisinsconsole.entity.PatientCondition;
import com.yoler.raisinsconsole.vo.MounthDatasVo;
import com.yoler.raisinsconsole.vo.PatientBriefInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * mybatis-病历Mapper
 */

@Mapper
public interface PatientConditionMapper extends BaseMapper<PatientCondition, Integer> {
    List<PatientBriefInfoVo> getPatientBriefInfo(Map<String, Object> queryMap);

    List<MounthDatasVo> getMounthDatas(Map<String, Object> queryMap);

}
