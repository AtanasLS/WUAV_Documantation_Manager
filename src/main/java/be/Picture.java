package main.java.be;

import java.awt.*;

public class Picture {

    private String name;
    private Image installationPhoto;
    private int documentationID;

    public Picture(String name, Image installationPhoto, int documentationID){
        this.name = name;
        this.installationPhoto = installationPhoto;
        this.documentationID = documentationID;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getInstallationPhoto() {
        return installationPhoto;
    }

    public void setInstallationPhoto(Image installationPhoto) {
        this.installationPhoto = installationPhoto;
    }

    public int getDocumentationID() {
        return documentationID;
    }

    public void setDocumentationID(int documentationID) {
        this.documentationID = documentationID;
    }
}
