package com.axxes.garageband.util;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MusicXmlParser {

    public static Drumloop parserDrumloopFromXml(String fileLocation) {
        Drumloop drumloop = new Drumloop();

        try {
            File file = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            /*NodeList nodeMeasures = doc.getElementsByTagName("measure");

            for (int i = 0; i < nodeMeasures.getLength(); i++) {
                Node nNode = nodeMeasures.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Measure no : " + eElement.getAttribute("id"));
                }
            }*/

            // Measures
            NodeList nodeMeasures = doc.getElementsByTagName("measure");
            for (int i = 0; i < nodeMeasures.getLength(); i++) {
                Node mNode = nodeMeasures.item(i);
                Element mElement = (Element) mNode;
                System.out.println("Measure no: " + mElement.getAttribute("id"));

                // Beats
                NodeList nodeBeats = mElement.getElementsByTagName("beat");
                for (int j = 0; j < nodeBeats.getLength(); j++) {
                    Node bNode = nodeBeats.item(j);

                    if (bNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element bElement = (Element) bNode;
                        System.out.println("\tBeat no: " + bElement.getAttribute("id"));

                        // Instruments
                        NodeList instruments = bElement.getElementsByTagName("name");
                        for (int k = 0; k < instruments.getLength(); k++) {
                            System.out.println("\t\tInstrument name: " + instruments.item(k).getTextContent());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return drumloop;
    }

    private Instrument getInstrument(String instrumentName) {
        Instrument instrument;

        switch (instrumentName) {
            case "Snare":
                instrument = new Snare("snare.wav");
                break;
            case "Cymbal":
                instrument = new Cymbal("cymbal.wav");
                break;
            case "HiHat":
                instrument = new HiHat("hihat.wav");
                break;
            case "Kick":
                instrument = new Kick("kick.wav");
                break;
            default:
                instrument = new HiHat("hihat.wav");
                break;
        }

        return instrument;
    }

}
