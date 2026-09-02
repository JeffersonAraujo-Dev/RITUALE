const API = 'http://localhost:8080/api/public/products';

const money = value => Number(value).toLocaleString('pt-BR',{style:'currency',currency:'BRL'});

function productCard(p){
  return `<article class="product-card">
    <div class="product-image">RITUALE</div>
    <h3>${p.name}</h3>
    <p>${p.description || ''}</p>
    <div class="price">${money(p.price)}</div>
    <div class="card-actions">
      <button class="button" onclick='addCart(${JSON.stringify(p)})'>Adicionar</button>
      <button class="button" onclick='toggleFavorite(${p.id})'>♡</button>
    </div>
  </article>`;
}

async function loadProducts(){
  try{
    const res=await fetch(API); const products=await res.json();
    const list=document.querySelector('#product-list');
    const featured=document.querySelector('#featured-products');
    if(list) list.innerHTML=products.map(productCard).join('');
    if(featured) featured.innerHTML=products.slice(0,4).map(productCard).join('');
  }catch(e){
    const target=document.querySelector('#product-list,#featured-products');
    if(target) target.innerHTML='<p>API offline. Inicie o backend em http://localhost:8080.</p>';
  }
}

function getCart(){return JSON.parse(localStorage.getItem('rituale_cart')||'[]')}
function saveCart(c){localStorage.setItem('rituale_cart',JSON.stringify(c));}
function addCart(p){
  const c=getCart(); const item=c.find(x=>x.id===p.id);
  if(item)item.quantity++; else c.push({...p,quantity:1}); saveCart(c); alert('Produto adicionado ao carrinho.');
}
function toggleFavorite(id){
  let f=JSON.parse(localStorage.getItem('rituale_favorites')||'[]');
  f=f.includes(id)?f.filter(x=>x!==id):[...f,id]; localStorage.setItem('rituale_favorites',JSON.stringify(f)); alert('Favoritos atualizado.');
}
function renderCart(){
  const el=document.querySelector('#cart'); if(!el)return;
  const c=getCart();
  if(!c.length){el.innerHTML='<p>Seu carrinho está vazio.</p>';return}
  let total=0;
  const rows=c.map((x,i)=>{const sub=Number(x.price)*x.quantity;total+=sub;return `<div class="product-card"><h3>${x.name}</h3><p>${x.quantity} × ${money(x.price)}</p><strong>${money(sub)}</strong><br><button onclick="removeCart(${i})">Remover</button></div>`}).join('');
  const text=encodeURIComponent('Olá! Gostaria de fazer um pedido na Rituale.\n\n'+c.map(x=>`${x.quantity}x ${x.name} — ${money(x.price)}`).join('\n')+'\n\nTotal: '+money(total));
  el.innerHTML=rows+`<h2>Total: ${money(total)}</h2><a class="button" target="_blank" href="https://wa.me/5500000000000?text=${text}">Finalizar pelo WhatsApp</a>`;
}
function removeCart(i){const c=getCart();c.splice(i,1);saveCart(c);renderCart()}
loadProducts();renderCart();
