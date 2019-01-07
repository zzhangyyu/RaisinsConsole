package com.yoler.raisinsconsole.entity;

import java.util.Date;

/**
 * 数据库映射-病历
 */
public class PatientCondition {
    private int id;
    private int patientInfoId;
    private Date visitingDate;
    private String pulse;
    private String tongue;
    private String addCondition;
    private String analysis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientInfoId() {
        return patientInfoId;
    }

    public void setPatientInfoId(int patientInfoId) {
        this.patientInfoId = patientInfoId;
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getTongue() {
        return tongue;
    }

    public void setTongue(String tongue) {
        this.tongue = tongue;
    }

    public String getAddCondition() {
        return addCondition;
    }

    public void setAddCondition(String addCondition) {
        this.addCondition = addCondition;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}

