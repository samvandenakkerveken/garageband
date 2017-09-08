package com.axxes.garageband.xml;

import com.axxes.garageband.util.MusicXmlParser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ParseXMLDrumloopTest {

    @Autowired
    MusicXmlParser parser;

    @Test
    public void parseXMLDrumloopTest() {
        parser.parserDrumloopFromXml("./drumloops/awesome.xml");
    }

}
