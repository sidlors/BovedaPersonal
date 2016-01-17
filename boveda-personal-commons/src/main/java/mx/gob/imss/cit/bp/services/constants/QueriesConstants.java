package mx.gob.imss.cit.bp.services.constants;

public final class QueriesConstants {
	
	/**
	 * Contructor privado
	 */
	private QueriesConstants(){
		
	}	

	/**
	 * Constante sql select
	 */
	public static final String SQL_SELECT = "SELECT * FROM ";
	/**
	 * Constante sql WHERE
	 */
	public static final String SQL_WHERE = " WHERE ";
	/**
	 * Constante sql LIKE
	 */
	public static final String SQL_LIKE = " LIKE ";
	/**
	 * Constante sql LIKE INI
	 */
	public static final String SQL_LIKE_IN = "\'%";
	/**
	 * Constante sql LIKE CLOSE
	 */
	public static final String SQL_LIKE_CLS = "%\'";
	/**
	 * Constante sql AND
	 */
	public static final String SQL_AND = " AND ";
	/**
	 * Constante sql TIMESTAMP
	 */
	public static final String SQL_TIMESTAMP = " >= TIMESTAMP '";
	/**
	 * Constante sql TIMESTAMP
	 */
	public static final String SQL_TIMESTAMP_LESS = " < TIMESTAMP '";
	/**
	 * Constante sql SQL_APOSTROFE
	 */
	public static final String SQL_APOSTROFE = "'";
	/**
	 * Constante sql SQL_EQUAL
	 */
	public static final String SQL_EQ = "=";
	/**
	 * Constante sql SQL_OPEN_PAREN
	 */
	public static final String SQL_OPEN_PAREN = "(";
	/**
	 * Constante sql SQL_CLOSE_PAREN
	 */
	public static final String SQL_CLOSE_PAREN = ")";
	/**
	 * Constante sql SQL_IN_FOLDER
	 */
	public static final String SQL_IN_FOLDER = " IN_FOLDER";
	/**
	 *  Constante SUFIJO_INICIO.
	 */
	public static final String SUFIJO_INICIO="_Ini";
	/**
	 * Constante SUFIJO_FIN.
	 */
	public static final String SUFIJO_FIN="_Fin";
	
}
