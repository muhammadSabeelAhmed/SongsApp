package app.sabeeldev.mysongs.PlayerSettings;

public interface CallBacks {
    void callbackObserver(Object obj);

    public interface playerCallBack {
        void onItemClickOnItem(Integer albumId);

        void onPlayingEnd();
    }
}