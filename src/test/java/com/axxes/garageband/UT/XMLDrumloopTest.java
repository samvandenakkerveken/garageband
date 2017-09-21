package com.axxes.garageband.UT;

import com.axxes.garageband.Audio.effects.NoEffect;
import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Beat;
import com.axxes.garageband.model.measures.Measure;
import com.axxes.garageband.util.MusicXmlParser;
import com.axxes.garageband.util.MusicXmlWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XMLDrumloopTest {

    @Autowired
    private MusicXmlWriter writer;
    @Autowired
    private MusicXmlParser parser;
    @Autowired
    private Drumloop drumloop;
    private Drumloop testLoop;
    @Autowired
    private NoEffect noEffect;

    @Before
    public void setup() {
        Instrument snare = new Snare();
        Instrument kick = new Kick();
        Instrument hihat = new HiHat();
        Instrument cymbal = new Cymbal();

        Beat beat1 = new Beat();
        Beat beat2 = new Beat();
        Beat beat3 = new Beat();
        Beat beat4 = new Beat();
        Beat beat5 = new Beat();
        Beat beat6 = new Beat();
        Beat beat7 = new Beat();
        Beat beat8 = new Beat();

        beat1.addInstrument(hihat, noEffect);
        beat2.addInstrument(hihat, noEffect);
        beat3.addInstrument(hihat, noEffect);
        beat4.addInstrument(hihat, noEffect);
        beat6.addInstrument(hihat, noEffect);
        beat7.addInstrument(hihat, noEffect);
        beat8.addInstrument(hihat, noEffect);
        beat1.addInstrument(kick, noEffect);
        beat5.addInstrument(snare, noEffect);
        beat5.addInstrument(cymbal, noEffect);

        Measure measure1 = new Measure();
        Measure measure2 = new Measure();

        measure1.addBeatOnLocation(beat1, 0);
        measure1.addBeatOnLocation(beat2, 1);
        measure1.addBeatOnLocation(beat3, 2);
        measure1.addBeatOnLocation(beat4, 3);

        measure2.addBeatOnLocation(beat5, 0);
        measure2.addBeatOnLocation(beat6, 1);
        measure2.addBeatOnLocation(beat7, 2);
        measure2.addBeatOnLocation(beat8, 3);

        this.testLoop = new Drumloop();
        this.testLoop.setBpm(330);
        this.testLoop.setMeasures(Arrays.asList(measure1, measure2));
    }

    @Test
    public void writeDrumloopXml() {
        File file = new File("src/test/resources/test.xml");
        assertThat(writer.writeXMLFromDrumloop(this.testLoop, file)).isTrue();
    }

    @Test
    public void parseDrumloopXml() {
        File file = new File("src/test/resources/testloop.xml");
        parser.parserDrumloopFromXml(file);
        int bpm = this.drumloop.getBpm().get();
        assertThat(bpm).isEqualTo(330);
    }

}
