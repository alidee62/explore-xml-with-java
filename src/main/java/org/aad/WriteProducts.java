package org.aad;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WriteProducts {

    public WriteProducts (){
        try{
            this.WriteProductsXML();
        } catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void WriteProductsXML() throws ParserConfigurationException, IOException {
        // DocumentBuilderFactory
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        // Document Builder
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        // Document
        Document xmlDoc = docBuilder.newDocument();

        /*
        Build XML Elements and Text Nodes
        <products>
          <product>
              <name sku"123456">Designer Plate</name>
          </product>
        </products>
        */
        Element rootElement = xmlDoc.createElement("products");

        Element mainElement = xmlDoc.createElement("product");
        mainElement.setAttribute("sku", "123456");

        Text productNameText = xmlDoc.createTextNode("Designer Plate");
        Element productName = xmlDoc.createElement("name");
        productName.appendChild(productNameText);

        mainElement.appendChild(productName);

        rootElement.appendChild(mainElement);

        xmlDoc.appendChild(rootElement);

        // Set OutputFormat
        OutputFormat outFormat = new OutputFormat(xmlDoc);
        outFormat.setIndenting(true);

        // Declare the file
        File xmlFile = new File("products.xml");

        // Declare the FileOutputStream
        FileOutputStream outStream = new FileOutputStream(xmlFile);

        // XMLSerializer to serialise the XML data with the specified OutputFormat
        XMLSerializer serializer = new XMLSerializer(outStream,outFormat);
        serializer.serialize(xmlDoc);
    }

    public static void main(String[] args){

        WriteProducts ProductsXML = new WriteProducts();
    }
}
