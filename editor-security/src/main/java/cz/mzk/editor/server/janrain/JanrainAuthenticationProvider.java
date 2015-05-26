
package cz.mzk.editor.server.janrain;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.USER_IDENTITY_TYPES;
import cz.mzk.editor.server.EditorUserAuthentication;
import cz.mzk.editor.server.DAO.DatabaseException;

import javax.inject.Inject;

/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

/**
 * @author Matous Jobanek
 * @version Nov 14, 2012
 */
public class JanrainAuthenticationProvider
        implements AuthenticationProvider {

    @Inject
    private JanrainClient janrainClient;

    /**
     * {@inheritDoc}
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String openId = (String) authentication.getPrincipal();
        Long userId = -1L;
        if (openId != null)
	    try {
		userId = janrainClient.getUserId(openId);
	    } catch (DatabaseException e) {
		throw new AuthenticationServiceException(Constants.CANNOT_CONNECT_TO_DB);
	    } catch (SQLException e) {
		throw new InternalAuthenticationServiceException(Constants.DB_ERROR, e);
	    }

        if (userId < 0) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException(Constants.INVALID_LOGIN_OR_PASSWORD);
        } else if (userId == 0) {
            EditorUserAuthentication customAuthentication =
                    new EditorUserAuthentication("ROLE_USER", authentication, USER_IDENTITY_TYPES.OPEN_ID);
            customAuthentication.setAuthenticated(false);
            customAuthentication.setToAdd(true);
            return customAuthentication;
        } else {
            EditorUserAuthentication customAuthentication =
                    new EditorUserAuthentication("ROLE_USER", authentication, USER_IDENTITY_TYPES.OPEN_ID);
            customAuthentication.setAuthenticated(true);
            return customAuthentication;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> authentication) {
        return OpenIDAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
