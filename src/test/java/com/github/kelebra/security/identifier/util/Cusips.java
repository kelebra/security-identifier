package com.github.kelebra.security.identifier.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Cusips {

    private static final String CUSIPS_FILE = "/cusips.pdf";

    public static Collection<String> all() {
        try {
            PDDocument document = PDDocument.load(Cusips.class.getResourceAsStream(CUSIPS_FILE));
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(3);
            String st = stripper.getText(document);
            List<String> lines = Arrays.asList(st.split("\n"));

            List<String> cusips = new ArrayList<String>(lines.size());
            for (String line : lines) {
                String[] splitted = line.split(" ");
                String cusipFirstPart = splitted[0].trim();
                if (line.contains("*") && cusipFirstPart.length() == 6) {
                    cusips.add(cusipFirstPart + splitted[1] + splitted[2]);
                }
            }
            return cusips;
        } catch (Exception any) {
            throw new RuntimeException("Unable to process all cusips resource", any);
        }
    }
}
