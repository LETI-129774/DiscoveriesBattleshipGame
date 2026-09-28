/**
* Representa uma Fragata no jogo da Batalha Naval (versão da época dos Descobrimentos).
* A Fragata é um navio que ocupa 4 quadrículas na grelha do tabuleiro.
* 
* @author Laura número 129778
* @version 1.0
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Construtor para criar uma nova Fragata.
     * Inicializa o navio com o seu nome predefinido e calcula todas as coordenadas 
     * que este vai ocupar no tabuleiro, dependendo da sua orientação e da sua posição inicial.
     *
     * @param bearing     A orientação do navio na grelha (por exemplo, NORTH, SOUTH, EAST, WEST).
     * @param pos     A posição inicial (coordenada) a partir da qual o navio começa a ser colocado.
     * @throws IllegalArgumentException Se for fornecida uma orientação (bearing) inválida.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /*
     * (non-Javadoc)
     * Obtém o tamanho da Fragata em termos de quadrículas ocupadas.
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
