package cz.mzk.editor.server.handler;

import com.gwtplatform.dispatch.shared.ActionException;
import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DescriptionDAO;
import cz.mzk.editor.server.util.ServerUtils;
import cz.mzk.editor.server.util.UserProvider;
import cz.mzk.editor.shared.rpc.action.PutDescription;
import cz.mzk.editor.shared.rpc.action.PutDescriptionAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by rumanekm on 7/20/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class PutDescriptionHandlerTest {

    @Mock
    private DescriptionDAO descriptionDAO;

    @Mock
    private UserProvider userProvider;

    @Mock
    private ServerUtils serverUtils;

    private PutDescriptionHandler service;



    @Before
    public void setUp() {
        this.descriptionDAO = mock(DescriptionDAO.class);
        this.userProvider = mock(UserProvider.class);
        this.serverUtils = mock(ServerUtils.class);
        this.service = new PutDescriptionHandler(descriptionDAO, userProvider, serverUtils);

    }

    @Test
    public void testPutDescription() throws DatabaseException, ActionException {
        when(descriptionDAO.putCommonDescription("uuid:530719f5-ee95-4449-8ce7-12b0f4cadb22", "test description", new Long(3))).thenReturn(true);
        when(userProvider.checkUserRightOrAll(Constants.EDITOR_RIGHTS.PUBLISH)).thenReturn(true);
        when(userProvider.getUserId()).thenReturn(new Long(3));


        PutDescriptionAction action = new PutDescriptionAction("uuid:530719f5-ee95-4449-8ce7-12b0f4cadb22", "test description", true);
        service.execute(action, null);
        verify(descriptionDAO, times(1)).putCommonDescription("uuid:530719f5-ee95-4449-8ce7-12b0f4cadb22", "test description", new Long(3));
    }

}
