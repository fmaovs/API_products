const obtener = async() => {
    //url del endpoint a consumir
    const url = "http://localhost:8081/api/products"
    //definir las credenciales
    const username = "user"
    const password = "c79d1de4-6cd8-44bf-8909-a29047f32939"

    //Definir un objeto que contenga las opciones de conexion 
    const options = {
        method: "GET",
        headers: {
            "Authorization" : "Basic " + btoa( `${username}:${password}` ),
        }

    }

//uso del fetch para conectar
const response = await fetch(url, options)
const products = await response.json()
console.log(products)
}

obtener()