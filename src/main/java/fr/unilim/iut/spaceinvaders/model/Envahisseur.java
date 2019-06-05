package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {

	private Direction directionActuelle;

	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
		this.directionActuelle = Direction.GAUCHE;
	}

	public Direction getDirectionActuelle() {
		return this.directionActuelle;
	}

	public void changerDirection(Direction direction) {
		this.directionActuelle = direction;
	}
}
