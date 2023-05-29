package main.test;

import com.itextpdf.text.DocumentException;
import main.java.be.Customer;
import main.java.be.LogIns;
import main.java.be.Project;
import main.java.bll.utilties.PDFGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PDFGeneratorTest {

    @Test
    void testGeneratePDF() {
        String selectedDirectory = "C:\\Users\\Atanas Stoyanov\\OneDrive - Erhvervsakademi Sydvest\\Dokumenter\\GitHub";

        String name = "Unit Test Document" ;
        Customer selectedCustomer = new Customer("John", "McKenna", "johns@email.com", "Johhns Street 10", "Stenkrogen 13 st tv",
                "+45 50 11 25 52", 0);
        Project selectedProject = new Project("Projector Installation", 1);
        LogIns selectedLogIns = new LogIns("john.doe", "password", "Projector Installation", 1);
        String description = "This is a test document";
        ArrayList<File> selectedPhotos = new ArrayList<>();
        selectedPhotos.add(new File("C:\\Users\\Atanas Stoyanov\\OneDrive - Erhvervsakademi Sydvest\\Dokumenter\\GitHub\\WUAV_Document_Manager\\Addada.png"));
        selectedPhotos.add(new File("C:\\Users\\Atanas Stoyanov\\OneDrive - Erhvervsakademi Sydvest\\Dokumenter\\GitHub\\WUAV_Document_Manager\\Test.png"));
        String layoutDrawing = "C:\\Users\\Atanas Stoyanov\\OneDrive - Erhvervsakademi Sydvest\\Dokumenter\\GitHub\\WUAV_Document_Manager\\New Document.png";
        ArrayList<Boolean> includes = new ArrayList<>();
        includes.add(true);  // include customer name
        includes.add(true);  // include login credentials
        includes.add(true);  // include customer first name
        includes.add(true);  // include customer last name
        includes.add(true);  // include project type
        includes.add(true);  // include photos of installation
        includes.add(true);  // include description

        PDFGenerator pdfGenerator = new PDFGenerator();

        try {
            pdfGenerator.generatePDF(selectedDirectory, name, selectedCustomer, selectedProject, selectedLogIns, description, selectedPhotos, layoutDrawing, includes);

            File pdfFile = new File(selectedDirectory + "\\" + name + ".pdf");
            Assertions.assertTrue(pdfFile.exists());

        } catch (DocumentException | IOException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}