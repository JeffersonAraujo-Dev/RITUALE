-- Categorias de produtos
INSERT INTO categories (name, slug)
VALUES
    ('Masculino', 'masculino'),
    ('Feminino', 'feminino'),
    ('Unissex', 'unissex')
ON CONFLICT (slug) DO NOTHING;

-- Base de catálogo real da Touti
INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Barcelona - Més Que Un Club - 100ml - Feminino', 'mes-que-un-club-desodorante-colonia-100ml-feminino',
       'Desodorante colônia feminina com assinatura elegante e marcante.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Barcelona Blaugrana 100ml', 'blaugrana-desodorante-colonia-100ml',
       'Perfume masculino com presença olfativa intensa e identidade football-inspired.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Barcelona Visca El Barça - 100ml', 'visca-el-barca-desodorante-colonia-100ml',
       'Fragrância masculina com personalidade, presença e boa fixação.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Bliss - 100ml - Feminino', 'bliss-desodorante-colonia-100ml-feminino',
       'Perfume feminino delicado, sofisticado e envolvente.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Charm 100 ml - Feminino', 'charm-100-ml-feminino',
       'Fragrância feminina elegante, suave e moderna.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Desire - 100ml - Feminino', 'desire-desodorante-colonia-100ml-feminino',
       'Perfume feminino com aroma intenso e irresistível.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Juventus - Bianconeri - 100ml', 'bianconeri-desodorante-colonia-100ml',
       'Perfume masculino com personalidade esportiva e elegante.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Juventus - Fino Alla Fine - 100ml', 'fino-alla-fine-desodorante-colonia-100ml',
       'Perfume masculino refinado e sofisticado.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'My Soul - 100 ml - Feminino', 'my-soul-desodorante-colonia-100-ml-feminino',
       'Fragrância feminina com presença leve, elegante e memorável.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Marina Ferrari - 90ml - Feminino', 'marina-ferrari-desodorante-colonia-90ml-feminino',
       'Perfume feminino sofisticado, moderno e com grande presença.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'feminino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Manchester City - Blue Moon - 100ml', 'blue-moon-desodorante-colonia-100ml',
       'Perfume masculino contemporâneo com presença marcante.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Manchester City - Cityzens 100ml', 'cityzens-desodorante-colonia-100ml',
       'Fragrância masculina com um perfil moderno e forte.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'NFL - Quarterback 100ml', 'nfl-quarterback-100ml',
       'Perfume masculino com perfil esportivo, intenso e memorável.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'NFL - TouchDown 100ml', 'nfl-quarterback-100ml-copia',
       'Versão masculina com assinatura envolvente e esportiva.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'masculino'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO products (category_id, name, slug, description, price, stock, active)
SELECT c.id, 'Paris Est Magique - 100ml', 'paris-est-magique-desodorante-colonia-100ml',
       'Fragrância unissex com elegância, intensidade e versatilidade.', 149.90, 10, TRUE
FROM categories c WHERE c.slug = 'unissex'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/barcelona-mes-que-un-club-100ml-feminino-9386852.webp?v=1758416145', p.name, 0
FROM products p WHERE p.slug = 'mes-que-un-club-desodorante-colonia-100ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/barcelona-blaugrana-100ml-masculino-7614740.webp?v=1758416145', p.name, 0
FROM products p WHERE p.slug = 'blaugrana-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/visca-el-barca-100ml-masculino-2491622.webp?v=1770318919', p.name, 0
FROM products p WHERE p.slug = 'visca-el-barca-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/bliss-100ml-feminino-6780681.png?v=1758416150', p.name, 0
FROM products p WHERE p.slug = 'bliss-desodorante-colonia-100ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/charm-100-ml-feminino-6100292.webp?v=1764074465', p.name, 0
FROM products p WHERE p.slug = 'charm-100-ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/desire-100ml-feminino-7481102.webp?v=1758416149', p.name, 0
FROM products p WHERE p.slug = 'desire-desodorante-colonia-100ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/juventus-bianconeri-100ml-4372120.webp?v=1758416227', p.name, 0
FROM products p WHERE p.slug = 'bianconeri-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/juventus-fino-alla-fine-100ml-7756620.png?v=1758416227', p.name, 0
FROM products p WHERE p.slug = 'fino-alla-fine-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/my-soul-100-ml-feminino-4821283.webp?v=1763825928', p.name, 0
FROM products p WHERE p.slug = 'my-soul-desodorante-colonia-100-ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/marina-ferrari-desodorante-colonia-90ml-feminino-5749891.webp?v=1758416150', p.name, 0
FROM products p WHERE p.slug = 'marina-ferrari-desodorante-colonia-90ml-feminino';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/blue-moon-desodorante-colonia-100ml-2553458.webp?v=1758416218', p.name, 0
FROM products p WHERE p.slug = 'blue-moon-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/cityzens-desodorante-colonia-100ml-6885210.webp?v=1758416218', p.name, 0
FROM products p WHERE p.slug = 'cityzens-desodorante-colonia-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/nfl-quarterback-100ml-6733279.webp?v=1783017668', p.name, 0
FROM products p WHERE p.slug = 'nfl-quarterback-100ml';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/nfl-touchdown-100ml-3667178.png?v=1783017668', p.name, 0
FROM products p WHERE p.slug = 'nfl-quarterback-100ml-copia';

INSERT INTO product_images (product_id, url, alt_text, position)
SELECT p.id, 'https://touti.com.br/cdn/shop/files/paris-est-magique-100ml-2349201.webp?v=1758416145', p.name, 0
FROM products p WHERE p.slug = 'paris-est-magique-desodorante-colonia-100ml';
