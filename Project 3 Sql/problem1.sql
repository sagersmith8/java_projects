create index user_last_name_index on users(last_name);
create index user_first_name_index on users(first_name);
select index_name from user_indexes;

/*
INDEX_NAME
-----------------------
USER_FIRST_NAME_INDEX
USER_LAST_NAME_INDEX
 */
