const baseUrl = 'http://localhost:8080/api/appointments';

export async function createAppointment(data) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(data)
  });
  return res.json();
}

export async function getAllAppointments() {
  const res = await fetch(baseUrl);
  return res.json();
}