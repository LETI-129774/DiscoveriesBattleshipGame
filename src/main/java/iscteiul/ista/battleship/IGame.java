package iscteiul.ista.battleship;

import java.util.List;

/**
 * Define o contrato para a gestão de uma partida de Batalha Naval.
 * Declara os métodos essenciais para processar as jogadas (tiros) sobre a frota adversária e consultar as estatísticas e o estado do jogo.
 * 
 * @author Laura 129778
 * @version 1.0
 */
public interface IGame {
    
    /**
     * Efetua um tiro sobre uma determinada posição no tabuleiro do adversário.
     * Regista o resultado da jogada, permitindo informar o jogador se o tiro atingiu um navio ou se foi um "tiro na água".
     * @param pos As coordenadas (IPosition) alvo do tiro na grelha.
     * @return O navio (IShip) caso este tiro o tenha afundado por completo; null caso tenha sido água ou apenas um impacto sem afundar[cite: 1].
     */
    IShip fire(IPosition pos);

    /**
     * Obtém o histórico de todas as posições onde foram realizados tiros válidos.
     * @return Uma lista com as posições (IPosition) disparadas.
     */
    List<IPosition> getShots();

    /**
     * Obtém o número de vezes que o jogador disparou para coordenadas repetidas no tabuleiro.
     * @return O número total de tiros repetidos.
     */
    int getRepeatedShots();

    /**
     * Obtém o número de tiros inválidos (por exemplo, tentativas de disparo fora dos limites da grelha de 10x10 quadrados).
     * @return O número total de tiros inválidos.
     */
    int getInvalidShots();

    /**
     * Obtém o número total de tiros certeiros, ou seja, que atingiram partes de navios da frota.
     * @return O número total de tiros com impacto num navio.
     */
    int getHits();

    /**
     * Obtém o número total de navios do adversário que já foram completamente afundados durante a partida.
     * @return A quantidade de navios afundados.
     */
    int getSunkShips();

    /**
     * Obtém o número de navios que ainda continuam intactos ou parcialmente atingidos (a flutuar).
     * O jogo é ganho quando não restar nenhum navio a flutuar.
     * @return A quantidade de navios restantes na frota.
     */
    int getRemainingShips();

    /**
     * Imprime visualmente na consola o tabuleiro de jogo, mostrando os tiros válidos já efetuados pelo jogador.
     */
    void printValidShots();

    /**
     * Imprime visualmente na consola o tabuleiro, revelando a localização completa de toda a frota de navios.
     */
    void printFleet();
}
