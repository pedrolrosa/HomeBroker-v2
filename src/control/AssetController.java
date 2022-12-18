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
import javax.swing.JOptionPane;
import java.util.List;
import model.entities.Account;
import model.entities.Asset;
import model.repositories.impl.AssetImpl; 
import model.repositories.services.AssetServices;

/**
 *
 * @author silva.junior
 */
public class AssetController {
    
 
    private static final AssetImpl database = new AssetImpl();
    
    private static final AssetServices databaseServices = new AssetServices();
    
    public static Asset search(Long id){
        return databaseServices.target(id);
    }
    
    public static boolean hasAmount(Long id, Integer quantity){
        return search(id).getAmount() >= quantity;
    }
    
    public static boolean subAmount(Long asset, Integer quantity){
        return databaseServices.updateAmount(asset, search(asset).getAmount() - quantity);
    }
    
    public static boolean create(Asset asset){
        if(asset == null){ 
            JOptionPane.showMessageDialog(null, "Invalid inserts");
            return false; 
        }else{
             
            return database.create(asset); 
        }
    } 
    
    public static List<Asset> read(){
        return database.read(); 
    }
    
    public static boolean update (Asset asset){
      if(asset == null){
       JOptionPane.showMessageDialog(null, "Invalid updates");
       return false; 
      }else{
       return database.update(asset); 
      } 
      
    }
    
    public static boolean delete(Long id){
     return database.delete(id); 
    }
    
        public static void generatePDF() throws DocumentException, IOException {

        List<Asset> assets =database.read();

        // step 1
        Document document = new Document();

        // step 2
        
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Reports/relatorioAtivos.pdf"));
        
        
        // step 3 
        document.open();

        // step 3 header
        Paragraph p = new Paragraph("Relatório de Ativos");
        p.setAlignment(1);
        document.add(p);

        Paragraph p1 = new Paragraph(" ");
        document.add(p1);

        //step 4
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Company")); 
        PdfPCell cell2 = new PdfPCell(new Paragraph("Ticker"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Amount"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Initial Price"));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        //step 5 
        for(Asset asset: assets){
        cell1 = new PdfPCell(new Paragraph(String.valueOf(asset.getCompany()))); 
        cell2 = new PdfPCell(new Paragraph(asset.getTicker()));
        cell3 = new PdfPCell(new Paragraph(String.valueOf(asset.getAmount())));
        cell4 = new PdfPCell(new Paragraph(asset.getInitialPrice().toString()));
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
