package main.java.be;

import java.awt.*;

public class Picture {

    private int id;
    private String name;
    private String installationPhoto;
    private int documentationID;

    public Picture(String name, String installationPhoto, int documentationID){
        this.name = name;
        this.installationPhoto = installationPhoto;
        this.documentationID = documentationID;

    }

    public Picture(int id, String name, String installationPhoto, int documentationID) {
        this.id = id;
        this.name = name;
        this.installationPhoto = installationPhoto;
        this.documentationID = documentationID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstallationPhoto() {
        return installationPhoto;
    }

    public void setInstallationPhoto(String installationPhoto) {
        this.installationPhoto = installationPhoto;
    }

    public int getDocumentationID() {
        return documentationID;
    }

    public void setDocumentationID(int documentationID) {
        this.documentationID = documentationID;
    }


}
