

let dateToday = new Date();

console.log(dateToday);

let day = dateToday.getDay();

console.log(day);

switch (day) {
    case 0:
        console.log(`Today is Sunday`);
        break;
        
    case 1:
        console.log(`Today is Monday`);
        break;
        
    case 2:
        console.log(`Today is Tuesday`);
        break;
        
    case 3:
        console.log(`Today is Wednesday`);
        break;

    default:
        break;
}