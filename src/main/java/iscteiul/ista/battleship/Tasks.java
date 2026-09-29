package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilitária de tarefas e testes incrementais
 * Contém rotinas e simulações para testar o comportamento de criação de navios,
 * montagem de frotas, validação de comandos de utilizador e processamento de rajadas de tiro.
 */
public class Tasks {

    /** Logger do Log4j2 para registo e visualização das mensagens e estados no ecran. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Número fixo de disparos efetuados por cada rajada de tiros (3). */
    private static final int NUMBER_SHOTS = 3;

    /** Mensagem de encerramento apresentada ao utilizador ao sair/desistir. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Comando para solicitar a criação de uma nova frota. */
    private static final String NOVAFROTA = "nova";

    /** Comando para encerrar o jogo ou desistir da partida. */
    private static final String DESISTIR = "desisto";

    /** Comando para executar uma rajada de tiros sobre o tabuleiro adversário. */
    private static final String RAJADA = "rajada";

    /** Comando para consultar o histórico de tiros válidos disparados. */
    private static final String VERTIROS = "ver";

    /** Comando para revelar a posição de toda a frota (modo de depuração/batota). */
    private static final String BATOTA = "mapa";

    /** Comando para exibir o estado atual das embarcações da frota. */
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Executa a Tarefa A: testa a construção individual de navios.
     * Lê posições do utilizador e verifica se o navio as ocupa.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Executa a Tarefa B: testa o processo interativo de construção de frotas e consulta de estado.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executa a Tarefa C: testa a criação de frotas, consulta de estado e o comando de revelação ("mapa").
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executa a Tarefa D: simula o fluxo completo de jogo, incluindo a criação da frota,
     * consulta do estado e disparo de rajadas de 3 tiros.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Realiza a montagem de uma nova frota a partir dos dados lidos da entrada standard.
     *
     * @param in O {@link Scanner} utilizado para a leitura dos dados fornecidos pelo utilizador.
     * @return A instância de {@link Fleet} construída com os navios adicionados com sucesso.
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê da entrada os dados referentes a um navio (tipo, posição e orientação),
     * constrói a respetiva instância e devolve-a.
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê um par de coordenadas (linha e coluna) da entrada e devolve um objeto de posição.
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Processa uma rajada contendo 3 disparos sobre a frota no contexto do jogo ativo.
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
