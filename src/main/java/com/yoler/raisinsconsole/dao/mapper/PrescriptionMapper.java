package com.yoler.raisinsconsole.dao.mapper;

import com.yoler.raisinsconsole.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis-药方Mapper
 */

@Mapper
public interface PrescriptionMapper extends BaseMapper<Prescription, Integer> {

}
