package iscteiul.ista.battleship;
/**
 * Representa uma embarcação do tipo Caravel (Caravela), com tamanho fixo de 2.
 * A Caravel ocupa duas células no tabuleiro, sendo posicionada na vertical
 * (NORTH/SOUTH) ou na horizontal (EAST/WEST), dependendo do seu {@link Compass bearing}.
 * A posição inicial fornecida corresponde ao canto superior esquerdo da embarcação.
 *
 * @author Natália 129773
 * @version 1.0
 *
 */

public class Caravel extends Ship {

    /** Tamanho fixo da caravela (2 células). */
    private static final Integer SIZE = 2;

    /** Nome da embarcação. */
    private static final String NAME = "Caravela";

    /**
     * Cria uma nova Caravel com o bearing e posição especificados.
     * A orientação determina como as duas posições da embarcação são calculadas:
     *
     * <ul>
     *   <li><b>NORTH/SOUTH</b>: ocupa duas células verticalmente.</li>
     *   <li><b>EAST/WEST</b>: ocupa duas células horizontalmente.</li>
     * </ul>
     *
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     *
     * @throws NullPointerException     se o bearing for nulo
     * @throws IllegalArgumentException se o bearing não corresponder a uma direção válida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * Obtém o tamanho da caravela.
     *
     * @return tamanho fixo  da caravela (2)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
