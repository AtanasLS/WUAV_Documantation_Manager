package main.java.be;

import java.awt.*;
import java.util.Date;

public class Document {

    private Image layoutDrawing;
    private String description;
    private Date date;

    public Document(Image layoutDrawing, String description, Date date){
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
    }

    public Image getLayoutDrawing() {
        return layoutDrawing;
    }

    public void setLayoutDrawing(Image layoutDrawing) {
        this.layoutDrawing = layoutDrawing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
