package com.psss.homequrantine.Model;

public class QDaysDB {

    String fever, cough, taste, report;

    public QDaysDB() {
    }

    public QDaysDB(String fever, String cough, String taste, String report) {
        this.fever = fever;
        this.cough = cough;
        this.taste = taste;
        this.report = report;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
