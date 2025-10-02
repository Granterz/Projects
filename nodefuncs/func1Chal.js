const sortNum = (data, mode) => {

    let arr = data.toString().split("");

    if (mode === "sort") {
        arr.sort((a, b) => a - b); 
    } else if (mode === "reverse") {
        arr.reverse(); 
    }

    return arr.join(" ");
};

let myreverse = sortNum(20800790901, "reverse");
console.log(myreverse); 

let mysort = sortNum(20800790901, "sort");
console.log(mysort); 
