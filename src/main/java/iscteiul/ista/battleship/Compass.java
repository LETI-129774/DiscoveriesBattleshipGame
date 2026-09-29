/**
 * Representa os pontos cardeais utilizados no jogo para indicar direções.
 */
package iscteiul.ista.battleship;

/**
 * Cada direção está associada a um carácter que a identifica.
 *
 * <ul>
 * <li>'n' - Norte</li>
 * <li>'s' - Sul</li>
 * <li>'e' - Este</li>
 * <li>'o' - Oeste</li>
 * <li>'u' - Desconhecida</li>
 * </ul>
 *
 * @author fba
 */
public enum Compass {

    /** Direção Norte. */
    NORTH('n'), 
    /** Direção Sul. */
    SOUTH('s'), 
    /** Direção Este. */
    EAST('e'), 
    /** Direção Oeste. */
    WEST('o'), 
    /** Direção desconhecida ou inválida. */
    UNKNOWN('u');

    /** Carácter associado à direção. */
    private final char c;

    /**
     * Cria uma direção associada a um carácter.
     *
     * @param c carácter que representa a direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o carácter associado à direção.
     *
     * @return o carácter que representa a direção
     */
    public char getDirection() {
        return c;
    }

    /**
     * Devolve a representação textual da direção.
     *
     * @return uma cadeia de caracteres contendo o símbolo da direção 
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter numa direção.
     *
     * @param ch carácter a converter
     * @return a direção correspondente ao carácter fornecido;
     *         {@code UNKNOWN} caso o carácter não represente
     *         nenhuma direção válida
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
