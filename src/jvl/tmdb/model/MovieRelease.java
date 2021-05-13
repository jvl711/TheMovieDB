/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.tmdb.model;

import jvl.tmdb.AbstractTMDBObject;
import org.json.JSONObject;

/**
 *
 * @author jvl711
 */
public class MovieRelease extends AbstractTMDBObject
{
    private String raw_json;
    private Configuration config;
    
    private String countryCode;
    private String certification;
    private String languageCode;
    /*
        Premiere = 1, Theatrical (limited) = 2, Theatrical = 3, Digital = 4, Physical = 5, TV = 6
    */
    private int releaseTypeCode;
    private String releaseDate;
    private String note;
    
    public static MovieRelease parseString(String data, String countryCode, Configuration config) 
    {
        MovieRelease release = new MovieRelease();
        release.config = config;
        JSONObject json = new JSONObject(data);
        
        release.countryCode = countryCode;
        release.certification = json.optString("certification");
        release.languageCode = json.optString("iso_639_1");
        release.note = json.optString("note");
        release.releaseDate = json.optString("release_date");
        release.releaseTypeCode = json.optInt("type");
        
        
        return release;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() 
    {
        return countryCode;
    }

    /**
     * For the United States this should have the parental rating
     * 
     * @return the certification
     */
    public String getCertification() 
    {
        return certification;
    }

    /**
     * @return the languageCode
     */
    public String getLanguageCode() 
    {
        return languageCode;
    }

    /**
     * Premiere = 1, Theatrical (limited) = 2, Theatrical = 3, Digital = 4, Physical = 5, TV = 6
     * 
     * @return the releaseTypeCode
     */
    public int getReleaseTypeCode() 
    {
        return releaseTypeCode;
    }

    /**
     * @return the releaseDate
     */
    public String getReleaseDate() 
    {
        return releaseDate;
    }

    /**
     * @return the note
     */
    public String getNote() 
    {
        return note;
    }
}
