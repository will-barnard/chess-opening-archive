package com.barnard.ChessArchive.model;

public class Opening {

    private int openingId;
    private OpeningCategory openingCategory;
    private Source source;
    private String pgn;
    private String notes;

    public int getOpeningId() {
        return openingId;
    }

    public void setOpeningId(int openingId) {
        this.openingId = openingId;
    }

    public OpeningCategory getOpeningCategory() {
        return openingCategory;
    }

    public void setOpeningCategory(OpeningCategory openingCategory) {
        this.openingCategory = openingCategory;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getPgn() {
        return pgn;
    }

    public void setPgn(String pgn) {
        this.pgn = pgn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
