/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.server.fedora.valueobj;

import java.sql.Connection;

import javax.xml.bind.Marshaller;

// TODO: Auto-generated Javadoc
/**
 * Konfigurace konvertoru.
 *
 * @author xholcik TODO: smazat celou tridu?
 */
public class ConvertorConfig {

	/** The marshaller. */
	private Marshaller marshaller;

	/** The import folder. */
	private String importFolder;

	/** The export folder. */
	private String exportFolder;

	/** The contract length. */
	private int contractLength;

	/** The default visibility. */
	private boolean defaultVisibility = false;

	/** The db connection. */
	private Connection dbConnection;

	/**
	 * Gets the import folder.
	 *
	 * @return the import folder
	 */
	public String getImportFolder() {
		return importFolder;
	}

	/**
	 * Sets the import folder.
	 *
	 * @param importFolder the new import folder
	 */
	public void setImportFolder(String importFolder) {
		this.importFolder = importFolder;
	}

	/**
	 * Gets the export folder.
	 *
	 * @return the export folder
	 */
	public String getExportFolder() {
		return exportFolder;
	}

	/**
	 * Sets the export folder.
	 *
	 * @param exportFolder the new export folder
	 */
	public void setExportFolder(String exportFolder) {
		this.exportFolder = exportFolder;
	}

	/**
	 * Gets the marshaller.
	 *
	 * @return the marshaller
	 */
	public Marshaller getMarshaller() {
		return marshaller;
	}

	/**
	 * Sets the marshaller.
	 *
	 * @param marshaller the new marshaller
	 */
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * Gets the contract length.
	 *
	 * @return the contract length
	 */
	public int getContractLength() {
		return contractLength;
	}

	/**
	 * Sets the contract length.
	 *
	 * @param contractLength the new contract length
	 */
	public void setContractLength(int contractLength) {
		this.contractLength = contractLength;
	}

	/**
	 * Checks if is default visibility.
	 *
	 * @return true, if is default visibility
	 */
	public boolean isDefaultVisibility() {
		return defaultVisibility;
	}

	/**
	 * Sets the default visibility.
	 *
	 * @param defaultVisibility the new default visibility
	 */
	public void setDefaultVisibility(boolean defaultVisibility) {
		this.defaultVisibility = defaultVisibility;
	}

	/**
	 * Gets the db connection.
	 *
	 * @return the db connection
	 */
	public Connection getDbConnection() {
		return dbConnection;
	}

	/**
	 * Sets the db connection.
	 *
	 * @param dbConnection the new db connection
	 */
	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

}