CREATE TABLE IF NOT EXISTS merchant (
  merchant_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  merchant_name VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  info VARCHAR DEFAULT '-',
  merchant_timestamp VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
  product_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  merchant_id BIGINT NOT NULL,
  product_name VARCHAR NOT NULL,
  sex VARCHAR NOT NULL,
  price INT NOT NULL,
  is_available BOOLEAN NOT NULL,
  category VARCHAR NOT NULL,
  product_timestamp VARCHAR NOT NULL,
  CONSTRAINT fk_merchant_id
      FOREIGN KEY(merchant_id) 
	      REFERENCES merchant(merchant_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_image (
  image_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  product_id BIGINT NOT NULL,
  is_thumbnail BOOLEAN NOT NULL,
  image_path VARCHAR NOT NULL,
  image_timestamp VARCHAR NOT NULL,
  CONSTRAINT fk_product_id_image
      FOREIGN KEY(product_id) 
	      REFERENCES product(product_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_attributes (
  attributes_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  product_id BIGINT NOT NULL,
  product_description VARCHAR DEFAULT '-',
  color VARCHAR DEFAULT '-',
  product_size VARCHAR DEFAULT '-',
  brand VARCHAR DEFAULT '-',
  material VARCHAR DEFAULT '-',
  CONSTRAINT fk_product_id
      FOREIGN KEY(product_id) 
	      REFERENCES product(product_id)
        ON DELETE CASCADE
);

CREATE or REPLACE FUNCTION search_by_query(query_in VARCHAR(25))
RETURNS TABLE (
  product_id BIGINT,
  merchant_id BIGINT,
  product_name VARCHAR,
  merchant_name VARCHAR,
  sex VARCHAR,
  price INT,
  category VARCHAR,
  color VARCHAR,
  product_size VARCHAR,
  brand VARCHAR,
  material VARCHAR,
  image_path VARCHAR,
  product_timestamp VARCHAR
) 
LANGUAGE sql
AS $$
SELECT
  product.product_id,
  merchant.merchant_id,
  product.product_name,
  merchant.merchant_name,
  product.sex,
  product.price,
  product.category,
  product_attributes.color,
  product_attributes.product_size,
  product_attributes.brand,
  product_attributes.material,
  product_image.image_path,
  product.product_timestamp
  FROM product INNER JOIN merchant ON product.merchant_id = merchant.merchant_id
  INNER JOIN product_attributes ON product.product_id = product_attributes.product_id
  INNER JOIN product_image ON product.product_id = product_image.product_id
  WHERE product.is_available = TRUE AND product_image.is_thumbnail = TRUE AND (
        (LOWER(product.product_name) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product.sex) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product.category) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(CAST(product.price AS VARCHAR(25))) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(merchant.merchant_name) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product_attributes.color) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product_attributes.product_size) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product_attributes.brand) LIKE CONCAT(LOWER(query_in), '%')) OR
        (LOWER(product_attributes.material) LIKE CONCAT(LOWER(query_in), '%'))
);
$$;
