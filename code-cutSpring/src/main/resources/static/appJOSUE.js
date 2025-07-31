import {addUser} from "./services/api.js";

const registerForm = document.querySelector("form");


registerForm.addEventListener("submit",async (e) => {
  e.preventDefault();
  const data = Object.fromEntries([... new FormData(registerForm)]);
  try{
   const response = await addUser(data);
   alert("Registro exitoso");
  window.location.href = "./pages/Login/login.html"
  console.log(response.ok)
  }catch(err){
    alert("algo salio mal")
  }
})