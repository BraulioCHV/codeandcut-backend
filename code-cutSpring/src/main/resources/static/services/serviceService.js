const baseUrl = 'http://localhost:8080/api/services';

export async function getAllServices() {
  const res = await fetch(baseUrl);
  return res.json();
}

export async function createService(service) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(service)
  });
  return res.json();
}