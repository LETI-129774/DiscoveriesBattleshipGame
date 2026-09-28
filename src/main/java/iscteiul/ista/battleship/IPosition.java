package iscteiul.ista.battleship;

/**
 * Interface que define o contrato para a representação e manipulação de uma posição
 * na grelha do jogo Discoveries Battleship Game.
 * <p>
 * Permite consultar as coordenadas (linha e coluna), gerir o estado de ocupação por navios,
 * registar tiros e verificar a adjacência entre posições.
 * </p>
 *
 * @author Teu Nome (LEI-XXXXX / LETI-XXXXX)
 * @version 1.0
 */
public interface IPosition {

    /**
     *obtem qual linha é que estamos
     */
    int getRow();

    /**
     *obtem qual coluna é que estamos
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto para verificar se as suas posições são iguais
     *
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra posição
     *
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Verifica se a posição está ocupada
     */
    void occupy();

    /**
     * verifica a posição que levou um tiro
     */
    void shoot();

    /**
     * Verifica se numa certa posição se ela tem um navio lá ou não
     *
     */
    boolean isOccupied();

    /**
     * Verifica se esta posição já foi disparada contra.
     *
     */
    boolean isHit();
}