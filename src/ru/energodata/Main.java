package ru.energodata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: detector.cmd <path to recognized files>");
        } else {
            findMissingXMLfiles(args[0]);
        }
    }
        private static void findMissingXMLfiles(String recognizedPathString) {
            File recognizedPath = new File(recognizedPathString);
            File[] xmlFiles = recognizedPath.listFiles(file -> file.getName().toLowerCase().endsWith(".xml"));
            File[] pdfFiles = recognizedPath.listFiles(file -> file.getName().toLowerCase().endsWith(".pdf"));
            List<String> xmlnames = new ArrayList<>();
            if (xmlFiles != null && pdfFiles != null) {
                for (File xmlFile : xmlFiles) {
                    xmlnames.add(xmlFile.getName().substring(0, xmlFile.getName().length() - 4));
                }
            List<String> pdfnames = new ArrayList<>();
                for (File pdfFile : pdfFiles) {
                    pdfnames.add(pdfFile.getName().substring(0, pdfFile.getName().length() - 4));
                }
                System.out.println(".pdf files without .xml pair:");
                for (String pdfname : pdfnames) {
                    if (xmlnames.stream().noneMatch(pdfname::equals)) {
                        System.out.println(pdfname + ".pdf");
                    }
                }
            }
            else{
                System.out.println(".xml-.pdf pairs not found");
            }
        }
    }