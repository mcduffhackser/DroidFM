package com.stafiiyevskyi.mlsdev.droidfm.data.api;

import com.stafiiyevskyi.mlsdev.droidfm.data.dto.album.ArtistTopAlbumsResponse;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.artist.SearchArtist;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.artist.TopChartArtists;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.artist.detail.ArtistInfoResponse;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tag.TopChartTags;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tag.topalbums.TagTopAlbumsResponse;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tag.topartists.TagTopArtistsResponse;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tag.toptracks.TagTopTracksResponse;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tracks.ArtistTopTracks;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tracks.TopChartTracks;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.tracks.search.TrackSearchResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by oleksandr on 20.04.16.
 */
public interface LastFMService {


    // Chart Requests /////////////////////////////////////////////////////
    @GET("?method=chart.gettopartists")
    Observable<TopChartArtists> getTopChartArtist(@Query("page") int pageNumber);

    @GET("?method=chart.gettoptags")
    Observable<TopChartTags> getTopChartTags(@Query("page") int pageNumber);

    @GET("?method=chart.gettoptracks")
    Observable<TopChartTracks> getTopChartTraks(@Query("page") int pageNumber);


    // Artists Requests////////////////////////////////////////////////////
    @GET("?method=artist.search")
    Observable<SearchArtist> searchArtist(@Query("artist") String searchName
            , @Query("page") int page);

    @GET("?method=artist.gettoptracks")
    Observable<ArtistTopTracks> getArtistTopTracks(@Query("artist") String artistName
            , @Query("mbid") String mbid, @Query("page") int pageNumber);

    @GET("?method=artist.gettopalbums")
    Observable<ArtistTopAlbumsResponse> getArtistTopAlbums(@Query("artist") String artistName
            , @Query("mbid") String mbid, @Query("page") int pageNumber);

    @GET("?method=artist.getinfo")
    Observable<ArtistInfoResponse> getArtistInfo(@Query("mbid") String mbid);


    // Track Requests /////////////////////////////////////////////////////
    @GET("?method=track.search")
    Observable<TrackSearchResponse> searchTrack(@Query("artist") String artistName, @Query("track") String trackName
            , @Query("page") int pageNumber);


    // Tag Requests ///////////////////////////////////////////////////////

    @GET("?method=tag.gettopalbums")
    Observable<TagTopAlbumsResponse> getTagsTopAlbums(@Query("tag") String tag, @Query("page") int pageNumber);

    @GET("?method=tag.gettopartists")
    Observable<TagTopArtistsResponse> getTagsTopArtists(@Query("tag") String tag, @Query("page") int pageNumber);

    @GET("?method=tag.gettoptracks")
    Observable<TagTopTracksResponse> getTagsTopTracks(@Query("tag") String tag, @Query("page") int pageNumber);

}
