package com.berepublic.app.model;

import java.util.List;

/**
 * Created by Dani on 09/05/2016.
 */
public class ITunesResponse {

    private int resultCount;
    private List<Song> results;

    public ITunesResponse(){

    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Song> getResults() {
        return results;
    }

    public void setResults(List<Song> results) {
        this.results = results;
    }
}
