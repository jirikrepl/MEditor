/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.mzk.editor.server.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rumanekm
 */
public class UserDaoNewImpl extends AbstractDAO implements UserDaoNew {

    @Override
    public Long getUserIdFromPrincipal(String principal) {
        PreparedStatement selectSt = null;
        Long userId = new Long(-1);

        String sql = "SELECT id FROM editor_user WHERE principal=(?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, principal);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                userId = rs.getLong("id");
            } else {
                String insertSql = "INSERT INTO editor_user (principal) VALUES (?)";
                PreparedStatement statement1 = getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                statement1.setString(1, principal);
                statement1.execute();
                ResultSet resKey = statement.getGeneratedKeys();
                if (resKey != null && resKey.next()) {
                    userId = resKey.getLong(1);
                }
            }


        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return userId;
    }
}



