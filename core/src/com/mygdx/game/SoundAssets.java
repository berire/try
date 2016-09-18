package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.stages.Main_Menu;

/**
 * Created by user on 31.8.2016.
 */
public class SoundAssets {

    public static Music music;
    public static Sound crossSound, endgameSound,Osound, Ssound, clickSound;

    public static void loadSound() {
        music = Gdx.audio.newMusic(Gdx.files.internal("cleansng.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        if (Main_Menu.soundEnabled) music.play();

        crossSound = Gdx.audio.newSound(Gdx.files.internal("cross.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click6.mp3"));
        endgameSound = Gdx.audio.newSound(Gdx.files.internal("newGame1.mp3"));
        Osound = Gdx.audio.newSound(Gdx.files.internal("S1.mp3"));
        Ssound = Gdx.audio.newSound(Gdx.files.internal("O1.mp3"));
    }

    public static void playSound(Sound sound) {
        if (Main_Menu.soundEnabled) sound.play(1);
    }
}
