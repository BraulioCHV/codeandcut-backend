const baseUrl = 'http://localhost:8080/api/orders';

export async function getAllOrders() {
  const res = await fetch(baseUrl);
  return res.json();
}

export async function createOrder(order) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(order)
  });
  return res.json();
}