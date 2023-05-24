package main.java.bll;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

import com.itextpdf.text.pdf.PdfWriter;
import main.java.be.Customer;
import main.java.be.LogIns;
import main.java.be.Project;

import java.io.*;
import java.util.ArrayList;

public class PDFGenerator {

    private static void absText( PdfWriter writer,String text, int x, int y, int size) {
        try {

            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.saveState();
            cb.beginText();
            cb.moveText(x, y);
            cb.setFontAndSize(bf, size);
            cb.showText(String.valueOf(text));
            cb.endText();
            cb.restoreState();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generatePDF(String selectedDirectory, String name, Customer selectedCustomer, Project selectedProject, LogIns selectedLogIns, String description, ArrayList<File> selectedPhotos, String  layoutDrawing,
                            ArrayList<Boolean> includes) throws DocumentException, IOException {
        try {

            //Create Document instance.
            Document document = new Document(PageSize.A4);

            //Create OutputStream instance.
            FileOutputStream outputStream;



            try {
                outputStream = new FileOutputStream(new File(selectedDirectory + "\\" +name+ ".pdf") );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            //Create PDFWriter instance.
          PdfWriter writer =  PdfWriter.getInstance(document, outputStream);

            document.open();
            absText(writer, name, 235, 800, 20);

            String customerAndProjectInfo=" ";
            if (includes.get(2)) {
                customerAndProjectInfo+=  selectedCustomer.getFirstName()+" ";
                customerAndProjectInfo+= selectedCustomer.getLastName() + " ";
            }
            if (includes.get(4)){
                customerAndProjectInfo+= selectedProject.getType();
            }
            absText(writer, customerAndProjectInfo, 20, 755, 12);


            absText(writer, "Installaton Drawing:", 20, 730, 12);

            Image image1 =  Image.getInstance(layoutDrawing);

            //Fixed Positioning
            image1.setAbsolutePosition(25, 530);


            //Scale to new height and new width of image
            image1.scaleAbsolute(450, 200);

            //Add to document
            document.add(image1);

            Paragraph text = new Paragraph(description);
            text.setSpacingBefore(270);
            if (includes.get(6)) {
                document.add(text);
            }


            String loginCred = "Login Credentials: Username: " + selectedLogIns.getUsername() + " Password: " + selectedLogIns.getPassword();

            if (includes.get(1)) {
                absText(writer, loginCred, 20, 220, 12);
            }

            if (includes.get(5)) {
                absText(writer, "Photos of the Installation: ", 20, 200, 12);



            for (int i = 0; i < selectedPhotos.size(); i++) {
                Image photo = Image.getInstance(selectedPhotos.get(i).getAbsolutePath());
                System.out.println(selectedPhotos.get(i));
                int pivot =  200 * i;

                photo.setAbsolutePosition(pivot, 35);
                photo.scaleAbsolute(200, 150);
                document.add(photo);
            }
            }


            //Close document and outputStream.
            document.close();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }




}
