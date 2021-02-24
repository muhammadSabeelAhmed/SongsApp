package app.sabeeldev.mysongs.Model;

import java.util.ArrayList;

public class Video {
    private String VideoTitle, Duration, VideoTags;
    private ArrayList<AllFormats> AllFormats;
    private String Status, Status_Code, Message;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus_Code() {
        return Status_Code;
    }

    public void setStatus_Code(String status_Code) {
        Status_Code = status_Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getVideoTitle() {
        return VideoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        VideoTitle = videoTitle;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getVideoTags() {
        return VideoTags;
    }

    public void setVideoTags(String videoTags) {
        VideoTags = videoTags;
    }

    public ArrayList<Video.AllFormats> getAllFormats() {
        return AllFormats;
    }

    public void setAllFormats(ArrayList<Video.AllFormats> allFormats) {
        AllFormats = allFormats;
    }

    public static class AllFormats {
        private String Link, Type, Extension, Quality;

        public AllFormats(String link, String type, String extension, String quality) {
            Link = link;
            Type = type;
            Extension = extension;
            Quality = quality;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String link) {
            Link = link;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getExtension() {
            return Extension;
        }

        public void setExtension(String extension) {
            Extension = extension;
        }

        public String getQuality() {
            return Quality;
        }

        public void setQuality(String quality) {
            Quality = quality;
        }
    }
}
