const baseUrl = 'http://localhost:8080/api/users';

export async function addUser(user) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(user)
  });
  return res.json();
}

export async function getAllUsers() {
  const res = await fetch(baseUrl);
  return res.json();
}

export async function getUserById(id) {
  const res = await fetch(`${baseUrl}/${id}`);
  return res.json();
}

export async function updateUser(id, user) {
  const res = await fetch(`${baseUrl}/${id}`, {
    method: 'PUT',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(user)
  });
  return res.json();
}

export async function deleteUser(id) {
  const res = await fetch(`${baseUrl}/${id}`, {
    method: 'DELETE'
  });
  return res;
}