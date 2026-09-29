package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;
/**
 * Representa uma frota de navios no jogo Battleship.
 * Uma frota contém um conjunto de navios e disponibiliza operações
 * para adicionar, consultar e listar navios de acordo com diferentes critérios.
 *
 * @author Natália 129773
 * @version 1.0
 */

public class Fleet implements IFleet {
    /**
     * This operation prints all the given ships
     *
     * @param ships The list of ships
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** Lista de navios pertencentes à frota. */
    private List<IShip> ships;

    /**
     * Cria uma frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista de navios da frota.
     *
     * @return lista de navios da frota
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota caso:
     * <ul>
     *   <li>A frota não tenha atingido o tamanho máximo.</li>
     *   <li>O navio esteja completamente dentro do tabuleiro.</li>
     *   <li>Não exista risco de colisão com outros navios.</li>
     * </ul>
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso;
     *         {@code false} caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Obtém todos os navios pertencentes a uma determinada categoria.
     *
     * @param category categoria a procurar
     * @return lista de navios da categoria indicada
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Obtém todos os navios que ainda não foram afundados.
     *
     * @return lista de navios ainda flutuantes
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Procura um navio que ocupe uma determinada posição do tabuleiro.
     *
     * @param pos posição a verificar
     * @return o navio que ocupa a posição indicada ou
     *         {@code null} se não existir nenhum
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um navio está completamente dentro dos limites do tabuleiro.
     *
     * @param s navio a verificar
     * @return {@code true} se o navio estiver dentro do tabuleiro;
     *         {@code false} caso contrário
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se a colocação de um navio provoca colisão ou proximidade
     * indevida com outros navios da frota.
     *
     * @param s navio a verificar
     * @return {@code true} se existir risco de colisão;
     *         {@code false} caso contrário
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Mostra o estado atual da frota, incluindo:
     * todos os navios, os navios ainda flutuantes
     * e os navios agrupados por categoria.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * This operation prints all the ships of a fleet belonging to a particular
     * category
     *
     * @param category The category of ships of interest
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * This operation prints all the ships of a fleet but not yet shot
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * This operation prints all the ships of a fleet
     */
    void printAllShips() {
        printShips(ships);
    }

}
