package com.stafiiyevskyi.mlsdev.droidfm.view.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stafiiyevskyi.mlsdev.droidfm.R;
import com.stafiiyevskyi.mlsdev.droidfm.app.event.EventPlayAllSavedTracks;
import com.stafiiyevskyi.mlsdev.droidfm.presenter.entity.SavedTrackEntity;
import com.stafiiyevskyi.mlsdev.droidfm.presenter.mapper.track.FileToSavedTrackEntityMapper;
import com.stafiiyevskyi.mlsdev.droidfm.view.Navigator;
import com.stafiiyevskyi.mlsdev.droidfm.view.activity.BaseActivity;
import com.stafiiyevskyi.mlsdev.droidfm.view.adapter.SavedTracksAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by oleksandr on 13.05.16.
 */
public class SavedTracksFragment extends BaseFragment implements SavedTracksAdapter.OnSavedTrackClickListener {

    @Bind(R.id.rv_tracks)
    RecyclerView rvTracks;
    @Bind(R.id.fab)
    FloatingActionButton fabPlayAllTracks;

    private RecyclerView.LayoutManager layoutManager;
    private SavedTracksAdapter adapter;
    private List<SavedTrackEntity> tracks;

    public static BaseFragment newInstance() {
        BaseFragment fragment = new SavedTracksFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.saved_section_title);
        setupRvTracks();
        loadTracks();
    }

    private void loadTracks() {
        File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC), "");
        if (file.isDirectory()) {
            tracks = Observable.from(file.listFiles()).map(new FileToSavedTrackEntityMapper()).toList().toBlocking().first();
        }
        adapter.setData(tracks);
    }

    private void setupRvTracks() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new SavedTracksAdapter(this);
        rvTracks.setLayoutManager(layoutManager);
        rvTracks.setAdapter(adapter);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_saved_tracks;
    }

    @Override
    public void updateToolbar() {
        ((BaseActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.saved_section_title);
        getActivity().supportInvalidateOptionsMenu();
        ((Navigator) getActivity()).setDrawerToggleEnabled();
    }

    @Override
    public void onTrackClick(SavedTrackEntity track) {
        String name = track.getName().replaceFirst(".mp3", "");
        String[] parts = name.split(" - ");
        String trackName = parts[1];
        String artistName = parts[0];
        ((Navigator) getActivity()).navigateToTrackDetails(artistName, trackName, "");
    }

    @OnClick(R.id.fab)
    public void onPlayAllTracksClick() {
        if (tracks.size() > 0) {
            EventPlayAllSavedTracks event = new EventPlayAllSavedTracks();
            event.setTracks(tracks);
            EventBus.getDefault().post(event);
        }
    }
}
