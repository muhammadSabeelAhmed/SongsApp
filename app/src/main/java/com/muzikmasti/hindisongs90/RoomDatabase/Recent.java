package com.muzikmasti.hindisongs90.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recent_songs")
public class Recent {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String albumID, albumName, albumsort, songID, title, webview, isRedirection, redirectionApp, image, youtubecode, songSortorder;

    public Recent(@NonNull String albumID, @NonNull String albumName, @NonNull String albumsort, @NonNull String songID, @NonNull String title, @NonNull String webview, @NonNull String isRedirection, @NonNull String redirectionApp, @NonNull String image, @NonNull String youtubecode, @NonNull String songSortorder) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumsort = albumsort;
        this.songID = songID;
        this.title = title;
        this.webview = webview;
        this.isRedirection = isRedirection;
        this.redirectionApp = redirectionApp;
        this.image = image;
        this.youtubecode = youtubecode;
        this.songSortorder = songSortorder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(@NonNull String albumID) {
        this.albumID = albumID;
    }

    @NonNull
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(@NonNull String albumName) {
        this.albumName = albumName;
    }

    @NonNull
    public String getAlbumsort() {
        return albumsort;
    }

    public void setAlbumsort(@NonNull String albumsort) {
        this.albumsort = albumsort;
    }

    @NonNull
    public String getSongID() {
        return songID;
    }

    public void setSongID(@NonNull String songID) {
        this.songID = songID;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getWebview() {
        return webview;
    }

    public void setWebview(@NonNull String webview) {
        this.webview = webview;
    }

    @NonNull
    public String getIsRedirection() {
        return isRedirection;
    }

    public void setIsRedirection(@NonNull String isRedirection) {
        this.isRedirection = isRedirection;
    }

    @NonNull
    public String getRedirectionApp() {
        return redirectionApp;
    }

    public void setRedirectionApp(@NonNull String redirectionApp) {
        this.redirectionApp = redirectionApp;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    @NonNull
    public String getYoutubecode() {
        return youtubecode;
    }

    public void setYoutubecode(@NonNull String youtubecode) {
        this.youtubecode = youtubecode;
    }

    @NonNull
    public String getSongSortorder() {
        return songSortorder;
    }

    public void setSongSortorder(@NonNull String songSortorder) {
        this.songSortorder = songSortorder;
    }
}


