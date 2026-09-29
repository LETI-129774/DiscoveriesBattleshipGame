package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato para representação e comportamento de um navio 
 * na grelha
 */
public interface IShip {

    /**
     *
     * retorna a categora da embarcação
     */
    String getCategory();

    /**
     * devolve o tamanho da embarcação
     */
    Integer getSize();

    /**
     * obtem a lista com todas as posições individuais ocupadas pelo navio 
     */
    List<IPosition> getPositions();

    /**
     * obtem a posição de referencia (posição inicial/superior esquerda) do navio.
     *
     */
    IPosition getPosition();

    /**
     * Obtém a orientação para onde o navio está apontado na grelha.
     *
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda se encontra a flutuar ou seja se ainda nao foi atingido
     *
     */
    boolean stillFloating();

    /**
     * obtem o valor do limite superior (menor indice de linha) ocupado pelo navio.
     *
     */
    int getTopMostPos();

    /**
     * obtem o valor do limite inferior (maior indice de linha) ocupado pelo navio.
     *
     */
    int getBottomMostPos();

    /**
     * Obtém o valor do limite esquerdo
     *
     */
    int getLeftMostPos();

    /**
     * Obtém o valor do limite direito
     *
     */
    int getRightMostPos();

    /**
     * Verifica se uma dada posição da grelha é ocupada por este navio.
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está a sobrepor outro
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está em contacto ou adjacente a uma posição específica
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo sobre uma posição ocupada por este navio
     */
    void shoot(IPosition pos);
}
