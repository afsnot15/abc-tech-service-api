
CREATE TABLE orders (
  id bigint NOT NULL AUTO_INCREMENT,
  operator_id bigint NOT NULL,
  end_order_location_id bigint DEFAULT NULL,
  start_order_location_id bigint DEFAULT NULL,
  CONSTRAINT pk_orders PRIMARY KEY (id));


CREATE TABLE orders_services (
  order_id BIGINT NOT NULL,
  service_id BIGINT NOT NULL,
  CONSTRAINT fk_order_assistance_id FOREIGN KEY (service_id) REFERENCES assistances (id),
  CONSTRAINT fk_assistance_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE order_location (
  id bigint NOT NULL AUTO_INCREMENT,
  date datetime(6) DEFAULT NULL,
  latitude double DEFAULT NULL,
  longitude double DEFAULT NULL,
  CONSTRAINT pk_id_order_locations PRIMARY KEY (id));

ALTER TABLE orders
    ADD CONSTRAINT fk_order_on_end_order_location FOREIGN KEY (end_order_location_id) REFERENCES order_location(id);

ALTER TABLE orders
    ADD CONSTRAINT fk_order_on_start_order_location FOREIGN KEY (start_order_location_id) REFERENCES order_location(id);
  