let grades = [
    { subject: 'Maths', grade: 45 },
    { subject: 'English Lit', grade: 55 },
    { subject: 'Grammar', grade: 40 },
    { subject: 'Science', grade: 59 },
    { subject: 'History', grade: 71 },
];

const printItems = (currentItem) => {
    //console.log(currentItem);
};

grades.forEach(printItems);

let generateStr = (data) => {
    return `${data} callback process`;
};

let print = (callback) => {  
    let res = callback("Hi from the");
    return res;
};

let mymessage = print(generateStr);

console.log(` ${mymessage} `);