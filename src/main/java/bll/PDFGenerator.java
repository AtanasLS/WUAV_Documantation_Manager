package main.java.bll;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.print.attribute.standard.Destination;
import java.io.*;

public class PDFGenerator {

    public void generatePDF(String selectedDirectory, String name,String description, String  layoutDrawing) throws DocumentException, IOException {
        try {

            //Create Document instance.
            Document document = new Document();

            //Create OutputStream instance.
            OutputStream outputStream;

            /*
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setInitialDirectory(new File("src"));
            Stage stage = new Stage();
            File selectedDirectory = directoryChooser.showDialog(stage);
             */

            try {
                outputStream = new FileOutputStream(new File(selectedDirectory + "\\" +name+ ".pdf") );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            //Create PDFWriter instance.
            PdfWriter.getInstance(document, outputStream);

            //Open the document.
            document.open();

            //Add content to the document.
            document.add(new Paragraph(description));
            document.add(new Paragraph("Layout Drawing"));

            Image image1 =  Image.getInstance(layoutDrawing);

            //Fixed Positioning
            image1.setAbsolutePosition(20f, 700f);

            //Scale to new height and new width of image
            image1.scaleAbsolute(200, 200);

            //Add to document
            document.add(image1);


            //Close document and outputStream.
            document.close();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
