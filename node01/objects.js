let toy = { name: "snot", toy_type: "puppet", colour: "pink and white" };
console.log( toy.name );
console.log( toy.colour ); 

toy.favourite = false;

if(toy.favourite){
    console.log(` ${toy.name} is your favourite toy`);
  }else{
    console.log("Not your favourite toy");
}

let pens = [{ pen_type: "foutain", ink_colour: "black", cost: 10.99 },
            { pen_type: "ball point", ink_colour: "blue", cost: 2.99 },
            { pen_type: "gel", ink_colour: "red", cost: 5.99 },
            { pen_type: "gel", ink_colour: "black", cost: 4.99 },
            ];
            
for (data in pens){
    console.log( `Pen - ${pens[data]["pen_type"]}` );
}

pens.forEach((mypen) =>{
    console.log(`I have a ${mypen.pen_type} pen and it's colour is ${mypen.ink_colour}.`)
});