package com.axxes.garageband.xml;

import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.util.MusicXmlParser;
import org.junit.Test;

public class ParseXMLDrumloopTest {

    @Test
    public void parseXMLDrumloopTest() {
        MusicXmlParser.parserDrumloopFromXml("./drumloops/drumloop.xml");
    }

}
