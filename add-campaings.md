
#### Generacion de campaña:  
*Metodo:* POST  
*Path:* /api/campaing  

Body example:
```javascript
{
	"text":<Texto a enviar en la campaña>,
	"description":<Descipcion de la campaña>,
	"activation_date":<Fecha de activacion de la campaña>
}
```
Consideraciones:
>  El parametro *activation_date* indica la fecha en la que se va a enviar la campaña. El formato tien que ser **"YYYY-MM-dd hh:mm"** a modo de ejemplo: 2018-04-20 10:20"

#### Consultar las campañas  
Permite obtener todas las campañas que se han dado de alta. La forma de consultarlas es la siguiente:  
*Metdodo:* GET  
*Path:* /api/campaign

Consideraciones:
> - Es posible obtener los datos de una campaña en particular. Para esto hay que agregar el id de campaña al path _"/api/campaign/{campaignId}"_  
- Es posible consultar una campaña con la totalidad de la informacion, es decir con los numeros destinos que tiene asigandos. Para esto se debe agregar el query param **"option"** con el valor **destinations** . A modo de ejemplo: _"/api/campaign?options=destinations"_

#### Elimniacion de campañas ####
(pendiente)

#### Agregar destinos a una campaña ####  
Para agregar destinos a una campaña se debe realizar de la siguiente manera:  

*Metodo:* PATCH  
*Path:* /api/campaing/{campaingId}/destinations  

Body example:
```javascript
{
	"destinations":["1231231","13123123123"]
}
```
El campo "destinations" es un array de numeros

#### Consultra destinos de una campaña ####
Es posible consultar los destinos que tiene cargados una campaña. Para esto se debe consultar de la siguiente manera:
