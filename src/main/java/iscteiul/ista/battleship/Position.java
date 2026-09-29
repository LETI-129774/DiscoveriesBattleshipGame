package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição concreta (coordenadas de linha e coluna) na grelha do jogo
 */
public class Position implements IPosition {

    /**
     * Índice da linha
     */
    private int row;

    /**
     * Índice da coluna 
     */
    private int column;

    /**
     * Indica se esta posição se encontra ocupada 
     */
    private boolean isOccupied;

    /**
     * Indica se esta posição já levou um tiro
     */
    private boolean isHit;

    /**
     * Constrói uma nova posição na grelha com as coordenadas indicadas.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código de hash para esta posição com base nas coordenadas e estados atuais
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto para determinar se coincidem.
     * Duas posições são consideradas iguais se partilharem o mesmo número de linha e coluna
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente a outra posição na grelha (incluindo diagonais)
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação em formato legível de texto das coordenadas da posição.
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}
