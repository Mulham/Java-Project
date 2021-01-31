package com.company;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("test.pdf");
        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        // this should appear on the logo place
        test(100, 730, document, page);

        document.save("test.pdf");
        document.close();
    }

    public static void test(int x, int y, PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 6);
        String text = "This is me Testing";
        contentStream.showText(text);
        contentStream.endText();
        contentStream.close();
    }
}