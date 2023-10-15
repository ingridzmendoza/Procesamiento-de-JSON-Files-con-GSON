package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorXML extends DefaultHandler {

    public final StringBuilder valorElemento = new StringBuilder(); //valorDelElemento
    public final CreadorJson creadorJson = new CreadorJson(); //instanciar clase CreadorJson

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        valorElemento.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valorElemento.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String value = valorElemento.toString().trim();
        creadorJson.agregarElemento(qName, value);
    }

    public String getResultado() { //metodo GET para obtener resultado de CreadorJson
        return creadorJson.getResultado();
    }

}//end class
