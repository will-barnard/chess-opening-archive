package com.barnard.ChessArchive.model;

import java.util.List;

public class Opening {

    private int openingId;
    private String openingName;
    private List<OpeningCategory> categories;
    private Source source;
    private String pgn;
    private String notes;

    public int getOpeningId() {
        return openingId;
    }

    public void setOpeningId(int openingId) {
        this.openingId = openingId;
    }

    public String getOpeningName() {
        return openingName;
    }

    public void setOpeningName(String openingName) {
        this.openingName = openingName;
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

    public List<OpeningCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<OpeningCategory> categories) {
        this.categories = categories;
    }
}
