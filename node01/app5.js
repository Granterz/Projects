const workweek = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"];
// console.log( workweek[4] ); 
for (day in workweek){
    console.log( `Day - ${workweek[day]}` );
}

let txt = "Work day -";
workweek.forEach((data) => {
    console.log( `${txt} - ${data}` );
});

workweek[4] = 'SATURDAY'; 
workweek.push('SUNDAY'); 

workweek.forEach((data) => {
    console.log( `new - ${data}` );
});