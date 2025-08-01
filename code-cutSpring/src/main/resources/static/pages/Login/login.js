import { loginUser } from "../../services/api.js";

const loginform = document.querySelector("form");

loginform.addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = Object.fromEntries([... new FormData(loginform)]);
  try{
    const response = await loginUser(data);
    if(!response)return;
    if(response.status === 500)return;
    alert("Iniciaste sesion");
    window.location.href = "../products/products.html";
  }catch(e){
    console.log(e);
  }
})