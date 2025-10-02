console.log( 45 > 40 );    
console.log( 45 == 44 );    
console.log( 99 != 100 );  
console.log( 99 < 100 );   
console.log( 90 >= 100 );   

let a = 0;
let b = false;

console.log( a == b ); 
console.log( a === b );

let yr = new Date().getFullYear();
console.log(yr);
if (yr > 2020) {
    console.log("We are in a year after 2020");
}

let userrole = "admin";

if (userrole === "member"){
    console.log("User has member rights."); 
 }else{
   console.log("User has member rights."); 
}

let user_age = 21;

if (user_age > 18) {
  access = true;
} else {
  access = false;
}

console.log(access);