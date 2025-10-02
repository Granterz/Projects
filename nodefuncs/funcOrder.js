let firstCall = (callB) => {
    setTimeout(() => {
        console.log('Creates a calculation which is 100, but takes one second.');
        let resultFirst = 100;
        return callB(resultFirst);
    }, 1000);
};

let cal2 = firstCall( (num) =>  {

        console.log(`Called to perform logic on the first function result..`);
        let numCal = num + 200;
        console.log(`Result: ${numCal}`);

    }
);

