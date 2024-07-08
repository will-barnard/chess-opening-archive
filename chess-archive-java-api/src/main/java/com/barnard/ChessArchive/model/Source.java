package com.barnard.ChessArchive.model;

public class Source {

    private int sourceId;
    private String sourceName;
    private int sourcePage;
    private int subnumber;

    public Source() {
    }

    public Source(int sourceId, String sourceName) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(int sourcePage) {
        this.sourcePage = sourcePage;
    }

    public int getSubnumber() {
        return subnumber;
    }

    public void setSubnumber(int subnumber) {
        this.subnumber = subnumber;
    }
}
