package app.sabeeldev.mysongs.Model;

import java.util.ArrayList;

public class Video {
    private String VideoTitle, Duration, VideoTags;
    private ArrayList<AllFormats> AllFormats = new ArrayList<>();
    private String Status, Status_Code, Message;
    private String message;

    public ArrayList<Video.AllFormats> getAllFormats() {
        return AllFormats;
    }

    public void setAllFormats(ArrayList<Video.AllFormats> allFormats) {
        this.AllFormats = allFormats;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getStatus_Code() {
        return Status_Code;
    }

    public void setStatus_Code(String status_Code) {
        this.Status_Code = status_Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String Message) {
        this.message = Message;
    }

    public String getVideoTitle() {
        return VideoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.VideoTitle = videoTitle;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        this.Duration = duration;
    }

    public String getVideoTags() {
        return VideoTags;
    }

    public void setVideoTags(String videoTags) {
        this.VideoTags = videoTags;
    }


    public static class AllFormats {
        private String Link, Type, Extension, Quality;

        public AllFormats(String link, String type, String extension, String quality) {
            this.Link = link;
            this.Type = type;
            this.Extension = extension;
            this.Quality = quality;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String link) {
            this.Link = link;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            this.Type = type;
        }

        public String getExtension() {
            return Extension;
        }

        public void setExtension(String extension) {
            this.Extension = extension;
        }

        public String getQuality() {
            return Quality;
        }

        public void setQuality(String quality) {
            this.Quality = quality;
        }
    }
}
