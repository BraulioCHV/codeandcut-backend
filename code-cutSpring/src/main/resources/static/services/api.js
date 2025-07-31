const URLBASE = `http://localhost:8080/api`

//add user
export const addUser = async (user) => {
  const response = await fetch(`${URLBASE}/users`, 
    {
      method:"POST",
      headers: {
    'Content-Type': 'application/json',
  },
      body: JSON.stringify(user),
      
    }
  )
  return response;
}

export const loginUser = async(user) => {
   const response = await fetch(`${URLBASE}/users/login`,
    {
      method:"POST",
      headers: {
    'Content-Type': 'application/json',
  },
      body: JSON.stringify(user),
      
    }
   );
   const data = await response.json();
   return data;
}

