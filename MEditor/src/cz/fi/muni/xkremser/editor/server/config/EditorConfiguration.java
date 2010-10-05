package cz.fi.muni.xkremser.editor.server.config;

import org.apache.commons.configuration.Configuration;

public abstract class EditorConfiguration {
	public static class Constants {
		public static final String INPUT_QUEUE = "input_queue";
		public static final String DOCUMENT_TYPES = "document_types";
		public static final String[] DOCUMENT_DEFAULT_TYPES = { "periodical", "monographs" };
		public static final String Z3950_PROFILE = "z39.50_profile";
		public static final String Z3950_HOST = "z39.50_host";
		public static final String Z3950_PORT = "z39.50_port";
		public static final String Z3950_BASE = "z39.50_base";
		public static final String[] Z3950_DEFAULT_HOSTS = { "aleph.mzk.cz", "aleph.muni.cz", "sigma.nkp.cz", "sigma.nkp.cz" };
		public static final String[] Z3950_DEFAULT_PORTS = { "9991", "9991", "9909", "9909" };
		public static final String[] Z3950_DEFAULT_BASES = { "MZK01-UTF", "MUB01", "SKC", "NKC" };
		public static final String FEDORA_HOST = "fedora_host";
		public static final String FEDORA_DEFAULT_HOST = "10.2.2.219"; // virtual
																																		// image
		// public static final String FEDORA_DEFAULT_HOST = "195.113.155.50"; //
		// freon.mzk.cz
		public static final String DB_HOST = "db_host";
		public static final String DB_PORT = "db_port";
		public static final String DB_LOGIN = "db_login";
		public static final String DB_PASSWORD = "db_password";
		public static final String DB_NAME = "db_name";
		public static final String DB_HOST_DEFAULT = "localhost";
		public static final String DB_PORT_DEFAULT = "5432";
		public static final String DB_LOGIN_DEFAULT = "meditor";
		public static final String DB_PASSWORD_DEFAULT = "123456";
		public static final String DB_NAME_DEFAULT = "meditor";

	}

	public abstract Configuration getConfiguration();

	public abstract void setConfiguration(Configuration configuration);

	public String getScanInputQueuePath() {
		return getConfiguration().getString(Constants.INPUT_QUEUE);
	}

	public String getZ3950Profile() {
		return getConfiguration().getString(Constants.Z3950_PROFILE);
	}

	public String getZ3950Host() {
		return getConfiguration().getString(Constants.Z3950_HOST);
	}

	public String getZ3950Port() {
		return getConfiguration().getString(Constants.Z3950_PORT);
	}

	public String getZ3950Base() {
		return getConfiguration().getString(Constants.Z3950_BASE);
	}

	public String getFedoraHost() {
		return getConfiguration().getString(Constants.FEDORA_HOST);
	}

	public String[] getDocumentTypes() {
		return getConfiguration().getStringArray(Constants.DOCUMENT_TYPES);
	}

	public String getDBHost() {
		return getConfiguration().getString(Constants.DB_HOST);
	}

	public String getDBPort() {
		return getConfiguration().getString(Constants.DB_PORT);
	}

	public String getDBLogin() {
		return getConfiguration().getString(Constants.DB_LOGIN);
	}

	public String getDBPassword() {
		return getConfiguration().getString(Constants.DB_PASSWORD);
	}

	public String getDBName() {
		return getConfiguration().getString(Constants.DB_NAME);
	}

}
