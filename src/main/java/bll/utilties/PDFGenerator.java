package main.java.bll.utilties;

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


    public void generatePDF(String selectedDirectory, String name, Customer selectedCustomer, Project selectedProject,
                            LogIns selectedLogIns, String description, ArrayList<File> selectedPhotos,
                            String  layoutDrawing,ArrayList<Boolean> includes) throws DocumentException, IOException {

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

            Image logoImage =  Image.getInstance("C:\\Users\\Atanas Stoyanov\\OneDrive - Erhvervsakademi Sydvest\\Dokumenter\\GitHub\\WUAV_Document_Manager\\src\\main\\resources\\images\\wuavLogo.png");
            //Fixed Positioning
            logoImage.setAbsolutePosition(40, 300);
            //Scale to new height and new width of image
            logoImage.scaleAbsolute(500, 300);
            //Add to document
            document.add(logoImage);

            document.newPage();

            absText(writer, name, 235, 800, 20);
            String customerAndProjectInfo=" ";
            if (includes.get(2)) {
                customerAndProjectInfo+=  selectedCustomer.getFirstName()+" ";
                customerAndProjectInfo+= selectedCustomer.getLastName() + " ";
            }
            if (includes.get(4)){
                customerAndProjectInfo+= selectedProject.getType();
            }
            absText(writer, customerAndProjectInfo, 20, 755, 14);
            absText(writer, "Installaton Drawing:", 20, 730, 14);
            Image image1 =  Image.getInstance(layoutDrawing);
            //Fixed Positioning
            image1.setAbsolutePosition(25, 510);
            //Scale to new height and new width of image
            image1.scaleAbsolute(450, 200);
            //Add to document
            document.add(image1);
            Paragraph text = new Paragraph(description);
            text.setSpacingBefore(300);
            if (includes.get(6)) {
                document.add(text);
            }
            String loginCred = "Login Credentials: Username: " + selectedLogIns.getUsername() + " Password: " + selectedLogIns.getPassword();

            if (includes.get(1)) {
                absText(writer, loginCred, 20, 190, 14);
            }
            if (includes.get(5)) {
                absText(writer, "Photos of the Installation: ", 20, 160, 14);


            for (int i = 0; i < selectedPhotos.size(); i++) {
                Image photo = Image.getInstance(selectedPhotos.get(i).getAbsolutePath());
                System.out.println(selectedPhotos.get(i));

                document.newPage();
                photo.setAbsolutePosition(40, 300);
                photo.scaleAbsolute(500, 300);
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
