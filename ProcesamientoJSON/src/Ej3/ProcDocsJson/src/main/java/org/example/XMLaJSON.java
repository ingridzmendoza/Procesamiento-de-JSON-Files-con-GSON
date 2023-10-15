package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class XMLaJSON {
    public static void main(String[] args) {

        try {
            String archivoXml = "src\\archivos\\car_sales.xml"; //archivo XML

            String archivoJson = archivoXml.replace(".xml", ".json"); //archivo JSON

            SAXParserFactory factory = SAXParserFactory.newInstance(); //API SAX
            SAXParser saxParser = factory.newSAXParser();
            ManejadorXML handler = new ManejadorXML(); //Instanciar ManejadorXML

            saxParser.parse(new File(archivoXml), handler);

            Gson gson = new GsonBuilder().setPrettyPrinting().create(); //GSON

            String hacerloJson = gson.toJson(handler.getResultado());// toJSON

            //hacerloJson = hacerloJson.replace("\\", "");//Eliminar las barras invertidas

            try (FileWriter fW = new FileWriter(archivoJson)) {
                fW.write(hacerloJson); //escribir JSON en un file
            }

            System.out.println("XML-->JSON. EXITO!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end main
}