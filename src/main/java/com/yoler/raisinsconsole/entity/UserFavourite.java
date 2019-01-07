package com.yoler.raisinsconsole.entity;

import java.util.Date;

/**
 * 数据库映射-用户收藏
 */
public class UserFavourite {
    private int userId;
    private String patientInfoId;
    private String patientConditionId;
    private String deleteFalg;
    private Date createTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPatientInfoId() {
        return patientInfoId;
    }

    public void setPatientInfoId(String patientInfoId) {
        this.patientInfoId = patientInfoId;
    }

    public String getPatientConditionId() {
        return patientConditionId;
    }

    public void setPatientConditionId(String patientConditionId) {
        this.patientConditionId = patientConditionId;
    }

    public String getDeleteFalg() {
        return deleteFalg;
    }

    public void setDeleteFalg(String deleteFalg) {
        this.deleteFalg = deleteFalg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
