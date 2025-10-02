let mybook = {
    name: "My life Story",
    price: 9.99,
    author: "John Busch",
    genre: ["Horror", "Dark", "Gory"]
};

//console.log(`the price is £${mybook.price}`);

//console.log(mybook.genre);

mybook.genre.forEach( (category) => {

    //console.log(category);

} );

let guitars = [
    {
    "Creator": "Fender",
    "Model": "American Strat",
    "Stock": 3,
    "Price": 899.99,
    "colours": ["white", "red", "gold"],
    }, 
    {
    "Creator": "Gibson",
    "Model": "Les Paul",
    "Stock": 6,
    "Price": 1499.99,
    }
];

guitars.forEach( (element) => {
    console.log(`Make: ${element.Creator} - Model: ${element.Model} costs £${element.Price}
        - Colours available are ... ${element.colours} `);
});

console.table(guitars);