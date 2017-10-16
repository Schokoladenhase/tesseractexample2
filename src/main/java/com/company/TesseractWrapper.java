package com.company;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class TesseractWrapper {

    final String installPath = "C:/Program Files (x86)/Tesseract-OCR/";

    private File outputFile = new File("C:\\Temp\\ocrtest\\");

    private File tempFile = new File("C:\\Temp\\ocrtest\\");


    private final String language = "deu";

    public TesseractWrapper() {
    }

    public File doOcr(File file) {
        File outputFile = new File(file.getName().concat(".result"));
        InputStream stderr = null;
        BufferedReader br = null;

        try {
            ProcessBuilder builder = new ProcessBuilder(installPath +File.separator+ "tesseract.exe", file.getAbsolutePath(),
                    outputFile.getName(), "-l " + language,"--oem 2","hocr");

            Process proc = builder.start();
            stderr = proc.getErrorStream();

            InputStreamReader isr = new InputStreamReader(stderr);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            int exitVal = proc.waitFor();
            if (exitVal != 0) {
                System.out.println("Process exitValue: " + exitVal);
            }

        }


        catch (Exception e) {
            // TODO Error Handling
        } finally {
            try {
                stderr.close();
                tempFile.delete();
                br.close();

            } catch (IOException e) {
                System.out.println("Couldn't close Streams");
            }

        }
        return outputFile;
    }

    public String loadResult() {

        String content = null;
        File file = outputFile;
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;

    }
}