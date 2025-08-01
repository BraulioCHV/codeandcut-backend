const baseUrl = 'http://localhost:8080/api/products';

export async function getAllProducts() {
  const res = await fetch(baseUrl);
  return res.json();
}

export async function addProduct(product) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(product)
  });
  return res.json();
}