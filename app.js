const express = require("express");

let app = express();

app.get("/input", (req, res) => {

    res.send(`<h2>My Age</h2>
                <form action="/process">
                 <input name="myAge" type="number">
                 <button>Process</button>
                </form>`);
});

app.get("/process", (req, res) => { 

    const userAge = req.query.myAge;
    const currYear = new Date().getFullYear();
    const result = currYear - userAge;

    if(userAge!=="" ){
        res.send(`<h2>My Birth Year</h2>
            <p> Born in either ${result} OR ${result+1}.</p>`);
    }else{
        res.send(`<h2>error</h2>
                 <p> Please input a year <a href='/input'>back</a></p>`);
    }

});

app.get("/", (req, res) => {
    res.redirect("/input");
});

app.listen(3000, ()=>{
    console.log(" Server is listening on http://localhost:3000/ ");
});