DO
$$
BEGIN
  IF NOT EXISTS(
      SELECT
      FROM pg_user
      WHERE usename = 'smigic')
  THEN
    CREATE USER smigic
    WITH PASSWORD 'smigic';
  END IF;
  CREATE SCHEMA IF NOT EXISTS smigicdb;
  ALTER SCHEMA smigicdb
  OWNER TO smigic;
  GRANT ALL PRIVILEGES ON SCHEMA smigicdb TO smigic;
  GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA smigicdb TO smigic;
  GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA smigicdb TO smigic;
END
$$;