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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import model.entities.Account;
import model.entities.RelatesAccountAsset;
import model.entities.Transaction;
import model.enums.TypeTransaction;
import model.repositories.impl.AccountImpl;
import model.repositories.services.AccountServices;

/**
 *
 * @author pedro
 */
public class AccountController {

    public static Account current;
    private static String name;
    private static Integer orderZero = 0;

    private static final AccountImpl database = new AccountImpl();
    private static final AccountServices databaseServices = new AccountServices();

    public static boolean dividend(Long asset, LocalDateTime base, BigDecimal value) {

        boolean pay = false;

        List<Long> idsAccounts = databaseServices.accountsDividend(asset);

        for (Long id : idsAccounts) {

            RelatesAccountAsset related = RelatesController.search(id);

            if (related.getStart().isBefore(base) && related.getModify().isAfter(base)) {

                Transaction transaction = new Transaction();
                transaction.setDescription("dividend");
                transaction.setDestiny(id);
                transaction.setOwner(current.getId());
                transaction.setType(TypeTransaction.TRANSFER);
                transaction.setValue(value);
                transaction.setStart(DateControl.now());

                transfer(id, value);
                pay = true;
            }
        }
        return pay;
    }

    public static String getNameLabel() {
        return name;
    }

    public static void setNameLabel(String name) {
        AccountController.name = name;
    }

    public static void resetOrderZero() {
        orderZero = 0;
    }

    public static boolean addOrderZero() {

        if (orderZero < 3) {
            orderZero += 1;
            return true;
        }
        return false;

    }

    public static Long searchAdm() {
        return databaseServices.searchPerType("ADM");
    }

    public static boolean fee(BigDecimal value) {
        if (transfer(searchAdm(), value)) {
            Transaction transaction = new Transaction();
            transaction.setDescription("fee order");
            transaction.setDestiny(searchAdm());
            transaction.setOwner(current.getId());
            transaction.setType(TypeTransaction.TRANSFER);
            transaction.setValue(value);
            transaction.setStart(DateControl.now());
            
            TransactionController.create(transaction);
        }

        return false;
    }

    public static void feeMonth() {
        Integer nTurn = databaseServices.feeMonth();
        databaseServices.deposit(searchAdm(), databaseServices.target(searchAdm()).getAmount().add(new BigDecimal(20).multiply(new BigDecimal(nTurn))));
    }

    public static boolean hasBalance(BigDecimal value) {

        return current.getAmount().compareTo(value) >= 0;
    }

    public static void refresh() {
        current = databaseServices.target(current.getId());
    }

    public static Account search(Long id) {
        return databaseServices.target(id);
    }

    public static boolean acess(Long owner) {
        Account attempt = databaseServices.acess(owner);

        if (attempt != null) {
            AccountController.current = attempt;
            return true;
        }
        return false;
    }

    public static boolean create(Long owner) {
        if (owner == null) {
            return false;
        } else {

            Account attempt = new Account();

            attempt.setOwner(owner);
            attempt.setAmount(new BigDecimal(20000));
            attempt.setMax(attempt.getAmount().multiply(BigDecimal.TEN));
            attempt.setStart(DateControl.now());

            return database.create(attempt);
        }
    }

    public static List<Account> read() {
        return database.read();
    }

    public static boolean update(Account attempt) {
        return database.update(attempt);
    }

    public static boolean delete(Long id) {
        return database.delete(id);
    }

    public static boolean deposit(BigDecimal value) {
        current.addAmount(value);
        return databaseServices.deposit(current.getId(), AccountController.current.addAmount(value));
    }

    public static boolean withdraw(BigDecimal value) {
        if (AccountController.hasBalance(value)) {
            current.subAmount(value);
            return databaseServices.withdraw(current.getId(), AccountController.current.subAmount(value));
        }
        return false;
    }

    public static boolean transfer(Long destiny, BigDecimal value) {
        Account accountDestiny = AccountController.search(destiny);
        if (AccountController.current.getAmount().compareTo(value) >= 0) {
            if (accountDestiny != null) {
                current.subAmount(value);
                return databaseServices.transfer(current.getId(), destiny, current.subAmount(value), accountDestiny.addAmount(value));
            }
        }
        return false;
    }

    public static boolean transferToMe(Long origin, BigDecimal value) {
        Account accountOrigin = AccountController.search(origin);
        if (accountOrigin.getAmount().compareTo(value) >= 0) {
            current.addAmount(value);
            return databaseServices.transfer(origin, current.getId(), accountOrigin.subAmount(value), current.addAmount(value));
        }
        return false;
    }

    public static void generatePDF() throws DocumentException, IOException {

        List<Account> accounts = database.read();

        // step 1
        Document document = new Document();

        // step 2
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Reports/relatorioDeContas.pdf"));

        // step 3 
        document.open();

        // step 3 header
        Paragraph p = new Paragraph("Relatório de Contas");
        p.setAlignment(1);
        document.add(p);

        Paragraph p1 = new Paragraph(" ");
        document.add(p1);

        //step 4
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Paragraph("id"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Owner"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Amount"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Max"));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        //step 5 
        for (Account account : accounts) {
            cell1 = new PdfPCell(new Paragraph(String.valueOf(account.getId())));
            cell2 = new PdfPCell(new Paragraph(String.valueOf(account.getOwner())));
            cell3 = new PdfPCell(new Paragraph(account.getAmount().toString()));
            cell4 = new PdfPCell(new Paragraph(account.getMax().toString()));
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
