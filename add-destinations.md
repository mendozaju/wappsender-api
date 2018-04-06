## Agregar destinos a una campaña ##
Dada una campaña, es posible agregar destinatarios. Estos seran los numeros de telefons a los que la campaña sera enviada. Las operaciones que se pueden realizar son las siguientes:

* [Obtener todos los destinos de una campaña](#Obtener-todos-los-destinatarios-a una-campaña)
* [Agregar un destinatario a una campaña](#agregar-un-destinatario-a-una-campaña)
* [Actualizar un destinatario puntual](#actualizar-un-destinatario-puntual)
* [Pisar todos los destinatarios](#pisar-todos-los-destinatarios)
* [Eliminar todos los destinatarios](#eliminar-todos-los-destinatarios)  

A continuacion se describen cada una de estas operaciones.

#### Obtener todos los destinatarios a una campaña :
  Este metodo permite obtener todos los destinatarios que esten configurados para una campaña.

*Metodo:* GET
*Path:* /api/campaing/{campaingId}/destinations  

Response Example:

```javascript
{
    "destinations": [
        {
            "number": "999999999999999",
            "id": 18
        },
        {
            "number": "999999999999999",
            "id": 19
        }
    ],
    "quantity": 2,
    "campaing_id": 1
}
```
El campo "id" dentro del arreglo de destinatarios (destinations) es el id de ese destino, ese id debera ser utilizado si se quiere actualizar el numero de ese destinatario (Se detalla en "Actualizar un destinatario puntual").

#### Agregar un destinatario a una campaña:
Este metodo es utilizado para agregar un destinatario adicional a una campaña, no elimina los ya existentes, sino que adiciona. El metodo permite agregar tanto un nuevo destinatario como un conjunto de ellos.

*Metodo:* PUT  
*Path:* /api/campaing/{campaingId}/destinations  

Body example:
```javascript
{
	"destinations":[{
		"number":"111111111111"
	},{
		"number":"222222222222"
	}]
}
```
Response Example:

```javascript
{
    "code": "OK",
    "description": "Se agrean correctamente los destinos a la campaña"
}
```

#### Actualizar un destinatario puntual
Este metodo nos permite poder actualizar el valor de un destinatario particular (En caso de que no queramos eliminarlo y crearlo nuevamete). El metodo permite actualizar uno o varios destinatarios a la vez.

*Metodo:* PATCH  
*Path:* /api/campaing/{campaingId}/destinations  

Body example:
```javascript
{
	"campaign_id":1,
	"destinations":[{
		"id":"18",
		"number":"999999999999999"
	},
	{
		"id":"19",
		"number":"999999999999999"
	}]
}
```

Response Example:

```javascript
{
    "code": "OK",
    "description": "Se actualizarion 2 registros"
}
```

#### Pisar todos los destinatarios
Este metod pisa todos los destinatarios, eliminando los destinatarios actuales y reemplazandolos por los enviados en el metodo.

*Metodo:* POST  
*Path:* /api/campaing/{campaingId}/destinations  

Body example:
```javascript
{
	"destinations":[{
		"number":"999999999999999"
	},{
		"number":"999999999999999"
	}]
}
```
Response Example:

```javascript
{
    "code": "OK",
    "description": "Se insertarion 2 registros"
}
```

#### Eliminar todos los destinatarios
Este metodo permite eliminar todos los destinatarios

*Metodo:* DEL  
*Path:* /api/campaing/{campaingId}/destinations  

Body example:
```javascript
{
	"campaign_id":1
}

Response Example:

```javascript
{
    "code": "OK",
    "description": "Se elimniarion 2 registros"
}
```




Pisar todos los destinatarios
