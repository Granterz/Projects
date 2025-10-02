const reserve = (str) =>{
    let num = str.length-1;
    let nStr="";
    for (num; num >=0; num-- ){
        nStr += str[num];
    }
    return nStr;
};

let reservestring = reserve("this is me");
console.log(reservestring);