let array = ["Fender", "Les Paul", "Ibanez"]; //simple array
let arr2D = [["Fender", 34], ["Les Paul", 12], ["Ibanez", 6]]; //2D array
//console.table(arr2D);

const days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];

days.forEach((day) =>{
    console.log(` - ${day}`);
});

for (let index = 0; index < days.length; index++) {
    const day = days[index];
    //console.log(`${index} - ${day}`);
}

let index=0;
for (const day of days) {
    console.log(`${index} - ${day}`);
    index++;
}