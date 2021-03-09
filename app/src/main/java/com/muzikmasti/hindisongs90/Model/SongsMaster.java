package com.muzikmasti.hindisongs90.Model;

import java.util.ArrayList;

public class SongsMaster {
    private String playList;
    private ArrayList<PlayList.Songs> mysongs;

    public String getPlayList() {
        return playList;
    }

    public SongsMaster(String playList, ArrayList<PlayList.Songs> mysongs) {
        this.playList = playList;
        this.mysongs = mysongs;
    }

    public void setPlayList(String playList) {
        this.playList = playList;
    }

    public ArrayList<PlayList.Songs> getMysongs() {
        return mysongs;
    }

    public void setMysongs(ArrayList<PlayList.Songs> mysongs) {
        this.mysongs = mysongs;
    }
}
