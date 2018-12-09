package com.m800.itunes.dataModel;

import com.google.gson.annotations.SerializedName;
import com.m800.itunes.ui.main.SongRecyclerViewAdapter;

public class Song extends RecyclerBaseItem {

    public Song() {
        super(SongRecyclerViewAdapter.LAYOUT_TYPE_SONG);
    }

    @SerializedName("wrapperType")
    private String mWrapperType;

    @SerializedName("kind")
    private String mKind;

    @SerializedName("artistId")
    private int mArtistID;

    @SerializedName("collectionId")
    private int mCollectionID;

    @SerializedName("trackId")
    private int mTrackID;

    @SerializedName("artistName")
    private String mArtistName;

    @SerializedName("collectionName")
    private String mCollectName;

    @SerializedName("trackName")
    private String mTrackName;

    @SerializedName("collectionCensoredName")
    private String mCollectCensoredName;

    @SerializedName("trackCensoredName")
    private String mTrackCensoredName;

    @SerializedName("artistViewUrl")
    private String mArtistViewUrl;

    @SerializedName("collectionViewUrl")
    private String mCollectionVIewUrl;

    @SerializedName("trackViewUrl")
    private String mTrackViewUrl;

    @SerializedName("previewUrl")
    private String mPreviewUrl;

    @SerializedName("artworkUrl60")
    private String mArtworkUrl60;

    @SerializedName("artworkUrl100")
    private String mArtworkUrl100;

    @SerializedName("collectionPrice")
    private float mCollectionPrice;

    @SerializedName("trackPrice")
    private float mTrackPrice;

    @SerializedName("collectionExplicitness")
    private String mCollectionExplicitness;

    @SerializedName("trackExplicitness")
    private String mTrackExplicitness;

    @SerializedName("discCount")
    private int mDiscConnt;

    @SerializedName("discNumber")
    private int mDiscNumber;

    @SerializedName("trackCount")
    private int mTrackCount;

    @SerializedName("trackNumber")
    private int mTrackNumber;

    @SerializedName("trackTimeMillis")
    private int mTrackTimeMillis;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("currency")
    private String mCurrency;

    @SerializedName("primaryGenreName")
    private String mPrimaryGenreName;


    public String getWrapperType() {
        return mWrapperType;
    }

    public void setWrapperType(String wrapperType) {
        mWrapperType = wrapperType;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public int getArtistID() {
        return mArtistID;
    }

    public void setArtistID(int artistID) {
        mArtistID = artistID;
    }

    public int getCollectionID() {
        return mCollectionID;
    }

    public void setCollectionID(int collectionID) {
        mCollectionID = collectionID;
    }

    public int getTrackID() {
        return mTrackID;
    }

    public void setTrackID(int trackID) {
        mTrackID = trackID;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getCollectName() {
        return mCollectName;
    }

    public void setCollectName(String collectName) {
        mCollectName = collectName;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        mTrackName = trackName;
    }

    public String getCollectCensoredName() {
        return mCollectCensoredName;
    }

    public void setCollectCensoredName(String collectCensoredName) {
        mCollectCensoredName = collectCensoredName;
    }

    public String getTrackCensoredName() {
        return mTrackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        mTrackCensoredName = trackCensoredName;
    }

    public String getArtistViewUrl() {
        return mArtistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        mArtistViewUrl = artistViewUrl;
    }

    public String getCollectionVIewUrl() {
        return mCollectionVIewUrl;
    }

    public void setCollectionVIewUrl(String collectionVIewUrl) {
        mCollectionVIewUrl = collectionVIewUrl;
    }

    public String getTrackViewUrl() {
        return mTrackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        mTrackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        mPreviewUrl = previewUrl;
    }

    public String getArtworkUrl60() {
        return mArtworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        mArtworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return mArtworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        mArtworkUrl100 = artworkUrl100;
    }

    public float getCollectionPrice() {
        return mCollectionPrice;
    }

    public void setCollectionPrice(float collectionPrice) {
        mCollectionPrice = collectionPrice;
    }

    public float getTrackPrice() {
        return mTrackPrice;
    }

    public void setTrackPrice(float trackPrice) {
        mTrackPrice = trackPrice;
    }

    public String getCollectionExplicitness() {
        return mCollectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        mCollectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return mTrackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        mTrackExplicitness = trackExplicitness;
    }

    public int getDiscConnt() {
        return mDiscConnt;
    }

    public void setDiscConnt(int discConnt) {
        mDiscConnt = discConnt;
    }

    public int getDiscNumber() {
        return mDiscNumber;
    }

    public void setDiscNumber(int discNumber) {
        mDiscNumber = discNumber;
    }

    public int getTrackCount() {
        return mTrackCount;
    }

    public void setTrackCount(int trackCount) {
        mTrackCount = trackCount;
    }

    public int getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        mTrackNumber = trackNumber;
    }

    public int getTrackTimeMillis() {
        return mTrackTimeMillis;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        mTrackTimeMillis = trackTimeMillis;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getPrimaryGenreName() {
        return mPrimaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        mPrimaryGenreName = primaryGenreName;
    }
}
