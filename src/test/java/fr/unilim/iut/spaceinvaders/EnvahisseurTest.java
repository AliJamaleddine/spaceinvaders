package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;

public class EnvahisseurTest {

	private SpaceInvaders spaceinvaders;
	
	
	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
    public void test_InvaderSeDeplaceCorrectementDansEspaceDeJeu(){

        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(7,3), 1);
        spaceinvaders.deplacerEnvahisseurVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "......EE.......\n" +
                "......EE.......\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    @Test
    public void test_InvaderSeDeplaceEtRevientCorrectementDansEspaceDeJeu(){
        boolean droite;
        droite = true;
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(7,3), 1);
        for (int i = 0; i <= 13; i++) {
            if (droite) {
                spaceinvaders.deplacerEnvahisseurVersLaDroite();
            } else {
                spaceinvaders.deplacerEnvahisseurVersLaGauche();
            }
            if (spaceinvaders.recupererEnvahisseur().abscisseLaPlusADroite() >= spaceinvaders.getLongueur() - 1) {
                droite = false;
            }
            if (spaceinvaders.recupererEnvahisseur().abscisseLaPlusAGauche() <= 0) {
                droite = true;
            }
        }

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                ".....EE........\n" +
                ".....EE........\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
 
     //Divers tests
 
 @Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
		
    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(6,3), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + 
				"...............\n" + 
    	        "...............\n" +
    	        ".........EE....\n" + 
    	        ".........EE....\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
 
 @Test
	public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {
		
    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(6,3), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		assertEquals("" + 
				"...............\n" + 
    	        "...............\n" +
    	        "...EE..........\n" + 
    	        "...EE..........\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
 
 @Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {
		
    	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(13,3), 3);
		spaceinvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + 
				"...............\n" + 
    	        "...............\n" +
    	        ".............EE\n" + 
    	        ".............EE\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" + 
    	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
 
 @Test
	public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {
		
 	spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2),new Position(1,3), 3);
		spaceinvaders.deplacerEnvahisseurVersLaGauche();
		assertEquals("" + 
				"...............\n" + 
 	        "...............\n" +
 	        "EE.............\n" + 
 	        "EE.............\n" + 
 	        "...............\n" + 
 	        "...............\n" + 
 	        "...............\n" + 
 	        "...............\n" + 
 	        "...............\n" + 
 	        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

 @Test
	public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement de l'envahisseur à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement de l'envahisseur vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}

}

