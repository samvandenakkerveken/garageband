package com.axxes.garageband.util;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Measure;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Arrays;

public class MusicXmlParser {

    @Autowired
    private static Kick kick;
    @Autowired
    private static Cymbal cymbal;
    @Autowired
    private static HiHat hiHat;
    @Autowired
    private static Snare snare;
    @Autowired
    private static Drumloop drumloop;

    public static void parserDrumloopFromXml(String fileLocation) {

        // Empty the current drumloop.
        drumloop.setMeasures(Arrays.asList(new Measure(), new Measure()));

        try {
            File file = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

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
                            Instrument instrument = getInstrument(instruments.item(k).getTextContent());
                            drumloop.addInstrument(instrument, i, j);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Instrument getInstrument(String instrumentName) {
        Instrument instrument;

        switch (instrumentName) {
            case "Snare":
                instrument = snare;
                break;
            case "Cymbal":
                instrument = cymbal;
                break;
            case "HiHat":
                instrument = hiHat;
                break;
            case "Kick":
                instrument = kick;
                break;
            default:
                instrument = hiHat;
                break;
        }

        return instrument;
    }

}
