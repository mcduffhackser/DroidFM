package com.stafiiyevskyi.mlsdev.droidfm.data.dto.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stafiiyevskyi.mlsdev.droidfm.data.dto.AttrResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleksandr on 20.04.16.
 */
public class Tags {

    @SerializedName("tag")
    @Expose
    private List<Tag> tag = new ArrayList<Tag>();
    @SerializedName("@attr")
    @Expose
    private AttrResponse Attr;

    /**
     * @return The tag
     */
    public List<Tag> getTag() {
        return tag;
    }

    /**
     * @param tag The tag
     */
    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    /**
     * @return The AttrResponse
     */
    public AttrResponse getAttr() {
        return Attr;
    }

    /**
     * @param Attr The @attr
     */
    public void setAttr(AttrResponse Attr) {
        this.Attr = Attr;
    }

}
