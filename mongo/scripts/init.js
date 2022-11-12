db.createCollection('user')
db.getCollection('user').insertOne({
  _id: ObjectId(),
  username: "admin",
  password: "$2a$10$p0npGUgS6gubS4QXvX0O/u5QKZ418ZhqoC2Hpl4qNlwbYJu6/X956",
  userRoles: ["ADMIN"],
  _class: "com.jdaalba.entity.User"
});