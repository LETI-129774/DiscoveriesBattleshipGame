package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lógica principal do jogo Batalha Naval.
 * Gere o estado da partida, incluindo a frota do adversário e o histórico de tiros (válidos, inválidos, repetidos e certeiros).
 * 
 * @author Laura 129778
 * @version 1.0
 *
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Construtor para inicializar uma nova partida de Batalha Naval.
     * Prepara os contadores de estatísticas a zero e associa a frota em jogo.
     * 
     * @param fleet     A frota de navios que será o alvo dos tiros nesta partida.
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /*
     * Efetua um tiro numa posição específica do tabuleiro.
     * Verifica se o tiro é válido e se não é repetido. Atualiza as estatísticas 
     * de jogo e verifica se atingiu ou afundou algum navio.
     *
     * @param pos A coordenada (posição) onde o tiro é disparado.
     * @return O navio atingido caso este tenha sido afundado com este tiro; null caso contrário.
     
     * @see battleship.IGame#fire(battleship.IPosition)
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /*
     * Obtém a lista de todas as posições onde foram efetuados tiros válidos.
     *
     * @return Uma lista de objetos IPosition representando os tiros realizados.
     *
     * @see battleship.IGame#getShots()
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /*
     * Obtém o número total de tiros repetidos (disparados para coordenadas já atacadas).
     *
     * @return O contador de tiros repetidos.
     *
     * @see battleship.IGame#getRepeatedShots()
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /*
     * Obtém o número total de tiros inválidos (fora dos limites do tabuleiro).
     *
     * @return O contador de tiros inválidos.
     *
     * @see battleship.IGame#getInvalidShots()
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /*
     * Obtém o número total de tiros que atingiram com sucesso uma parte de um navio.
     *
     * @return O contador de tiros certeiros.
     *
     * @see battleship.IGame#getHits()
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /*
     * Obtém o número total de navios que já foram totalmente afundados.
     *
     * @return O contador de navios afundados.
     *
     * @see battleship.IGame#getSunkShips()
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /*
     * Obtém o número de navios que ainda estão a flutuar (não foram afundados).
     *
     * @return A quantidade de navios restantes na frota.
     *
     * @see battleship.IGame#getRemainingShips()
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Valida se as coordenadas de um tiro estão dentro dos limites permitidos do tabuleiro.
     * 
     * @param pos A posição do tiro a validar.
     * @return true se o tiro for dentro dos limites, false caso contrário.
     */
    
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um tiro já foi efetuado na mesma posição anteriormente.
     * 
     * @param pos A posição a verificar.
     * @return true se o tiro já constar no histórico, false caso seja inédito.
     */
    
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime na consola uma representação visual do tabuleiro com um marcador específico.
     * Oceano é representado por '.' e as posições fornecidas recebem o marcador.
     * 
     * @param positions A lista de posições a marcar no tabuleiro.
     * @param marker O carácter a usar para desenhar as posições indicadas.
     */

    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Prints the board showing valid shots that have been fired // Imprime na consola o tabuleiro mostrando apenas os tiros válidos já efetuados (marcados com 'X').
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Prints the board showing the fleet // Imprime na consola o tabuleiro mostrando a posição de todos os navios da frota (marcados com '#').
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
