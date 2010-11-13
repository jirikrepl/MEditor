/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.server.fedora;

import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.BIBILO_MODS_URI;
import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.DC_NAMESPACE_URI;
import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.FEDORA_MODELS_URI;
import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.KRAMERIUS_URI;
import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.OAI_NAMESPACE_URI;
import static cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces.RDF_NAMESPACE_URI;

import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

// TODO: Auto-generated Javadoc
/**
 * The Class FedoraNamespaceContext.
 */
public class FedoraNamespaceContext implements NamespaceContext {

	/** The Constant MAP_PREFIX2URI. */
	private static final Map<String, String> MAP_PREFIX2URI = new IdentityHashMap<String, String>();
	
	/** The Constant MAP_URI2PREFIX. */
	private static final Map<String, String> MAP_URI2PREFIX = new IdentityHashMap<String, String>();

	static {
		MAP_PREFIX2URI.put("mods", BIBILO_MODS_URI);
		MAP_PREFIX2URI.put("dc", DC_NAMESPACE_URI);
		MAP_PREFIX2URI.put("fedora-models", FEDORA_MODELS_URI);
		MAP_PREFIX2URI.put("kramerius", KRAMERIUS_URI);
		MAP_PREFIX2URI.put("rdf", RDF_NAMESPACE_URI);
		MAP_PREFIX2URI.put("oai", OAI_NAMESPACE_URI);

		for (Map.Entry<String, String> entry : MAP_PREFIX2URI.entrySet()) {
			MAP_URI2PREFIX.put(entry.getValue(), entry.getKey());
		}
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	@Override
	public String getNamespaceURI(String arg0) {
		return MAP_PREFIX2URI.get(arg0.intern());
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
	 */
	@Override
	public String getPrefix(String arg0) {
		return MAP_URI2PREFIX.get(arg0.intern());
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
	 */
	@Override
	public Iterator getPrefixes(String arg0) {
		String prefixInternal = MAP_URI2PREFIX.get(arg0.intern());
		if (prefixInternal != null) {
			return Arrays.asList(prefixInternal).iterator();
		} else {
			return Collections.emptyList().iterator();
		}
	}
}