const daysOfWeek = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

const dayNumber = Math.floor(Math.random() * 7) + 1;

const dayName = daysOfWeek[dayNumber - 1];

const message = (dayNumber === 6 || dayNumber === 7) ? "It's the weekend!" : "It's a work day :(";

console.log(`${dayName} - ${message}`);
