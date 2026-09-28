/**
 * Representa uma embarcação do tipo Carrack (Nau), com tamanho fixo 3.
 *
 * A Carrack ocupa três células consecutivas no tabuleiro, sendo posicionada
 * verticalmente (NORTH/SOUTH) ou horizontalmente (EAST/WEST), conforme o
 * {@link Compass bearing} fornecido.
 *
 * A posição inicial corresponde ao canto superior esquerdo da embarcação.
 */
package iscteiul.ista.battleship;

public class Carrack extends Ship {

    /** Tamanho fixo da nau (3 células). */
    private static final Integer SIZE = 3;

    /** Nome da embarcação. */
    private static final String NAME = "Nau";

    /**
     * Cria uma nova Carrack com o bearing e posição especificados.
     *
     * Dependendo da orientação, a nau é posicionada da seguinte forma:
     * <ul>
     *   <li><b>NORTH/SOUTH</b>: ocupa três células verticalmente.</li>
     *   <li><b>EAST/WEST</b>: ocupa três células horizontalmente.</li>
     * </ul>
     *
     * @param bearing direção para onde a nau está orientada
     * @param pos     posição inicial (canto superior esquerdo) da nau
     *
     * @throws IllegalArgumentException se o bearing não corresponder a uma direção válida
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /*
     * Obtém o tamanho da nau.
     *
     * @return tamanho fixo da nau (3)
     *
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
