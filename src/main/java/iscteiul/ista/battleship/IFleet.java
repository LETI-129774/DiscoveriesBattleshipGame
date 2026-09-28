package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa a frota de navios de um jogador no jogo Batalha Naval.
 * Define os métodos necessários para gerir a colocação e o estado dos navios no tabuleiro.
 * 
 * @author Laura 129778
 * @version 1.0
 */
public interface IFleet {
    
    /**
     * Tamanho do tabuleiro (grelha) do jogo. 
     * Neste caso, representa uma grelha quadriculada de 10x10 quadrados.
     */
    Integer BOARD_SIZE = 10;
    
    /**
     * Número máximo de navios que compõem a frota de um jogador.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Obtém a lista de todos os navios que compõem a frota.
     * @return Uma lista contendo todos os objetos IShip da frota.
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um novo navio à frota.
     * O navio só deve ser adicionado se não colidir com outros navios já existentes
     * e se respeitar os limites do tabuleiro.
     * @param s O navio (IShip) a ser adicionado.
     * @return true se o navio foi adicionado com sucesso, false caso contrário.
     */
    boolean addShip(IShip s);

    /**
     * Obtém uma lista de navios pertencentes a uma categoria específica.
     * Útil para procurar todos os navios de um determinado tipo (ex: "Galeao", "Fragata").
     * @param category A categoria/tipo de navio a procurar.
     * @return Uma lista de navios que correspondem à categoria fornecida.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Obtém uma lista com todos os navios da frota que ainda não foram totalmente afundados.
     * @return Uma lista de navios (IShip) que ainda estão a flutuar.
     */
    List<IShip> getFloatingShips();

    /**
     * Verifica se existe algum navio da frota numa determinada posição do tabuleiro.
     * @param pos A coordenada a ser verificada.
     * @return O navio (IShip) que se encontra nessa posição, ou null se a posição estiver vazia (água).
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime na consola o estado atual de toda a frota.
     * Geralmente mostra que navios existem, as suas posições e se já foram afundados ou não.
     */
    void printStatus();
}
