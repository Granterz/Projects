function welcome(data){
    console.log(`Welcome to my site ${data}.`);
}

welcome("James");

function calVAT(cost){
    let newcost = cost * .2;
    return newcost;
}

let newprice = calVAT(69.99);
console.log(newprice);

const calvat1 = function(para1, para2){
    let vat = para1 * para2;
    let res = Math.floor(vat + para1);
    return res;
}

console.log(`New price with VAT added: £${calvat1(12.99, .2)}`);

const calvat2 = (costData, vatData) => {
    let vat = costData * vatData;
    let cost = vat + costData;
    let price = cost.toFixed(2);
    return price;
}

console.log(`New price with with 2 decimal places and VAT added: £${calvat2(109, .2)}`);