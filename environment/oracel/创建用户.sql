CREATE USER ruoyi_cloud IDENTIFIED BY 123456;
GRANT CONNECT, RESOURCE, dba TO ruoyi_cloud;
SELECT tablespace_name, file_name
FROM dba_data_files;
CREATE TABLESPACE ruoyi_cloud_tablespace DATAFILE 'C:\APP\TABLESPACE\ruoyi_cloud_tablespace.dbf' SIZE 100 M;
ALTER USER ruoyi_cloud DEFAULT TABLESPACE ruoyi_cloud_tablespace;
