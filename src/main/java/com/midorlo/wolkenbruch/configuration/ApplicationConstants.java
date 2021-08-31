package com.midorlo.wolkenbruch.configuration;

public interface ApplicationConstants {

    String REPOSITORY_SCAN_PARENT      = "com.midorlo.wolkenbruch.repository";
    String APPLICATION_PROPERTIES_BASE = "wolkenbruch";
    String SPRING_SECURITY_OVERRIDE    = "userDetailsService";

    interface TableNames {

        String ROLES             = "roles";
        String PRIVILEGES        = "privileges";
        String FILE_REFERENCES   = "file_references";
        String FILE_TYPES        = "file_types";
        String FOLDER_REFERENCES = "folder_references";
        String SYSTEM_SETTINGS   = "system_settings";
        String accounts          = "accounts";
    }

    interface RelationNames {

        String ROLES_TO_PRIVILEGES = "roles_to_privileges";
        String ACCOUNTS_PRIVILEGES = "accounts_privileges";
        String ACCOUNTS_ROLES      = "accounts_roles";
    }

    interface RelationColumnNames {

        String ID_ROLE      = "id_role";
        String ID_PRIVILEGE = "id_privilege";
        String ID_ACCOUNT   = "id_account";
    }

    interface TableColumnNames {

        String ID                 = "id";
        String ID_ACCOUNT_CREATED    = "id_account_created";
        String ID_ACCOUNT_CHANGED    = "id_account_changed";
        String TIME_CREATED       = "time_created";
        String TIME_CHANGED       = "time_changed";
        String NAME               = "name";
        String NAME_ORIGINAL      = "name_original";
        String EXTENSION          = "extension";
        String SIZE_BYTES         = "size_bytes";
        String LOCATION           = "location";
        String DESCRIPTION        = "description";
        String VALUE              = "value";
        String DATATYPE           = "datatype";
        String password_encrypted = "password_hash";
        String active             = "email_confirmed";
    }
}
