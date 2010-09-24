package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.SymbolProperty;
import java.util.List;

/**
 * TextCreationParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class TextCreationParticleSource implements SourceAction
{
    private SymbolProperty symbolProperty;
    private String text = "";


    /**
     * TextCreationParticleSource
     *
     * @param text String
     * @param symbolProperty SymbolProperty
     */
    public TextCreationParticleSource( String text, SymbolProperty symbolProperty )
    {
        this.text = text;
        this.symbolProperty = symbolProperty;
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        for ( int i = 0; i < particles.size(); i++ )
        {
            SymbolProperty sp = new SymbolProperty();

            sp.setText( String.valueOf( text.charAt( i ) ) );
            particles.get( i ).setData( sp );
        }
    }
}