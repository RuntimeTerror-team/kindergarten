CREATE TABLE IF NOT EXISTS `logging_event` (
                               `timestmp` BIGINT NOT NULL,
                               `formatted_message` LONGVARCHAR NOT NULL,
                               `logger_name` VARCHAR(256) NOT NULL,
                               `level_string` VARCHAR(256) NOT NULL,
                               `thread_name` VARCHAR(256),
                               `reference_flag` SMALLINT,
                               `arg0` VARCHAR(256),
                               `arg1` VARCHAR(256),
                               `arg2` VARCHAR(256),
                               `arg3` VARCHAR(256),
                               `caller_filename` VARCHAR(256),
                               `caller_class` VARCHAR(256),
                               `caller_method` VARCHAR(256),
                               `caller_line` CHAR(4),
                               `event_id` IDENTITY NOT NULL);

CREATE TABLE IF NOT EXISTS logging_event_property (
                                        event_id BIGINT NOT NULL,
                                        mapped_key  VARCHAR(254) NOT NULL,
                                        mapped_value LONGVARCHAR,
                                        PRIMARY KEY(event_id, mapped_key),
                                        FOREIGN KEY (event_id) REFERENCES logging_event(event_id));

CREATE TABLE IF NOT EXISTS logging_event_exception (
                                         event_id BIGINT NOT NULL,
                                         i SMALLINT NOT NULL,
                                         trace_line VARCHAR(256) NOT NULL,
                                         PRIMARY KEY(event_id, i),
                                         FOREIGN KEY (event_id) REFERENCES logging_event(event_id));