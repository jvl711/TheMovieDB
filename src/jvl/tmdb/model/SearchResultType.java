/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.tmdb.model;

/**
 *
 * @author jolewis
 */
public enum SearchResultType 
{

    MOVIE(1),
    SHOWS(2);
    
    int value;
    
    private SearchResultType(int value)
    {
        this.value = value;
    }
    
}
