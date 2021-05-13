
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONObject;

public class Crew extends AbstractTMDBModel<Crew>
{
    private String credit_id;
    private String department;
    private int gender;
    private int tmdb_id;
    private String job;
    private String name;
    private String profile_path;
    

    public String getCreditID() 
    {
        return credit_id;
    }

    public String getDepartment() 
    {
        return department;
    }

    public int getGender() 
    {
        return gender;
    }

    public int getTmdbID() 
    {
        return tmdb_id;
    }

    public String getJob() 
    {
        return job;
    }


    public String getName() 
    {
        return name;
    }

    public String getProfilePath() 
    {
        return profile_path;
    }
    
    public static Crew parse(String data, Configuration config)
    {
        return new Crew().constructModel(data, config);
    }
    
    public static Crew parse(File data, Configuration config) throws IOException
    {
        return new Crew().constructModel(data, config);
    }

    @Override
    protected Crew constructModel(String data, Configuration config) 
    {
        //Crew crew = new Crew();
        this.raw_json = data;
        this.config = config;
        
        JSONObject json = new JSONObject(data);
        
        this.tmdb_id = json.getInt("id");
        this.credit_id = json.getString("credit_id");
        this.department = json.getString("department");
        this.gender = json.optInt("gender", -1);
        this.job = json.getString("job");
        this.name = json.getString("name");
        this.profile_path = json.optString("profile_path", "");
        
        return this;
    }
}
