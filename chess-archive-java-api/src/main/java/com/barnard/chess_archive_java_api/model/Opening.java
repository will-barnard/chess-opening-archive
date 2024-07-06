package com.barnard.chess_archive_java_api.model;

import jdk.jfr.Category;

public class Opening {

    private int openingId;
    private int categoryId;
    private OpeningCategory openingCategory;
    private int sourceId;
    private Source source;
    private int sourcePage;
    private int sourceSubnumber;
    private String png;
    private String notes;

    public int getOpeningId() {
        return openingId;
    }

    public void setOpeningId(int openingId) {
        this.openingId = openingId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public OpeningCategory getOpeningCategory() {
        return openingCategory;
    }

    public void setOpeningCategory(OpeningCategory openingCategory) {
        this.openingCategory = openingCategory;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public int getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(int sourcePage) {
        this.sourcePage = sourcePage;
    }

    public int getSourceSubnumber() {
        return sourceSubnumber;
    }

    public void setSourceSubnumber(int sourceSubnumber) {
        this.sourceSubnumber = sourceSubnumber;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
