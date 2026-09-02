const API_BASE = 'http://localhost:8080/api';
const cartKey = 'rituale_cart';
let products = [];
let filter = 'todos';

const getToken = () => localStorage.getItem('rituale_token');
const setToken = (token, user) => {
  if (token) {
    localStorage.setItem('rituale_token', token);
    if (user) localStorage.setItem('rituale_user', JSON.stringify(user));
  } else {
    localStorage.removeItem('rituale_token');
    localStorage.removeItem('rituale_user');
  }
};

const getUser = () => {
  try {
    return JSON.parse(localStorage.getItem('rituale_user') || 'null');
  } catch {
    return null;
  }
};

const authHeaders = () => {
  const headers = { 'Content-Type': 'application/json' };
  const token = getToken();
  if (token) headers.Authorization = `Bearer ${token}`;
  return headers;
};

const getCart = () => JSON.parse(localStorage.getItem(cartKey) || '[]');
const saveCart = (c) => {
  localStorage.setItem(cartKey, JSON.stringify(c));
  updateCount();
};

function updateCount() {
  let total = 0;
  if (getToken()) {
    const cart = getCart();
    total = cart.reduce((sum, x) => sum + Number(x.quantity || 0), 0);
  } else {
    const cart = getCart();
    total = cart.reduce((sum, x) => sum + Number(x.quantity || 0), 0);
  }
  document.querySelectorAll('#cartCount').forEach((el) => {
    el.textContent = total;
  });
}

function cat(p) {
  return p.category?.name || p.category || 'Rituale';
}

function money(v) {
  return Number(v || 0).toFixed(2).replace('.', ',');
}

function productImage(p) {
  const candidate = p.imageUrl || p.image || p.images?.[0]?.url || p.productImage || 'https://touti.com.br/cdn/shop/files/barcelona-blaugrana-100ml-masculino-7614740.webp?v=1758416145';
  return candidate.startsWith('http') ? candidate : `https:${candidate}`;
}

function card(p) {
  const isFav = isFavorite(p.id);
  return `<article class="card">
    <div class="product-art"><img src="${productImage(p)}" alt="${p.name}" class="product-image" /></div>
    <div class="card-body">
      <span class="tag">${cat(p)}</span>
      <h3>${p.name}</h3>
      <p class="desc">${p.description || 'Uma fragrância selecionada pela Rituale.'}</p>
      <div class="price">R$ ${money(p.price)}</div>
      <div class="card-actions">
        <button class="add" onclick="add(${p.id})">Adicionar</button>
        <button class="details" onclick="location.href='produto.html?slug=' + encodeURIComponent('${p.slug}')">Ver</button>
        <button class="details" onclick="toggleFavorite(${p.id})">${isFav ? '★' : '☆'}</button>
      </div>
    </div>
  </article>`;
}

function render() {
  const el = document.querySelector('#products');
  if (!el) return;

  let list = products;
  if (filter !== 'todos') {
    list = list.filter((p) => cat(p).toLowerCase() === filter);
  }

  el.innerHTML = list.map(card).join('') || '<p>Nenhum produto encontrado.</p>';
}

async function load() {
  try {
    const res = await fetch(`${API_BASE}/public/products`);
    if (!res.ok) throw new Error('Falha ao carregar produtos');
    products = await res.json();
  } catch (error) {
    products = [];
  }
  render();
}

function isFavorite(productId) {
  const favs = JSON.parse(localStorage.getItem('rituale_favorites') || '[]');
  return favs.includes(Number(productId));
}

function toggleFavorite(productId) {
  const favs = JSON.parse(localStorage.getItem('rituale_favorites') || '[]');
  const next = favs.includes(Number(productId))
    ? favs.filter((id) => id !== Number(productId))
    : [...favs, Number(productId)];

  localStorage.setItem('rituale_favorites', JSON.stringify(next));
  render();
  alert(next.includes(Number(productId)) ? 'Produto adicionado aos favoritos.' : 'Produto removido dos favoritos.');
}

function add(id) {
  const p = products.find((x) => x.id === id);
  if (!p) return;

  if (getToken()) {
    fetch(`${API_BASE}/cart/items`, {
      method: 'POST',
      headers: authHeaders(),
      body: JSON.stringify({ productId: id, quantity: 1 })
    }).then(async (res) => {
      if (!res.ok) {
        const err = await res.text();
        throw new Error(err || 'Não foi possível adicionar ao carrinho');
      }
      alert('Produto adicionado ao carrinho.');
      updateCount();
    }).catch((err) => alert(err.message || 'Erro ao adicionar ao carrinho'));
    return;
  }

  const c = getCart();
  const i = c.findIndex((x) => x.id === id);
  if (i >= 0) c[i].quantity += 1;
  else c.push({ id: p.id, name: p.name, price: p.price, quantity: 1, slug: p.slug });
  saveCart(c);
  alert('Produto adicionado ao carrinho.');
}

function changeQty(i, delta) {
  const c = getCart();
  c[i].quantity += delta;
  if (c[i].quantity <= 0) c.splice(i, 1);
  saveCart(c);
  renderCart();
}

function removeItem(i) {
  const c = getCart();
  c.splice(i, 1);
  saveCart(c);
  renderCart();
}

async function renderCart() {
  const el = document.querySelector('#cart');
  if (!el) return;

  if (getToken()) {
    try {
      const res = await fetch(`${API_BASE}/cart`, { headers: authHeaders() });
      if (!res.ok) throw new Error();
      const data = await res.json();
      const items = data.items || [];
      const total = Number(data.total || 0);

      if (!items.length) {
        el.innerHTML = '<div class="summary"><p>Seu carrinho está vazio.</p><a class="btn gold" href="index.html#colecao">Explorar perfumes</a></div>';
        return;
      }

      el.innerHTML = items.map((item, index) => `
        <div class="cart-item">
          <div>
            <strong>${item.name}</strong>
            <div class="price">R$ ${money(item.unitPrice)}</div>
          </div>
          <div class="qty">
            <button onclick="changeQty(${index}, -1)">−</button>
            <span>${item.quantity}</span>
            <button onclick="changeQty(${index}, 1)">+</button>
            <button onclick="removeItem(${index})">×</button>
          </div>
        </div>
      `).join('') + `<div class="summary"><p>Total</p><strong>R$ ${money(total)}</strong><br><button class="btn gold" onclick="whatsapp()">Finalizar pelo WhatsApp</button></div>`;
      return;
    } catch {
      // fallback sem server
    }
  }

  const c = getCart();
  if (!c.length) {
    el.innerHTML = '<div class="summary"><p>Seu carrinho está vazio.</p><a class="btn gold" href="index.html#colecao">Explorar perfumes</a></div>';
    return;
  }

  let total = 0;
  el.innerHTML = c.map((x, i) => {
    total += x.price * x.quantity;
    return `<div class="cart-item"><div><strong>${x.name}</strong><div class="price">R$ ${money(x.price)}</div></div><div class="qty"><button onclick="changeQty(${i},-1)">−</button><span>${x.quantity}</span><button onclick="changeQty(${i},1)">+</button><button onclick="removeItem(${i})">×</button></div></div>`;
  }).join('') + `<div class="summary"><p>Total</p><strong>R$ ${money(total)}</strong><br><button class="btn gold" onclick="whatsapp()">Finalizar pelo WhatsApp</button></div>`;
}

function whatsapp() {
  const c = getCart();
  if (!c.length) return;

  const lines = c.map((x) => `• ${x.name} — ${x.quantity}x R$ ${money(x.price)}`);
  const total = c.reduce((sum, x) => sum + x.price * x.quantity, 0);
  const msg = `Olá, Rituale! Quero fazer este pedido:\n\n${lines.join('\n')}\n\nTotal: R$ ${money(total)}\n\nAguardo as instruções para finalizar.`;
  window.open(`https://wa.me/5500000000000?text=${encodeURIComponent(msg)}`, '_blank');
}

async function renderFavorites() {
  const el = document.querySelector('#favorites');
  if (!el) return;

  const ids = JSON.parse(localStorage.getItem('rituale_favorites') || '[]');
  if (!ids.length) {
    el.innerHTML = '<p>Nenhum favorito ainda.</p>';
    return;
  }

  try {
    const res = await fetch(`${API_BASE}/public/products`);
    if (!res.ok) throw new Error();
    const allProducts = await res.json();
    const favProducts = allProducts.filter((p) => ids.includes(Number(p.id)));
    el.innerHTML = favProducts.length ? favProducts.map(card).join('') : '<p>Nenhum favorito encontrado.</p>';
  } catch {
    el.innerHTML = '<p>Não foi possível carregar os favoritos.</p>';
  }
}

function bindFilters() {
  document.querySelectorAll('.filter').forEach((button) => {
    button.addEventListener('click', () => {
      document.querySelectorAll('.filter').forEach((item) => item.classList.remove('active'));
      button.classList.add('active');
      filter = button.dataset.filter;
      render();
    });
  });
}

function bindAuthForms() {
  const loginForm = document.getElementById('loginForm');
  if (loginForm) {
    loginForm.addEventListener('submit', async (event) => {
      event.preventDefault();
      const email = document.getElementById('loginEmail').value.trim();
      const password = document.getElementById('loginPassword').value.trim();

      try {
        const res = await fetch(`${API_BASE}/auth/login`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ email, password })
        });

        const data = await res.json();
        if (!res.ok) throw new Error(data.message || 'Erro ao fazer login');

        setToken(data.token, { name: data.name, role: data.role, email });
        window.location.href = 'index.html';
      } catch (error) {
        alert(error.message || 'Erro ao fazer login');
      }
    });
  }

  const registerForm = document.getElementById('registerForm');
  if (registerForm) {
    registerForm.addEventListener('submit', async (event) => {
      event.preventDefault();
      const name = document.getElementById('registerName').value.trim();
      const email = document.getElementById('registerEmail').value.trim();
      const password = document.getElementById('registerPassword').value.trim();

      try {
        const res = await fetch(`${API_BASE}/auth/register`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ name, email, password })
        });

        const data = await res.json();
        if (!res.ok) throw new Error(data.message || 'Erro ao criar conta');

        setToken(data.token, { name: data.name, role: data.role, email });
        window.location.href = 'index.html';
      } catch (error) {
        alert(error.message || 'Erro ao criar conta');
      }
    });
  }
}

document.addEventListener('DOMContentLoaded', () => {
  bindFilters();
  bindAuthForms();
  updateCount();
  renderCart();
  renderFavorites();
  load();
});
