package com.j2dparticles.sourceActions;

import com.j2dparticles.data.Particle;
import com.j2dparticles.data.SymbolProperty;
import java.util.List;

/**
 * RandomCharacterParticleSource
 *
 * @author  Michael Janner Marques
 * @version 0.1, 06/09/2010
 */
public class RandomCharacterParticleSource implements SourceAction
{
    private String letters = "ABCDEFGHIJLMNOPQRSTUVXZWYKabcdefghijlmnoprstuvxzwyk";

    /**
     * RandomCharacterParticleSource
     *
     */
    public RandomCharacterParticleSource()
    {
    }
    
    /**
     * applyAction
     *
     * @param particles List<Particle>
     */
    @Override
    public void applyAction( List<Particle> particles )
    {
        for ( Particle p: particles )
        {
            SymbolProperty sp = new SymbolProperty();

            sp.setText( String.valueOf( letters.charAt( (int) (Math.random() * 50) ) )  );
            p.setData( sp );
        }
    }
}
