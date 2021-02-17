package app.sabeeldev.mysongs.Model;

import java.util.ArrayList;

public class PlayList {
    private ArrayList<Headers> headers;
    private Adds adds;
    private ArrayList<SongOfTheDay> songoftheday;
    private ArrayList<Songs> songss;

    public ArrayList<Headers> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<Headers> headers) {
        this.headers = headers;
    }

    public Adds getAdds() {
        return adds;
    }

    public void setAdds(Adds adds) {
        this.adds = adds;
    }

    public ArrayList<SongOfTheDay> getSongoftheday() {
        return songoftheday;
    }

    public void setSongoftheday(ArrayList<SongOfTheDay> songoftheday) {
        this.songoftheday = songoftheday;
    }

    public ArrayList<Songs> getSongss() {
        return songss;
    }

    public void setSongss(ArrayList<Songs> songss) {
        this.songss = songss;
    }

    public static class Headers {
    }

    public static class Adds {
        private String ID, PID, AddType, Banner, Interstitial, Native, Rewarded, Date;
    }

    public static class SongOfTheDay {
        private String ID, Pid, keyword, Title, Status, Date, AddedBy;

        public SongOfTheDay(String ID, String pid, String keyword, String title, String status, String date, String addedBy) {
            this.ID = ID;
            Pid = pid;
            this.keyword = keyword;
            Title = title;
            Status = status;
            Date = date;
            AddedBy = addedBy;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPid() {
            return Pid;
        }

        public void setPid(String pid) {
            Pid = pid;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getAddedBy() {
            return AddedBy;
        }

        public void setAddedBy(String addedBy) {
            AddedBy = addedBy;
        }
    }

    public static class Songs {
        private String AlbumID, AlbumName, Albumsort, songID, Title, webview, IsRedirection, RedirectionApp, Image, youtubecode, songSortorder;

        public Songs(String albumID, String albumName, String albumsort, String songID, String title, String webview, String isRedirection, String redirectionApp, String image, String youtubecode, String songSortorder) {
            AlbumID = albumID;
            AlbumName = albumName;
            Albumsort = albumsort;
            this.songID = songID;
            Title = title;
            this.webview = webview;
            IsRedirection = isRedirection;
            RedirectionApp = redirectionApp;
            Image = image;
            this.youtubecode = youtubecode;
            this.songSortorder = songSortorder;
        }

        public String getAlbumID() {
            return AlbumID;
        }

        public void setAlbumID(String albumID) {
            AlbumID = albumID;
        }

        public String getAlbumName() {
            return AlbumName;
        }

        public void setAlbumName(String albumName) {
            AlbumName = albumName;
        }

        public String getAlbumsort() {
            return Albumsort;
        }

        public void setAlbumsort(String albumsort) {
            Albumsort = albumsort;
        }

        public String getSongID() {
            return songID;
        }

        public void setSongID(String songID) {
            this.songID = songID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getWebview() {
            return webview;
        }

        public void setWebview(String webview) {
            this.webview = webview;
        }

        public String getIsRedirection() {
            return IsRedirection;
        }

        public void setIsRedirection(String isRedirection) {
            IsRedirection = isRedirection;
        }

        public String getRedirectionApp() {
            return RedirectionApp;
        }

        public void setRedirectionApp(String redirectionApp) {
            RedirectionApp = redirectionApp;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getYoutubecode() {
            return youtubecode;
        }

        public void setYoutubecode(String youtubecode) {
            this.youtubecode = youtubecode;
        }

        public String getSongSortorder() {
            return songSortorder;
        }

        public void setSongSortorder(String songSortorder) {
            this.songSortorder = songSortorder;
        }
    }
}
