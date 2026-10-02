package iscteiul.ista.battleship;
/**
 * Representa uma embarcação do tipo Barge (Barca), com tamanho fixo de 1.
 * Uma Barge ocupa apenas uma célula no tabuleiro e não tem orientação relevante 
 * além do seu bearing, que é mantido por consistência com outras subclasses de {@link Ship}.
 *
 * @author Natália 129773
 * @version 1.0
 *
 */

public class Barge extends Ship {
    /** Tamanho fixo da barca (1 célula). */
    private static final Integer SIZE = 1;

    /** Nome da embarcação. */
    private static final String NAME = "Barca";

    /**
     * Cria uma nova Barge com o bearing e posição especificados.
     *
     * @param bearing - barge bearing
     * @param pos     - upper left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }
    
    /** 
     * Obtém o tamanho da barca.
     *
     * @return tamanho fixo da barca (1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
