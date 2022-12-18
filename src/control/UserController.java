/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.entities.User;
import model.repositories.impl.UserImpl;
import model.repositories.services.UserServices;

/**
 *
 * @author pedro
 */
public class UserController {
    
    public static User logued;
    
    private static final UserImpl database = new UserImpl();
    private static final UserServices databaseServices = new UserServices();
    
    public static boolean login(String user, String password){
        User attempt = databaseServices.authenticated(user, password);
        if(attempt != null){
            UserController.logued = attempt;
            return true;
        } 
        return false;
    }
    
    public static void refresh(){
        logued =  databaseServices.target(logued.getId());
    }
    
    public static boolean coupling(Long account, Long id){
        return databaseServices.coupling(account, id);
    }
    
    public static User search(Long id){
        return databaseServices.target(id);
    }
    
    public static boolean create(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.create(attempt);
        }
    }
    
    public static List<User> read(){
        return database.read();
    }
    
    public static boolean update(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.update(attempt);
        }
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
    
     public static void generatePDF() throws DocumentException, IOException {

        List<User> users =database.read();

        // step 1
        Document document = new Document();

        // step 2
        
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Reports/relatorioUsuarios.pdf"));
        
        
        // step 3 
        document.open();

        // step 3 header
        Paragraph p = new Paragraph("Relatório de usuarios");
        p.setAlignment(1);
        document.add(p);

        Paragraph p1 = new Paragraph(" ");
        document.add(p1);

        //step 4
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Paragraph("ID")); 
        PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("CPF"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Telefone"));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        //step 5 
        for(User user: users){
        cell1 = new PdfPCell(new Paragraph(String.valueOf(user.getId()))); 
        cell2 = new PdfPCell(new Paragraph(user.getName()));
        cell3 = new PdfPCell(new Paragraph(user.getCpf()));
        cell4 = new PdfPCell(new Paragraph(user.getPhone()));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
         }

        document.add(table);


        // step 6
        document.close();
    }
}
