let uidash = new Map();
var express = require('express');
var app = express()
app.use(express.urlencoded({ extended: true }))
app.use(express.json());

app.get('/',function(request,response){
response.send("Welcome To the AD SEVER!")
});


app.post('/savetoken', function(request, response){

  if(uidash.get(request.body.uid)== undefined | uidash.get(request.body.uid)!=request.body.token){
  uidash.set(request.body.uid,request.body.token);
  console.log(uidash)
  response.json({ token: 'updated' });   // echo the result back
  }
});

app.listen(3001);
