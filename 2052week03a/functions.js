
//function declaration
const greetings = (messag) => {
    console.log('Greetings ${messag.toUpperCase()} from my server-side code. ');
}

//call the function greetings
let nameData = "Josh";
greetings(nameData);
greetings("James");