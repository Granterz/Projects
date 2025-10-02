function getRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

const uniqueNumbers = new Set();

while (uniqueNumbers.size < 9) {
    uniqueNumbers.add(getRandomNumber(1, 49));
}

const randomNumbers = Array.from(uniqueNumbers);

const tableData = randomNumbers.map((value, index) => ({ Index: index, Value: value }));

console.table(tableData);
