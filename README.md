**API PARA GENERACION DE CAMPAÑAS DE ENVIO DE WHATSAPP**

- **Generacion de campaña:**  
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
**Consideraciones**
>  El parametro *activation_date* indica la fecha en la que se va a enviar la campaña. El formato tien que ser **"YYYY-MM-dd hh:mm"** a modo de ejemplo: 2018-04-20 10:20"


- Obtener una campaña

*Agregar destinos a una campaña*
(pendiente)

*Consultar todas las campañas*
(pendiente)

*Agregar destinos*
(pendiente)

*Consultra destinos de una campaña*
(pendiente)
