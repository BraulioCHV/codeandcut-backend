import { addUser } from './services/userService.js';

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const data = Object.fromEntries(new FormData(form));
  try {
    await addUser(data);
    alert("Usuario creado con éxito");
  } catch (err) {
    console.error(err);
    alert("Error al registrar usuario");
  }
});