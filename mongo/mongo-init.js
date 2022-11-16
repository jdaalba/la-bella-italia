db.createUser(
    {
      user: 'api_user',
      pwd: 'NdEep0XLpMNKUmgQVa81oDCx7mrSRodh0Z79qdX3',
      roles: [{role: 'readWrite', db: 'db'}],
    }
);

// user
db.createCollection('user')
db.getCollection('user').insertOne({
  _id: ObjectId(),
  username: "admin",
  password: "$2a$10$p0npGUgS6gubS4QXvX0O/u5QKZ418ZhqoC2Hpl4qNlwbYJu6/X956",
  userRoles: ["ADMIN"],
  _class: "com.jdaalba.entity.User"
});

// platos
db.createCollection('platos')
db.getCollection('platos').insertMany([
  {
    _id: "0c5fb679-d15a-4263-bfee-dbc0c3bbb555",
    categoria: "PIZZAS",
    nombre: "Margarita",
    ingredientes: [
      "Tomates frescos",
      "mozzarella fresca",
      "albahaca fesca"
    ],
    precio: 12.5,
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "25829974-0aef-4bd6-8392-fa2881d9e9b9",
    categoria: "ENSALADAS",
    nombre: "Lasagna",
    ingredientes: [
      "Salsa especial",
      "mozzarella",
      "queso parmesana",
      "ternera local"
    ],
    precio: 13.5,
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "235eb6d6-eb91-4456-a14d-7f0e3c73f76f",
    categoria: "APERITIVOS",
    nombre: "Sopa del día",
    ingredientes: [
      "Preguntar al camarero"
    ],
    precio: 5.5,
    "etiqueta": "TEMPORADA",
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "1af9a07e-debc-4199-81ca-662c1b2a63f2",
    categoria: "PIZZAS",
    nombre: "Pizza BBQ",
    ingredientes: [
      "Maldad infinita",
      "masa de pizza",
      "ingrediente secreto"
    ],
    precio: 13,
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "f71ecdca-4a7d-4abf-905d-3211fcedb859",
    categoria: "ENSALADAS",
    nombre: "Ensalada de la casa",
    ingredientes: [
      "tomate",
      "lechuga"
    ],
    precio: 10,
    "etiqueta": "TEMPORADA",
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "e5d7c616-c9e3-4ee6-a321-389a6698be71",
    categoria: "ENSALADAS",
    nombre: "Ensalada de tomate",
    ingredientes: [
      "Tomate (nada más)"
    ],
    precio: 7,
    "etiqueta": "NUEVO",
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "91527bfc-63d7-456b-a64a-57dd6aaba749",
    categoria: "PIZZAS",
    nombre: "Cuatro quesos",
    ingredientes: [
      "4 quesos",
      "tomate"
    ],
    precio: 15.5,
    "etiqueta": "NUEVO",
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "1d55700c-47fd-400d-ac1e-6e98e228fe19",
    categoria: "PIZZAS",
    nombre: "Cuatro Estaciones",
    ingredientes: [
      "Otoño",
      "Verano",
      "Invierno",
      "Primavera"
    ],
    precio: 13,
    "etiqueta": "TEMPORADA",
    _class: "com.jdaalba.entity.Plato"
  },
  {
    _id: "5d50e106-81d9-4649-8f9d-f7c79edbd796",
    categoria: "APERITIVOS",
    nombre: "Berenjenas a la napolitana",
    ingredientes: [
      "Berenjenas",
      "orégano"
    ],
    precio: 8,
    "etiqueta": "NUEVO",
    _class: "com.jdaalba.entity.Plato"
  }
]);