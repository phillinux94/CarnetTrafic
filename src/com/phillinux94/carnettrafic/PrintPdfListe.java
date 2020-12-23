package com.phillinux94.carnettrafic;

import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.stream.Stream;


public class PrintPdfListe extends PdfPageEventHelper{

    private Document document;
    private String pathPdf;

    public class Rotate extends PdfPageEventHelper {

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, PdfPage.PORTRAIT);
        }

    }

    PrintPdfListe(String filePath, String station, String debut, String fin){

        this.pathPdf = filePath;

        try {

            // Extraction de la liste des QSO
            Database datas = new Database();

            ArrayList listeQso = datas.getListeQso(debut, fin, 1);
            datas.closeDatabase();

            Font fontTitre = new Font(Font.FontFamily.COURIER, 15, Font.BOLD, BaseColor.BLACK);
            Font fontSousTitre = new Font(Font.FontFamily.COURIER, 14, Font.BOLD, BaseColor.BLACK);
            Font fontTexte = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.BLACK);

            this.document = new Document(PageSize.A4,0,0,0,0);

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(this.pathPdf));

            Rotate rotation = new Rotate();
            pdfWriter.setPageEvent(rotation);

            Rectangle rectangle = new Rectangle(30, 30, 550, 800);
            pdfWriter.setBoxSize("rectangle", rectangle);

            HeaderAndFooterPdfPageEventHelper headerAndFooter =
                    new HeaderAndFooterPdfPageEventHelper();

            pdfWriter.setPageEvent(headerAndFooter);

            document.open();

            // Titre du document
            Paragraph titre = new Paragraph("Station " + station + " - Liste des QSO de " + debut + " à " + fin + "\n \n", fontTitre);
            this.document.add(titre);

            // Tableau des QSO
            ArrayList qso = new ArrayList();

            PdfPTable tableauListe = new PdfPTable(11);
            addTableHeader(tableauListe, "N°",
                    "Date",
                    "Indicatif",
                    "Départ.",
                    "Locator",
                    "Bande",
                    "Mode",
                    "QTH",
                    "Rst R",
                    "Rst E",
                    "Distance");

            for (int i = 0; i < listeQso.size(); i++){

                qso = (ArrayList) listeQso.get(i);

                String id = qso.get(0).toString();
                String date = qso.get(1).toString();
                String indicatif = (String) qso.get(2);
                String departement = (String) qso.get(3);
                String locator = (String) qso.get(4);
                String bande = (String) qso.get(5);
                String mode = (String) qso.get(6);
                String qth = (String) qso.get(7);
                String rst_r = (String) qso.get(8);
                String rst_e = (String) qso.get(9);
                String distance = qso.get(10).toString();

                addRows(tableauListe, id, date, indicatif, departement, locator, bande, mode,
                        qth, rst_r, rst_e, distance);

            }
            this.document.add(tableauListe);

            this.document.close();

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    private void addTableHeader(PdfPTable table,
                                String titre1,
                                String titre2,
                                String titre3,
                                String titre4,
                                String titre5,
                                String titre6,
                                String titre7,
                                String titre8,
                                String titre9,
                                String titre10,
                                String titre11) {
        Stream.of(titre1, titre2, titre3, titre4, titre5, titre6, titre7, titre8, titre9, titre10, titre11)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table,
                         String col1,
                         String col2,
                         String col3,
                         String col4,
                         String col5,
                         String col6,
                         String col7,
                         String col8,
                         String col9,
                         String col10,
                         String col11) {
        PdfPCell ligne1 = new PdfPCell();
        ligne1.setPhrase(new Phrase(col1));
        ligne1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne1);

        PdfPCell ligne2 = new PdfPCell();
        ligne2.setPhrase(new Phrase(col2));
        ligne2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne2);

        PdfPCell ligne3 = new PdfPCell();
        ligne3.setPhrase(new Phrase(col3));
        ligne3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne3);

        PdfPCell ligne4 = new PdfPCell();
        ligne4.setPhrase(new Phrase(col4));
        ligne4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne4);

        PdfPCell ligne5 = new PdfPCell();
        ligne5.setPhrase(new Phrase(col5));
        ligne5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne5);

        PdfPCell ligne6 = new PdfPCell();
        ligne6.setPhrase(new Phrase(col6));
        ligne6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne6);

        PdfPCell ligne7 = new PdfPCell();
        ligne7.setPhrase(new Phrase(col7));
        ligne7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne7);

        PdfPCell ligne8 = new PdfPCell();
        ligne8.setPhrase(new Phrase(col8));
        ligne8.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne8);

        PdfPCell ligne9 = new PdfPCell();
        ligne9.setPhrase(new Phrase(col9));
        ligne9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne9);

        PdfPCell ligne10 = new PdfPCell();
        ligne10.setPhrase(new Phrase(col10));
        ligne10.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne10);

        PdfPCell ligne11 = new PdfPCell();
        ligne11.setPhrase(new Phrase(col11));
        ligne11.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ligne11);


        table.setHorizontalAlignment(Element.ALIGN_CENTER);

    }

}

class HeaderAndFooterPdfPageEventHelper extends PdfPageEventHelper {
    public void onStartPage(PdfWriter pdfWriter, Document document) {

        Rectangle rect = pdfWriter.getBoxSize("rectangle");

        // TOP LEFT
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(""), rect.getLeft(),
                rect.getTop(), 0);

        // TOP MEDIUM
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(""),
                rect.getRight() / 2, rect.getTop(), 0);

        // TOP RIGHT
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(""), rect.getRight(),
                rect.getTop(), 0);
    }

    public void onEndPage(PdfWriter pdfWriter, Document document) {

        Rectangle rect = pdfWriter.getBoxSize("rectangle");
        // BOTTOM LEFT
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase("   Ham Radio 1.0"),
                rect.getLeft()+15, rect.getBottom(), 0);

        // BOTTOM MEDIUM
        String numPage = String.valueOf(pdfWriter.getCurrentPageNumber());
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(numPage),
                rect.getRight() / 2, rect.getBottom(), 0);

        // BOTTOM RIGHT
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase("Liste des QSO  "),
                rect.getRight()-10, rect.getBottom(), 0);
    }
}
