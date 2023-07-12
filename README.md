#### Endpoints de la api

---

**Buscar usuario**

`http://localhost:8081/api/customer?name=<name_user>&numid=<num_identi>&page=<page>&limit<limit_items>`

**Crear usuario**

`http://localhost:8081/api/customer`

```
{
    "names" : "Anderson Macias Ordonez",
    "identificationType":"DNI",
    "identificationNumber" : "2101165609",
    "email" : "anderk@gmail.com",
    "phone": "0981942117",
    "mainProvince" : "Pichincha",
    "mainCity" : "Quito",
    "mainAddress" : "Av Putito"
}
```

**Editar usuario**

`http://localhost:8081/api/customer/<user_id>`

```
{
    "names" : "Anderson Macias Ordonez",
    "identificationType":"DNI",
    "identificationNumber" : "2101165609",
    "email" : "anderk@gmail.com",
    "phone": "0981942117",
    "mainProvince" : "Pichincha",
    "mainCity" : "Quito",
    "mainAddress" : "Av Putito"
}
```

**Eliminar usuario**

`http://localhost:8081/api/customer/<user_id>`

**Crear direccion adicional de usuario**

`http://localhost:8081/api/customer/address/<user_id>/user`

```
{
    "province": "Sucumbios",
    "city": " Tarapoa",
    "address": "Av. SAn JOSE"
}
```

**Editar direccion**

`http://localhost:8081/api/customer/address/<address_id>/user/<user_id>`

```
{
    "province": "Sucumbios",
    "city": " Tarapoa",
    "address": "Av. SAn JOSE"
}
```

**Obtener direcciones de usuario**

`http://localhost:8081/api/customer/address/<user_id>/user`

**Obtener direccion principal de usuario**

`http://localhost:8081/api/customer/address/main/<user_id>/user`

**Eliminar direccion**

`http://localhost:8081/api/customer/address/<address_id>`
