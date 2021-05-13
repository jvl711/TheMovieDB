
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;


public class Credits extends AbstractTMDBModel<Credits>
{
    
    private ArrayList<Cast> cast;
    private ArrayList<Crew> crew;
    
    public Credits()
    {
        cast = new ArrayList();
        crew = new ArrayList();
    }
    
    public ArrayList<Cast> getCast()
    {
        return cast;
    }
    
    public ArrayList<Crew> getCrew()
    {
        return crew;
    }
    
    public static Credits parse(String data, Configuration config)
    {
        return new Credits().constructModel(data, config);
    }

    public static Credits parse(File file, Configuration config) throws IOException
    {
        return new Credits().constructModel(file, config);
    }
    
    @Override
    protected Credits constructModel(String data, Configuration config) 
    {    
        this.raw_json = data;
        this.config = config;
        
        JSONObject json = new JSONObject(data);
        
        this.tmdb_id = json.optInt("id");
        
        if(json.has("cast"))
        {
            JSONArray ca = json.getJSONArray("cast");

            for(int i = 0; i < ca.length(); i++)
            {
                this.cast.add(Cast.parse(ca.getJSONObject(i).toString(), config));
            }
        }
        
        if(json.has("crew"))
        {
            JSONArray cr = json.getJSONArray("crew");

            for(int i = 0; i < cr.length(); i++)
            {
                this.crew.add(Crew.parse(cr.getJSONObject(i).toString(), config));
            }
        }
        
        
        return this;
    }
}
