
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONObject;


public class Cast extends AbstractTMDBModel<Cast>
{
    private String character;
    private String credit_id;
    private int gender;
    private String name;
    private int order;
    private String profile_path;

    public String getCharacter() 
    {
        return character;
    }

    public String getCreditID() 
    {
        return credit_id;
    }

    public int getGender() 
    {
        return gender;
    }
    
    public String getGenderString() 
    {
        switch (this.gender) 
        {
            case 1:
                return "Femaie";
                
            case 2:
                return "Male";
                
            default:
                return "Other";
        }

    }

    public String getName() 
    {
        return name;
    }

    public int getOrder() 
    {
        return order;
    }

    public String getProfilePath() 
    {
        return profile_path;
    }
    
    public static Cast parse(String data, Configuration config)
    {
        return new Cast().constructModel(data, config);
    }

    public static Cast parse(File data, Configuration config) throws IOException
    {
        return new Cast().constructModel(data, config);
    }
    
    @Override
    protected Cast constructModel(String data, Configuration config) 
    {
        //Cast cast = new Cast();
        this.raw_json = data;
        this.config = config;
        
        JSONObject json = new JSONObject(data);
        
        this.character = json.getString("character");
        this.credit_id = json.getString("credit_id");
        this.gender = json.optInt("gender", -1);
        this.tmdb_id = json.getInt("id");
        this.name = json.getString("name");
        this.order = json.getInt("order");
        this.profile_path = json.optString("profile_path", "");
        
        return this;
    }
}
