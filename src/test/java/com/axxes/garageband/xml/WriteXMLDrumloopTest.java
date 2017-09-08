package com.axxes.garageband.xml;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Beat;
import com.axxes.garageband.model.measures.Measure;
import com.axxes.garageband.util.MusicXmlWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WriteXMLDrumloopTest {

    private Drumloop drumloop;

    @Before
    public void setup() {
        Instrument snare = new Snare("snare.wav");
        Instrument kick = new Kick("kick.wav");
        Instrument hihat = new HiHat("hihat.wav");
        Instrument cymbal = new Cymbal("cymbal.wav");

        Beat beat1 = new Beat();
        Beat beat2 = new Beat();
        Beat beat3 = new Beat();
        Beat beat4 = new Beat();
        Beat beat5 = new Beat();
        Beat beat6 = new Beat();
        Beat beat7 = new Beat();
        Beat beat8 = new Beat();

        beat1.addInstrument(hihat);
        beat2.addInstrument(hihat);
        beat3.addInstrument(hihat);
        beat4.addInstrument(hihat);
        beat6.addInstrument(hihat);
        beat7.addInstrument(hihat);
        beat8.addInstrument(hihat);
        beat1.addInstrument(kick);
        beat5.addInstrument(snare);
        beat5.addInstrument(cymbal);

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

        this.drumloop = new Drumloop();
        this.drumloop.setMeasures(Arrays.asList(measure1, measure2));
    }

    @Test
    public void writeDrumloopXml() {
        assertThat(MusicXmlWriter.writeXMLFromDrumloop(this.drumloop)).isTrue();
    }

}
