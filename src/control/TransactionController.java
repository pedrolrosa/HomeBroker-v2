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
import model.entities.Asset;
import model.entities.Transaction;
import model.repositories.impl.TransactionImpl;
import model.repositories.services.TransactionServices;

/**
 *
 * @author pedro
 */
public class TransactionController {
    
    private static final TransactionImpl database = new TransactionImpl();
    private static final TransactionServices databaseServices = new TransactionServices();

    
    public static Transaction search(Long id){
        return databaseServices.target(id);
    }    
    
    public static boolean create(Transaction attempt){
        return database.create(attempt);
    }
    
    public static List<Transaction> read(){
        return database.read();
    }
    
    public static List<Transaction> read(Long owner){
        return databaseServices.search(owner);
    }
    
    public static boolean update(Transaction transaction){
        return database.update(transaction);
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
   } 
        public static void generatePDF() throws DocumentException, IOException {

        List<Transaction> transactions =database.read();

        // step 1
        Document document = new Document();

        // step 2
        
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Reports/relatorioTransacoes.pdf"));
        
        
        // step 3 
        document.open();

        // step 3 header
        Paragraph p = new Paragraph("Relatório de Transações");
        p.setAlignment(1);
        document.add(p);

        Paragraph p1 = new Paragraph(" ");
        document.add(p1);

        //step 4
        PdfPTable table = new PdfPTable(5);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Id")); 
        PdfPCell cell2 = new PdfPCell(new Paragraph("Owner"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Destiny"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Type"));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Value"));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);

        //step 5 
        for(Transaction transaction: transactions){
        cell1 = new PdfPCell(new Paragraph(String.valueOf(transaction.getId()))); 
        cell2 = new PdfPCell(new Paragraph(String.valueOf(transaction.getOwner())));
        cell3 = new PdfPCell(new Paragraph(String.valueOf(transaction.getDestiny())));
        cell4 = new PdfPCell(new Paragraph(transaction.getType().toString()));
        cell5 = new PdfPCell(new Paragraph(transaction.getValue().toString()));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
         }

        document.add(table);


        // step 6
        document.close();
    }
}
