package com.stafiiyevskyi.mlsdev.droidfm.presenter;

/**
 * Created by oleksandr on 27.04.16.
 */
public interface TrackDetailScreenPresenter extends Presenter {

    void getTrackDetails(String artist, String track, String mbid);
}
