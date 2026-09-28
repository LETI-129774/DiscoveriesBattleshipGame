/**
 * Representa um Galeão no jogo da Batalha Naval (versão da época dos Descobrimentos).
 * O Galeão é o navio de maior dimensão, correspondente ao Porta-aviões tradicional, e ocupa 5 quadrículas no tabuleiro.
 * 
 * @author Laura 129778
 * @version 1.0
 */
package iscteiul.ista.battleship;

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Construtor para criar um novo Galeão.
     * Inicializa o navio com o seu nome predefinido e calcula a sua forma e as coordenadas 
     * que este vai ocupar no tabuleiro, dependendo da sua orientação e da sua posição inicial.
     *
     * @param bearing     A orientação do navio na grelha (NORTH, SOUTH, EAST, WEST).
     * @param pos     A posição inicial (coordenada) de referência a partir da qual o navio é desenhado.
     * @throws NullPointerException Se a orientação (bearing) fornecida for nula.
     * @throws IllegalArgumentException Se for fornecida uma orientação inválida.
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * Obtém o tamanho do Galeão em termos de quadrículas ocupadas.
     * @return     O tamanho fixo do Galeão, que é 5.
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }
     /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Norte.
     * @param pos A posição inicial de referência.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Sul.
     * @param pos A posição inicial de referência.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Este.
     * @param pos A posição inicial de referência.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições ocupadas pelo Galeão quando orientado para Oeste.
     * @param pos A posição inicial de referência.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
