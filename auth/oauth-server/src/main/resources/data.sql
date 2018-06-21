INSERT INTO `oauth_client_details` (`client_id`, `client_secret`, `scope`, `authorized_grant_types`, `access_token_validity`, `additional_information`)
VALUES
('acme', 'acmesecret', 'read', 'authorization_code,password,refresh_token,implicit', '900', '{}')
ON DUPLICATE key UPDATE
client_secret = VALUES(`client_secret`),
scope = VALUES(`scope`),
authorized_grant_types = VALUES(`authorized_grant_types`),
access_token_validity = VALUES(`access_token_validity`),
additional_information = VALUES(`additional_information`);


INSERT INTO users VALUES ('admin', 'admin', 1);
INSERT INTO authorities VALUES ('admin', 'ROLE_USER');