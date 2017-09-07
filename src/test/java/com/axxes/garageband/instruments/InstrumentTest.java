package com.axxes.garageband.instruments;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstrumentTest {

    @Before
    public void setup(){
        TinySound.init();
    }

    @Test
    public void testPlaySound(){
        Boolean played = false;
        Sound sound = TinySound.loadSound("kick.wav");
        sound.play();
        played = true;
        assertThat(played).isTrue();
    }
}
